package strategy;

import entities.StockEntry;

// Strategy: once a product is low on stock, how much should be reordered?
public interface ReplenishmentStrategy {
    int quantityToReorder(StockEntry entry);
}
