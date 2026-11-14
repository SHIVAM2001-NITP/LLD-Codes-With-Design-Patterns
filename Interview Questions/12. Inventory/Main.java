import entities.Product;
import entities.StockEntry;
import exceptions.InsufficientStockException;
import exceptions.InvalidQuantityException;
import exceptions.ProductNotFoundException;
import service.InventoryManager;
import strategy.TopUpToLevelReplenishmentStrategy;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;

public class Main {
    public static void main(String[] args) {

        InventoryManager manager = new InventoryManager(
                new TopUpToLevelReplenishmentStrategy(50), Clock.fixed(Instant.parse("2026-01-01T00:00:00Z"), ZoneOffset.UTC));

        Product widget = new Product("SKU-1", "Widget", 10);
        manager.registerProduct(widget);

        System.out.println("=== Receive and reserve stock ===");
        manager.receiveStock("SKU-1", "WH-EAST", 100);
        manager.reserveStock("SKU-1", "WH-EAST", 30);
        StockEntry entry = manager.getStock("SKU-1", "WH-EAST");
        System.out.println("On hand: " + entry.getOnHand() + ", reserved: " + entry.getReserved() + ", available: " + entry.getAvailable());

        System.out.println("\n=== Ship the reserved stock ===");
        manager.shipStock("SKU-1", "WH-EAST", 30);
        System.out.println("On hand: " + entry.getOnHand() + ", reserved: " + entry.getReserved() + ", available: " + entry.getAvailable());

        System.out.println("\n=== Reserve and ship enough to trigger a reorder ===");
        manager.reserveStock("SKU-1", "WH-EAST", 62);
        manager.shipStock("SKU-1", "WH-EAST", 62);     // available drops to 8, at or below the threshold of 10
        System.out.println("On hand: " + entry.getOnHand() + ", available: " + entry.getAvailable() + ", low stock: " + entry.isLowStock());

        System.out.println("\n=== Transfer stock between warehouses ===");
        manager.receiveStock("SKU-1", "WH-WEST", 5);
        manager.transferStock("SKU-1", "WH-EAST", "WH-WEST", 3);
        System.out.println("WH-EAST available: " + manager.getStock("SKU-1", "WH-EAST").getAvailable());
        System.out.println("WH-WEST available: " + manager.getStock("SKU-1", "WH-WEST").getAvailable());

        System.out.println("\n=== A damaged-stock adjustment ===");
        manager.adjustStock("SKU-1", "WH-WEST", -2);
        System.out.println("WH-WEST on hand: " + manager.getStock("SKU-1", "WH-WEST").getOnHand());

        System.out.println("\n-- Rejections --");
        try {
            manager.reserveStock("SKU-1", "WH-EAST", 1000);
        } catch (InsufficientStockException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
        try {
            manager.shipStock("SKU-1", "WH-EAST", 5);     // nothing reserved right now
        } catch (InsufficientStockException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
        try {
            manager.receiveStock("SKU-1", "WH-EAST", -10);
        } catch (InvalidQuantityException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
        try {
            manager.receiveStock("SKU-404", "WH-EAST", 10);
        } catch (ProductNotFoundException e) {
            System.out.println("Rejected: " + e.getMessage());
        }

        System.out.println("\n=== Audit trail (" + manager.getTransactions().size() + " entries) ===");
        manager.getTransactions().forEach(System.out::println);
    }
}
