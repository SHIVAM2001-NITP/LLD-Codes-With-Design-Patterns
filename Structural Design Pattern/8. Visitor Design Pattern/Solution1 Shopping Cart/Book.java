public class Book implements Item {
    private String name;
    private double price;

    public Book(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }

    @Override
    public void accept(ItemVisitor visitor) {
        visitor.visit(this);   // 'this' is a Book here, so visit(Book) is chosen (double dispatch)
    }
}
