public class Fruit {
    private String name;
    private double pricePerKg;
    private double weightInKg;

    public Fruit(String name, double pricePerKg, double weightInKg) {
        this.name = name;
        this.pricePerKg = pricePerKg;
        this.weightInKg = weightInKg;
    }

    public String getName() { return name; }
    public double getPrice() { return pricePerKg * weightInKg; }

    public double getTax() { return 0; }

    public double getShippingCost() { return 10 * weightInKg; }
}
