// Operation 3: count the nodes, R = Integer
public class NodeCountVisitor implements ExprVisitor<Integer> {

    @Override
    public Integer visitNum(Num num) { return 1; }

    @Override
    public Integer visitAdd(Add add) {
        return 1 + add.getLeft().accept(this) + add.getRight().accept(this);
    }

    @Override
    public Integer visitMul(Mul mul) {
        return 1 + mul.getLeft().accept(this) + mul.getRight().accept(this);
    }
}
