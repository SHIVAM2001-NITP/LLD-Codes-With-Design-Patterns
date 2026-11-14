package strategy;

import entities.Cart;

// A percentage off the whole cart, capped so a huge cart cannot blow past the intended cost
public class PercentageDiscountStrategy implements DiscountStrategy {
    private final double minCartValue;
    private final double percentage;
    private final double maxDiscountCap;

    public PercentageDiscountStrategy(double minCartValue, double percentage, double maxDiscountCap) {
        this.minCartValue = minCartValue;
        this.percentage = percentage;
        this.maxDiscountCap = maxDiscountCap;
    }

    @Override
    public boolean isApplicable(Cart cart) { return cart.getSubtotal() >= minCartValue; }

    @Override
    public double calculateDiscount(Cart cart) {
        return Math.min(cart.getSubtotal() * percentage / 100.0, maxDiscountCap);
    }
}
