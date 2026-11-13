public class Add implements Expr {
    private Expr left, right;

    public Add(Expr left, Expr right) {
        this.left = left;
        this.right = right;
    }

    public Expr getLeft() { return left; }
    public Expr getRight() { return right; }

    @Override
    public <R> R accept(ExprVisitor<R> visitor) { return visitor.visitAdd(this); }
}
