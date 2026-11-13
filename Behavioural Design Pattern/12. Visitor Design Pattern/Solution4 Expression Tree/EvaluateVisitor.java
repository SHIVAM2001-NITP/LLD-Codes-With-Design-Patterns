// Operation 1: evaluate the tree, R = Integer
public class EvaluateVisitor implements ExprVisitor<Integer> {

    @Override
    public Integer visitNum(Num num) { return num.getValue(); }

    @Override
    public Integer visitAdd(Add add) {
        return add.getLeft().accept(this) + add.getRight().accept(this);
    }

    @Override
    public Integer visitMul(Mul mul) {
        return mul.getLeft().accept(this) * mul.getRight().accept(this);
    }
}
