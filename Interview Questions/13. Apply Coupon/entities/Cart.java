package entities;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final List<CartItem> items = new ArrayList<>();
    private String appliedCouponCode;
    private double discount = 0;

    public void addItem(Product product, int quantity) {
        items.add(new CartItem(product, quantity));
    }

    public List<CartItem> getItems() { return items; }

    public List<CartItem> getItemsInCategory(String category) {
        List<CartItem> result = new ArrayList<>();
        for (CartItem item : items) {
            if (item.getProduct().getCategory().equals(category)) {
                result.add(item);
            }
        }
        return result;
    }

    public double getSubtotal() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getSubtotal();
        }
        return total;
    }

    public double getFinalAmount() { return getSubtotal() - discount; }

    public double getDiscount() { return discount; }

    public String getAppliedCouponCode() { return appliedCouponCode; }

    public void applyDiscount(String couponCode, double discountAmount) {
        this.appliedCouponCode = couponCode;
        this.discount = Math.min(discountAmount, getSubtotal());
    }

    public void removeCoupon() {
        this.appliedCouponCode = null;
        this.discount = 0;
    }
}
