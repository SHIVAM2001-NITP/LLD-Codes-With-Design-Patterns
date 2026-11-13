// Generic visitor: every operation chooses its own return type R
public interface ExprVisitor<R> {
    R visitNum(Num num);
    R visitAdd(Add add);
    R visitMul(Mul mul);
}
