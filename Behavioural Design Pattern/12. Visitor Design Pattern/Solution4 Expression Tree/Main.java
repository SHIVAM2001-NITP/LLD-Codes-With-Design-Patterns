public class Main {
    public static void main(String[] args) {

        // (2 + 3) * 4
        Expr expression = new Mul(new Add(new Num(2), new Num(3)), new Num(4));

        int result = expression.accept(new EvaluateVisitor());
        String text = expression.accept(new PrintVisitor());
        int nodes = expression.accept(new NodeCountVisitor());

        System.out.println(text + " = " + result);   // ((2 + 3) * 4) = 20
        System.out.println("Nodes: " + nodes);       // Nodes: 5
    }
}
