package entities;

import java.util.List;

public class Expense {
    private final String id;
    private final String description;
    private final User paidBy;
    private final double amount;
    private final List<Split> splits;

    public Expense(String id, String description, User paidBy, double amount, List<Split> splits) {
        this.id = id;
        this.description = description;
        this.paidBy = paidBy;
        this.amount = amount;
        this.splits = splits;
    }

    public String getId() { return id; }

    public User getPaidBy() { return paidBy; }

    public double getAmount() { return amount; }

    public List<Split> getSplits() { return splits; }

    @Override
    public String toString() {
        return id + " " + description + ": " + paidBy + " paid " + amount + " " + splits.stream()
                .map(s -> s.getUser() + "=" + String.format("%.2f", s.getAmount()))
                .reduce((a, b) -> a + ", " + b).orElse("");
    }
}
