package entities;

public class Product {
    private final String sku;
    private final String name;
    private final int reorderThreshold;    // reorder when available stock falls to or below this

    public Product(String sku, String name, int reorderThreshold) {
        this.sku = sku;
        this.name = name;
        this.reorderThreshold = reorderThreshold;
    }

    public String getSku() { return sku; }

    public String getName() { return name; }

    public int getReorderThreshold() { return reorderThreshold; }

    @Override
    public String toString() { return name + " (" + sku + ")"; }
}
