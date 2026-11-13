// Sums the area of every shape it visits
public class AreaVisitor implements ShapeVisitor {
    private double totalArea = 0;

    @Override
    public void visit(Circle circle) {
        totalArea += Math.PI * circle.getRadius() * circle.getRadius();
    }

    @Override
    public void visit(Rectangle rectangle) {
        totalArea += rectangle.getWidth() * rectangle.getHeight();
    }

    @Override
    public void visit(Triangle triangle) {
        // Heron's formula
        double s = (triangle.getA() + triangle.getB() + triangle.getC()) / 2;
        totalArea += Math.sqrt(s * (s - triangle.getA()) * (s - triangle.getB()) * (s - triangle.getC()));
    }

    public double getTotalArea() { return totalArea; }
}
