// Concrete Visitor 2: shipping rules for every item type
public class ShippingVisitor implements ItemVisitor {
    private double totalShipping = 0;

    @Override
    public void visit(Book book) {
        totalShipping += 40;
    }

    @Override
    public void visit(Fruit fruit) {
        totalShipping += 10 * fruit.getWeightInKg();
    }

    @Override
    public void visit(Electronics electronics) {
        totalShipping += 150;
    }

    public double getTotalShipping() { return totalShipping; }
}
