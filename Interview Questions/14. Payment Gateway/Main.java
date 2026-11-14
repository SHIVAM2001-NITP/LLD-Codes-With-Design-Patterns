import entities.PaymentRequest;
import entities.Transaction;
import enums.PaymentMethod;
import exceptions.RefundNotAllowedException;
import exceptions.UnsupportedPaymentMethodException;
import observer.AuditLogObserver;
import observer.WebhookNotifier;
import provider.MockPaymentProvider;
import service.PaymentGateway;

public class Main {
    public static void main(String[] args) {

        MockPaymentProvider stripe = new MockPaymentProvider("Stripe", "4000-0000-0000-0002", "0000");
        MockPaymentProvider razorpay = new MockPaymentProvider("Razorpay", "0000");

        PaymentGateway gateway = new PaymentGateway();
        gateway.registerProvider(PaymentMethod.CREDIT_CARD, stripe);     // tried first
        gateway.registerProvider(PaymentMethod.CREDIT_CARD, razorpay);   // fallback
        gateway.addObserver(new WebhookNotifier());
        gateway.addObserver(new AuditLogObserver());

        System.out.println("=== 1. A normal payment (Stripe approves first try) ===");
        PaymentRequest goodCard = new PaymentRequest("ORDER-1", 1500.0, "INR", PaymentMethod.CREDIT_CARD, "4111-1111-1111-1111");
        Transaction t1 = gateway.charge(goodCard, "idem-key-1");
        System.out.println("Result: " + t1);

        System.out.println("\n=== 2. Stripe declines this card, Razorpay picks it up ===");
        PaymentRequest fallbackCard = new PaymentRequest("ORDER-2", 2500.0, "INR", PaymentMethod.CREDIT_CARD, "4000-0000-0000-0002");
        Transaction t2 = gateway.charge(fallbackCard, "idem-key-2");
        System.out.println("Result: " + t2);

        System.out.println("\n=== 3. Every provider in the chain declines ===");
        PaymentRequest badCard = new PaymentRequest("ORDER-3", 900.0, "INR", PaymentMethod.CREDIT_CARD, "0000");
        Transaction t3 = gateway.charge(badCard, "idem-key-3");
        System.out.println("Result: " + t3);

        System.out.println("\n=== 4. Retrying with the same idempotency key as #1 does not charge again ===");
        System.out.println("Stripe charge attempts before retry: " + stripe.getAttempts());
        Transaction t1Retry = gateway.charge(goodCard, "idem-key-1");
        System.out.println("Stripe charge attempts after retry: " + stripe.getAttempts() + " (unchanged)");
        System.out.println("Same transaction returned: " + (t1Retry == t1));

        System.out.println("\n=== 5. Refund the successful #1 transaction ===");
        gateway.refund(t1.getId(), t1.getAmount());
        System.out.println("Status now: " + t1.getStatus());

        System.out.println("\n-- Rejections --");
        try {
            gateway.refund(t1.getId(), t1.getAmount());     // already refunded
        } catch (RefundNotAllowedException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
        try {
            gateway.refund(t3.getId(), t3.getAmount());      // never succeeded
        } catch (RefundNotAllowedException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
        try {
            PaymentRequest wallet = new PaymentRequest("ORDER-4", 100.0, "INR", PaymentMethod.WALLET, "wallet-1");
            gateway.charge(wallet, "idem-key-4");             // no provider registered for WALLET
        } catch (UnsupportedPaymentMethodException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
    }
}
