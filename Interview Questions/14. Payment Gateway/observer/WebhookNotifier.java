package observer;

import entities.Transaction;

// Simulates telling the merchant's backend what happened, the way a real gateway would
public class WebhookNotifier implements PaymentObserver {

    @Override
    public void onSuccess(Transaction transaction) {
        System.out.println("  [Webhook] payment.success -> " + transaction);
    }

    @Override
    public void onFailure(Transaction transaction) {
        System.out.println("  [Webhook] payment.failed -> " + transaction);
    }

    @Override
    public void onRefund(Transaction transaction) {
        System.out.println("  [Webhook] payment.refunded -> " + transaction);
    }
}
