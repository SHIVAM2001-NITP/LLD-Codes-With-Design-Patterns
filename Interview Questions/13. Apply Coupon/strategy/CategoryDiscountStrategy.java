package strategy;

import entities.Cart;
import entities.CartItem;

// A percentage off only the items in one category, capped
public class CategoryDiscountStrategy implements DiscountStrategy {
    private final String category;
    private final double percentage;
    private final double maxDiscountCap;

    public CategoryDiscountStrategy(String category, double percentage, double maxDiscountCap) {
        this.category = category;
        this.percentage = percentage;
        this.maxDiscountCap = maxDiscountCap;
    }

    @Override
    public boolean isApplicable(Cart cart) { return !cart.getItemsInCategory(category).isEmpty(); }

    @Override
    public double calculateDiscount(Cart cart) {
        double categorySubtotal = 0;
        for (CartItem item : cart.getItemsInCategory(category)) {
            categorySubtotal += item.getSubtotal();
        }
        return Math.min(categorySubtotal * percentage / 100.0, maxDiscountCap);
    }
}
