package entities;

// One warehouse's stock for one product. `reserved` is already-sold stock still physically
// on the shelf; available = onHand - reserved is what can be sold or reserved next.
public class StockEntry {
    private final Product product;
    private final String warehouseId;
    private int onHand;
    private int reserved;

    public StockEntry(Product product, String warehouseId) {
        this.product = product;
        this.warehouseId = warehouseId;
    }

    public Product getProduct() { return product; }

    public String getWarehouseId() { return warehouseId; }

    public int getOnHand() { return onHand; }

    public int getReserved() { return reserved; }

    public int getAvailable() { return onHand - reserved; }

    public void receive(int quantity) { onHand += quantity; }

    public void reserve(int quantity) { reserved += quantity; }

    public void release(int quantity) { reserved -= quantity; }

    public void ship(int quantity) {
        onHand -= quantity;
        reserved -= quantity;
    }

    public void adjust(int delta) { onHand += delta; }

    public boolean isLowStock() { return getAvailable() <= product.getReorderThreshold(); }
}
