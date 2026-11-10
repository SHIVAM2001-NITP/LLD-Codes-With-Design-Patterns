import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Object structure: one list holding different element types
        List<Item> cart = new ArrayList<>();
        cart.add(new Book("Clean Code", 500));
        cart.add(new Fruit("Apple", 120, 2));
        cart.add(new Electronics("Phone", 20000, 2));

        TaxVisitor taxVisitor = new TaxVisitor();
        ShippingVisitor shippingVisitor = new ShippingVisitor();
        JsonExportVisitor jsonVisitor = new JsonExportVisitor();

        for (Item item : cart) {
            item.accept(taxVisitor);
            item.accept(shippingVisitor);
            item.accept(jsonVisitor);
        }

        System.out.println("Total tax      : " + taxVisitor.getTotalTax());
        System.out.println("Total shipping : " + shippingVisitor.getTotalShipping());
        System.out.println("JSON           : " + jsonVisitor.getJson());
    }
}
