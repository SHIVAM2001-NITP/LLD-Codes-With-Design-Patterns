package strategy;

import entities.Cart;

// Strategy: does this coupon apply to this cart, and if so, how much does it take off?
public interface DiscountStrategy {
    boolean isApplicable(Cart cart);

    double calculateDiscount(Cart cart);
}
