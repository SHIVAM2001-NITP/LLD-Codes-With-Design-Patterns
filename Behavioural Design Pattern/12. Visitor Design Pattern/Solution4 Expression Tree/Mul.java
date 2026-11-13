public class Mul implements Expr {
    private Expr left, right;

    public Mul(Expr left, Expr right) {
        this.left = left;
        this.right = right;
    }

    public Expr getLeft() { return left; }
    public Expr getRight() { return right; }

    @Override
    public <R> R accept(ExprVisitor<R> visitor) { return visitor.visitMul(this); }
}
