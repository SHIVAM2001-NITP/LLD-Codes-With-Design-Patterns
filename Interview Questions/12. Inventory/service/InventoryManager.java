package service;

import entities.Product;
import entities.StockEntry;
import entities.Transaction;
import enums.TransactionType;
import exceptions.InsufficientStockException;
import exceptions.InvalidQuantityException;
import exceptions.ProductNotFoundException;
import strategy.ReplenishmentStrategy;

import java.time.Clock;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Single entry point for every stock movement. Every method validates the quantity, finds
// (or refuses to find) the (product, warehouse) entry, applies the change, and appends one
// Transaction - so the audit trail can never miss or double-count a movement.
public class InventoryManager {
    private final Map<String, Product> products = new HashMap<>();
    private final Map<String, StockEntry> stock = new HashMap<>();   // key: sku + "@" + warehouseId
    private final List<Transaction> transactions = new ArrayList<>();
    private final ReplenishmentStrategy replenishmentStrategy;
    private final Clock clock;

    public InventoryManager(ReplenishmentStrategy replenishmentStrategy, Clock clock) {
        this.replenishmentStrategy = replenishmentStrategy;
        this.clock = clock;
    }

    public void registerProduct(Product product) { products.put(product.getSku(), product); }

    public synchronized void receiveStock(String sku, String warehouseId, int quantity) {
        requirePositive(quantity);
        StockEntry entry = entryFor(sku, warehouseId);
        entry.receive(quantity);
        log(entry, TransactionType.INBOUND, quantity);
    }

    public synchronized void reserveStock(String sku, String warehouseId, int quantity) {
        requirePositive(quantity);
        StockEntry entry = entryFor(sku, warehouseId);
        if (entry.getAvailable() < quantity) {
            throw new InsufficientStockException("Only " + entry.getAvailable() + " available for " + entry.getProduct() + " at " + warehouseId);
        }
        entry.reserve(quantity);
        log(entry, TransactionType.RESERVED, quantity);
    }

    public synchronized void releaseReservation(String sku, String warehouseId, int quantity) {
        requirePositive(quantity);
        StockEntry entry = entryFor(sku, warehouseId);
        if (entry.getReserved() < quantity) {
            throw new InvalidQuantityException("Cannot release " + quantity + ", only " + entry.getReserved() + " is reserved");
        }
        entry.release(quantity);
        log(entry, TransactionType.RELEASED, quantity);
    }

    public synchronized void shipStock(String sku, String warehouseId, int quantity) {
        requirePositive(quantity);
        StockEntry entry = entryFor(sku, warehouseId);
        if (entry.getReserved() < quantity) {
            throw new InsufficientStockException("Cannot ship " + quantity + ", only " + entry.getReserved() + " is reserved for " + entry.getProduct());
        }
        entry.ship(quantity);
        log(entry, TransactionType.OUTBOUND, quantity);
        maybeReplenish(entry);
    }

    public synchronized void adjustStock(String sku, String warehouseId, int delta) {
        StockEntry entry = entryFor(sku, warehouseId);
        if (entry.getOnHand() + delta < entry.getReserved()) {
            throw new InvalidQuantityException("Adjustment would drop on-hand stock below what is already reserved");
        }
        entry.adjust(delta);
        log(entry, TransactionType.ADJUSTMENT, delta);
    }

    // Moves stock between two warehouses for the same product, as one atomic operation
    public synchronized void transferStock(String sku, String fromWarehouse, String toWarehouse, int quantity) {
        requirePositive(quantity);
        StockEntry from = entryFor(sku, fromWarehouse);
        if (from.getAvailable() < quantity) {
            throw new InsufficientStockException("Only " + from.getAvailable() + " available to transfer from " + fromWarehouse);
        }
        from.adjust(-quantity);
        log(from, TransactionType.OUTBOUND, quantity);
        StockEntry to = entryFor(sku, toWarehouse);
        to.receive(quantity);
        log(to, TransactionType.INBOUND, quantity);
    }

    public StockEntry getStock(String sku, String warehouseId) { return entryFor(sku, warehouseId); }

    public List<Transaction> getTransactions() { return transactions; }

    private void maybeReplenish(StockEntry entry) {
        if (entry.isLowStock()) {
            int reorderQuantity = replenishmentStrategy.quantityToReorder(entry);
            System.out.println("  [Reorder] " + entry.getProduct() + " at " + entry.getWarehouseId()
                    + " is low (" + entry.getAvailable() + " available) -> reordering " + reorderQuantity);
        }
    }

    private StockEntry entryFor(String sku, String warehouseId) {
        Product product = products.get(sku);
        if (product == null) {
            throw new ProductNotFoundException("Unknown SKU: " + sku);
        }
        return stock.computeIfAbsent(sku + "@" + warehouseId, k -> new StockEntry(product, warehouseId));
    }

    private void log(StockEntry entry, TransactionType type, int quantity) {
        transactions.add(new Transaction(entry.getProduct(), entry.getWarehouseId(), type, quantity, clock.instant()));
    }

    private void requirePositive(int quantity) {
        if (quantity <= 0) {
            throw new InvalidQuantityException("Quantity must be positive, got " + quantity);
        }
    }
}
