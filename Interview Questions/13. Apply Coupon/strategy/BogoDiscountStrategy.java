package strategy;

import entities.Cart;
import entities.CartItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// "Buy one, get one free" on a category: for every 2 units in the category, the cheaper
// unit of the pair is free. Needs at least 2 units in the category to apply at all.
public class BogoDiscountStrategy implements DiscountStrategy {
    private final String category;

    public BogoDiscountStrategy(String category) {
        this.category = category;
    }

    @Override
    public boolean isApplicable(Cart cart) { return unitCount(cart) >= 2; }

    @Override
    public double calculateDiscount(Cart cart) {
        List<Double> unitPrices = new ArrayList<>();
        for (CartItem item : cart.getItemsInCategory(category)) {
            for (int i = 0; i < item.getQuantity(); i++) {
                unitPrices.add(item.getProduct().getPrice());
            }
        }
        Collections.sort(unitPrices);                     // cheapest units are the ones made free
        int freeCount = unitPrices.size() / 2;
        double discount = 0;
        for (int i = 0; i < freeCount; i++) {
            discount += unitPrices.get(i);
        }
        return discount;
    }

    private int unitCount(Cart cart) {
        int count = 0;
        for (CartItem item : cart.getItemsInCategory(category)) {
            count += item.getQuantity();
        }
        return count;
    }
}
