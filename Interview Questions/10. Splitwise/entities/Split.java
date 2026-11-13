package entities;

// How much one user's share of an expense comes to. Immutable once computed by a SplitStrategy.
public class Split {
    private final User user;
    private final double amount;

    public Split(User user, double amount) {
        this.user = user;
        this.amount = amount;
    }

    public User getUser() { return user; }

    public double getAmount() { return amount; }
}
