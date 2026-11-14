import entities.Cart;
import entities.Coupon;
import entities.Product;
import exceptions.CouponExpiredException;
import exceptions.CouponNotApplicableException;
import exceptions.CouponUsageLimitExceededException;
import service.CouponManager;
import strategy.BogoDiscountStrategy;
import strategy.CategoryDiscountStrategy;
import strategy.FlatDiscountStrategy;
import strategy.PercentageDiscountStrategy;
import util.ManualClock;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        Product tshirt = new Product("P1", "T-Shirt", "APPAREL", 500);
        Product jeans = new Product("P2", "Jeans", "APPAREL", 1500);
        Product phone = new Product("P3", "Phone", "ELECTRONICS", 20000);

        ManualClock clock = new ManualClock(Instant.parse("2026-01-01T00:00:00Z"));
        CouponManager manager = new CouponManager(clock);
        manager.register(new Coupon("FLAT200", "Flat 200 off on 1000+", new FlatDiscountStrategy(1000, 200),
                LocalDate.of(2026, 1, 31), 100));
        manager.register(new Coupon("SAVE10", "10% off, up to 1000", new PercentageDiscountStrategy(500, 10, 1000),
                LocalDate.of(2026, 1, 31), 100));
        manager.register(new Coupon("APPAREL20", "20% off apparel, up to 300", new CategoryDiscountStrategy("APPAREL", 20, 300),
                LocalDate.of(2026, 1, 31), 1));
        manager.register(new Coupon("BOGOAPPAREL", "Buy 1 get 1 free on apparel", new BogoDiscountStrategy("APPAREL"),
                LocalDate.of(2026, 1, 31), 100));
        manager.register(new Coupon("OLD50", "Expired coupon", new FlatDiscountStrategy(0, 50),
                LocalDate.of(2025, 12, 31), 100));

        System.out.println("=== Cart: 2 T-Shirts + 1 Jeans + 1 Phone (subtotal 23000) ===");
        Cart cart = new Cart();
        cart.addItem(tshirt, 2);
        cart.addItem(jeans, 1);
        cart.addItem(phone, 1);
        System.out.println("Subtotal: " + cart.getSubtotal());

        System.out.println("\n-- Apply FLAT200 --");
        double d1 = manager.applyCoupon(cart, "FLAT200");
        System.out.println("Discount: " + d1 + ", final amount: " + cart.getFinalAmount());
        cart.removeCoupon();

        System.out.println("\n-- Apply SAVE10 (capped at 1000, 10% of 23000 would be 2300) --");
        double d2 = manager.applyCoupon(cart, "SAVE10");
        System.out.println("Discount: " + d2 + ", final amount: " + cart.getFinalAmount());
        cart.removeCoupon();

        System.out.println("\n-- Apply APPAREL20 (20% of 2500 apparel subtotal = 500, capped at 300) --");
        double d3 = manager.applyCoupon(cart, "APPAREL20");
        System.out.println("Discount: " + d3 + ", final amount: " + cart.getFinalAmount());
        cart.removeCoupon();

        System.out.println("\n-- Apply BOGOAPPAREL (3 apparel units -> cheapest 1 free) --");
        double d4 = manager.applyCoupon(cart, "BOGOAPPAREL");
        System.out.println("Discount: " + d4 + ", final amount: " + cart.getFinalAmount());
        cart.removeCoupon();

        System.out.println("\n=== Auto-pick the best coupon for this cart ===");
        Optional<Coupon> best = manager.findBestCoupon(cart);
        best.ifPresent(c -> System.out.println("Best coupon: " + c.getCode() + " (" + c.getDescription() + ")"));

        System.out.println("\n-- Rejections --");
        try {
            manager.applyCoupon(cart, "OLD50");
        } catch (CouponExpiredException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
        try {
            Cart smallCart = new Cart();
            smallCart.addItem(tshirt, 1);
            manager.applyCoupon(smallCart, "FLAT200");     // subtotal 500 < min 1000
        } catch (CouponNotApplicableException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
        try {
            manager.applyCoupon(cart, "APPAREL20");        // usage limit is 1, already used once above
        } catch (CouponUsageLimitExceededException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
        try {
            manager.applyCoupon(cart, "UNKNOWN10");
        } catch (CouponNotApplicableException e) {
            System.out.println("Rejected: " + e.getMessage());
        }

        System.out.println("\n-- A coupon that is valid today expires later --");
        clock.advance(Duration.ofDays(45));
        try {
            manager.applyCoupon(cart, "FLAT200");
        } catch (CouponExpiredException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
    }
}
