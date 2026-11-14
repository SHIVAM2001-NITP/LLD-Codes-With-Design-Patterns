package service;

import entities.Cart;
import entities.Coupon;
import exceptions.CouponExpiredException;
import exceptions.CouponNotApplicableException;
import exceptions.CouponUsageLimitExceededException;

import java.time.Clock;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

// Single entry point: register coupons, validate and apply one by code, or find the best
// applicable one automatically. Validity (expiry, usage, applicability) is always checked
// in the same order and in one place, so the three ways in can never disagree.
public class CouponManager {
    private final Map<String, Coupon> coupons = new HashMap<>();
    private final Clock clock;

    public CouponManager(Clock clock) {
        this.clock = clock;
    }

    public void register(Coupon coupon) {
        coupons.put(coupon.getCode(), coupon);
    }

    public synchronized double applyCoupon(Cart cart, String code) {
        Coupon coupon = coupons.get(code);
        if (coupon == null) {
            throw new CouponNotApplicableException("Unknown coupon code: " + code);
        }
        validate(coupon, cart);

        double discount = coupon.getStrategy().calculateDiscount(cart);
        coupon.recordUse();
        cart.applyDiscount(coupon.getCode(), discount);
        return discount;
    }

    // Tries every registered coupon and returns the one giving the biggest discount, if any applies
    public synchronized Optional<Coupon> findBestCoupon(Cart cart) {
        Coupon best = null;
        double bestDiscount = -1;
        for (Coupon coupon : coupons.values()) {
            if (isUsable(coupon, cart)) {
                double discount = coupon.getStrategy().calculateDiscount(cart);
                if (discount > bestDiscount) {
                    best = coupon;
                    bestDiscount = discount;
                }
            }
        }
        return Optional.ofNullable(best);
    }

    private void validate(Coupon coupon, Cart cart) {
        if (coupon.isExpired(clock)) {
            throw new CouponExpiredException(coupon.getCode() + " expired");
        }
        if (!coupon.hasUsesLeft()) {
            throw new CouponUsageLimitExceededException(coupon.getCode() + " has reached its usage limit");
        }
        if (!coupon.getStrategy().isApplicable(cart)) {
            throw new CouponNotApplicableException(coupon.getCode() + " does not apply to this cart");
        }
    }

    private boolean isUsable(Coupon coupon, Cart cart) {
        return !coupon.isExpired(clock) && coupon.hasUsesLeft() && coupon.getStrategy().isApplicable(cart);
    }
}
