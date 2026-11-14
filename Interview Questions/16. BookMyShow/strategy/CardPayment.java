package strategy;

// Declines when the card has an insufficient limit, to show the failure path
public class CardPayment implements PaymentStrategy {
    private final String cardNumber;
    private final double limit;

    public CardPayment(String cardNumber, double limit) {
        this.cardNumber = cardNumber;
        this.limit = limit;
    }

    @Override
    public boolean pay(double amount) {
        if (amount > limit) {
            System.out.println("  [Card " + cardNumber + "] declined: Rs." + amount + " exceeds limit " + limit);
            return false;
        }
        System.out.println("  [Card " + cardNumber + "] charged Rs." + amount);
        return true;
    }
}
