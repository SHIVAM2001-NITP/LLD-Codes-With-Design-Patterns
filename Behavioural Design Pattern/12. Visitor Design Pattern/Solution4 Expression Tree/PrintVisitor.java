// Operation 2: print the tree as text, R = String
public class PrintVisitor implements ExprVisitor<String> {

    @Override
    public String visitNum(Num num) { return String.valueOf(num.getValue()); }

    @Override
    public String visitAdd(Add add) {
        return "(" + add.getLeft().accept(this) + " + " + add.getRight().accept(this) + ")";
    }

    @Override
    public String visitMul(Mul mul) {
        return "(" + mul.getLeft().accept(this) + " * " + mul.getRight().accept(this) + ")";
    }
}
