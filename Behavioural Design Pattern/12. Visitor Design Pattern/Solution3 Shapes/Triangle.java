public class Triangle implements Shape {
    private double a, b, c;   // side lengths

    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double getA() { return a; }
    public double getB() { return b; }
    public double getC() { return c; }

    @Override
    public void accept(ShapeVisitor visitor) { visitor.visit(this); }
}
