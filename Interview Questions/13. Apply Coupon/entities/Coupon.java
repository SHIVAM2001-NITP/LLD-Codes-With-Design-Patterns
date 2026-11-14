package entities;

import strategy.DiscountStrategy;

import java.time.Clock;
import java.time.LocalDate;

public class Coupon {
    private final String code;
    private final String description;
    private final DiscountStrategy strategy;
    private final LocalDate expiryDate;
    private final int usageLimit;
    private int usedCount = 0;

    public Coupon(String code, String description, DiscountStrategy strategy, LocalDate expiryDate, int usageLimit) {
        this.code = code;
        this.description = description;
        this.strategy = strategy;
        this.expiryDate = expiryDate;
        this.usageLimit = usageLimit;
    }

    public String getCode() { return code; }

    public String getDescription() { return description; }

    public DiscountStrategy getStrategy() { return strategy; }

    public boolean isExpired(Clock clock) { return LocalDate.now(clock).isAfter(expiryDate); }

    public boolean hasUsesLeft() { return usedCount < usageLimit; }

    public void recordUse() { usedCount++; }

    public int getUsedCount() { return usedCount; }
}
