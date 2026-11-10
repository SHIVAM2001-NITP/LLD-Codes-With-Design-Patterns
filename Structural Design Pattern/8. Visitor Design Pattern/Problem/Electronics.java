public class Electronics {
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

    public double getTax() { return price * 0.18; }

    public double getShippingCost() { return 150; }
}
