public interface Expr {
    <R> R accept(ExprVisitor<R> visitor);
}
