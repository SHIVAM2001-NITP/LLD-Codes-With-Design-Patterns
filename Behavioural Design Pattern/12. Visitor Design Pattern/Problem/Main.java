public class Main {
    public static void main(String[] args) {

        Book book = new Book("Clean Code", 500);
        Fruit fruit = new Fruit("Apple", 120, 2);
        Electronics phone = new Electronics("Phone", 20000, 2);

        // The cart holds 3 DIFFERENT classes, so we cannot even keep them in one List<Item>
        // without a common interface, and every new operation (JSON export, discount, insurance...)
        // means opening Book, Fruit and Electronics again and editing all of them.
        double tax = book.getTax() + fruit.getTax() + phone.getTax();
        double shipping = book.getShippingCost() + fruit.getShippingCost() + phone.getShippingCost();

        System.out.println("Total tax      : " + tax);
        System.out.println("Total shipping : " + shipping);
    }
}
