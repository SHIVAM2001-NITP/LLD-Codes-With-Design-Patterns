public class Book {
    private String name;
    private double price;

    public Book(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }

    // operation 1 lives inside the class
    public double getTax() { return price * 0.05; }

    // operation 2 lives inside the class
    public double getShippingCost() { return 40; }
}
