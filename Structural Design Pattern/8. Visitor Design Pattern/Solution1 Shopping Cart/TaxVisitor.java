// Concrete Visitor 1: tax rules for every item type, kept in ONE place
public class TaxVisitor implements ItemVisitor {
    private double totalTax = 0;

    @Override
    public void visit(Book book) {
        totalTax += book.getPrice() * 0.05;
    }

    @Override
    public void visit(Fruit fruit) {
        totalTax += 0;   // fruits are tax free
    }

    @Override
    public void visit(Electronics electronics) {
        totalTax += electronics.getPrice() * 0.18;
    }

    public double getTotalTax() { return totalTax; }
}
