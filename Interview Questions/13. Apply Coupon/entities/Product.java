package entities;

public class Product {
    private final String id;
    private final String name;
    private final String category;
    private final double price;

    public Product(String id, String name, String category, double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getId() { return id; }

    public String getName() { return name; }

    public String getCategory() { return category; }

    public double getPrice() { return price; }

    @Override
    public String toString() { return name + " ($" + price + ")"; }
}
