package observer;

import entities.Transaction;

// A second, independent subscriber: an internal audit trail
public class AuditLogObserver implements PaymentObserver {

    @Override
    public void onSuccess(Transaction transaction) {
        System.out.println("  [Audit] recorded SUCCESS for " + transaction.getId());
    }

    @Override
    public void onFailure(Transaction transaction) {
        System.out.println("  [Audit] recorded FAILED for " + transaction.getId());
    }

    @Override
    public void onRefund(Transaction transaction) {
        System.out.println("  [Audit] recorded REFUND for " + transaction.getId());
    }
}
