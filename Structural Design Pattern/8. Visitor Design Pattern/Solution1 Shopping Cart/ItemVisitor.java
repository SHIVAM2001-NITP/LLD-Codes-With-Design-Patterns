// Visitor: one visit() method for every concrete element type
public interface ItemVisitor {
    void visit(Book book);
    void visit(Fruit fruit);
    void visit(Electronics electronics);
}
