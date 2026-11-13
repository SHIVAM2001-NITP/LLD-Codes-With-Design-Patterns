package entities;

// One shelf position: a product and how many are left
public class Slot {
    private final Product product;
    private int quantity;

    public Slot(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() { return product; }

    public int getQuantity() { return quantity; }

    public boolean isEmpty() { return quantity == 0; }

    public void dispenseOne() {
        if (isEmpty()) {
            throw new IllegalStateException("Slot for " + product.getName() + " is empty");
        }
        quantity--;
    }
}
