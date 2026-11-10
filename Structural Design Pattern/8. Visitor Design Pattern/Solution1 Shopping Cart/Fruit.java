public class Fruit implements Item {
    private String name;
    private double pricePerKg;
    private double weightInKg;

    public Fruit(String name, double pricePerKg, double weightInKg) {
        this.name = name;
        this.pricePerKg = pricePerKg;
        this.weightInKg = weightInKg;
    }

    public String getName() { return name; }
    public double getPricePerKg() { return pricePerKg; }
    public double getWeightInKg() { return weightInKg; }

    @Override
    public void accept(ItemVisitor visitor) {
        visitor.visit(this);   // 'this' is a Fruit here, so visit(Fruit) is chosen
    }
}
