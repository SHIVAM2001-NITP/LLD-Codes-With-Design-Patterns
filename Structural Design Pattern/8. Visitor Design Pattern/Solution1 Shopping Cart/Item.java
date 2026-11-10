// Element: every item of the cart can accept a visitor
public interface Item {
    void accept(ItemVisitor visitor);
}
