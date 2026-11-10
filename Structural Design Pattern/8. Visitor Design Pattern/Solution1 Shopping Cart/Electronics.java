public class Electronics implements Item {
    private String name;
    private double price;
    private int warrantyInYears;

    public Electronics(String name, double price, int warrantyInYears) {
        this.name = name;
        this.price = price;
        this.warrantyInYears = warrantyInYears;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getWarrantyInYears() { return warrantyInYears; }

    @Override
    public void accept(ItemVisitor visitor) {
        visitor.visit(this);   // 'this' is Electronics here, so visit(Electronics) is chosen
    }
}
