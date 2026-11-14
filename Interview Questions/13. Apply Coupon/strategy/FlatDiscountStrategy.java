package strategy;

import entities.Cart;

// A fixed amount off, once the cart reaches a minimum subtotal
public class FlatDiscountStrategy implements DiscountStrategy {
    private final double minCartValue;
    private final double flatAmount;

    public FlatDiscountStrategy(double minCartValue, double flatAmount) {
        this.minCartValue = minCartValue;
        this.flatAmount = flatAmount;
    }

    @Override
    public boolean isApplicable(Cart cart) { return cart.getSubtotal() >= minCartValue; }

    @Override
    public double calculateDiscount(Cart cart) { return flatAmount; }
}
