public class Num implements Expr {
    private int value;

    public Num(int value) { this.value = value; }

    public int getValue() { return value; }

    @Override
    public <R> R accept(ExprVisitor<R> visitor) { return visitor.visitNum(this); }
}
