package strategy;

// Strategy: one way to pay. Returns true if the payment went through.
public interface PaymentStrategy {
    boolean pay(double amount);
}
