package observer;

import entities.Transaction;

// Observer: each subscriber only reacts to the events it cares about
public interface PaymentObserver {
    default void onSuccess(Transaction transaction) { }

    default void onFailure(Transaction transaction) { }

    default void onRefund(Transaction transaction) { }
}
