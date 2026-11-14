package strategy;

import entities.StockEntry;

// Always reorder the same fixed amount
public class FixedQuantityReplenishmentStrategy implements ReplenishmentStrategy {
    private final int quantity;

    public FixedQuantityReplenishmentStrategy(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int quantityToReorder(StockEntry entry) { return quantity; }
}
