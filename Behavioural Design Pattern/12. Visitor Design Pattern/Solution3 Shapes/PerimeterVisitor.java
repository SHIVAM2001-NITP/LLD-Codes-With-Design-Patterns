// Sums the perimeter of every shape it visits
public class PerimeterVisitor implements ShapeVisitor {
    private double totalPerimeter = 0;

    @Override
    public void visit(Circle circle) {
        totalPerimeter += 2 * Math.PI * circle.getRadius();
    }

    @Override
    public void visit(Rectangle rectangle) {
        totalPerimeter += 2 * (rectangle.getWidth() + rectangle.getHeight());
    }

    @Override
    public void visit(Triangle triangle) {
        totalPerimeter += triangle.getA() + triangle.getB() + triangle.getC();
    }

    public double getTotalPerimeter() { return totalPerimeter; }
}
