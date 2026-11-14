package provider;

import entities.PaymentRequest;

// Strategy: one payment processor (Stripe, Razorpay, ...). The gateway only ever calls
// these two methods and never needs to know which real provider it is talking to.
public interface PaymentProvider {
    String getName();

    PaymentResult charge(PaymentRequest request);

    PaymentResult refund(String transactionId, double amount);
}
