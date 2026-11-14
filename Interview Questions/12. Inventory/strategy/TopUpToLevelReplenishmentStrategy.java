package strategy;

import entities.StockEntry;

// Reorders just enough to bring available stock back up to a target level
public class TopUpToLevelReplenishmentStrategy implements ReplenishmentStrategy {
    private final int targetLevel;

    public TopUpToLevelReplenishmentStrategy(int targetLevel) {
        this.targetLevel = targetLevel;
    }

    @Override
    public int quantityToReorder(StockEntry entry) {
        return Math.max(0, targetLevel - entry.getAvailable());
    }
}
