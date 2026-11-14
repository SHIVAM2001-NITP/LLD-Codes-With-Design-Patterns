package strategy;

public class UpiPayment implements PaymentStrategy {
    private final String upiId;

    public UpiPayment(String upiId) { this.upiId = upiId; }

    @Override
    public boolean pay(double amount) {
        System.out.println("  [UPI] charged Rs." + amount + " to " + upiId);
        return true;
    }
}
