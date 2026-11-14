package service;

import entities.PaymentRequest;
import entities.Transaction;
import enums.PaymentMethod;
import enums.TransactionStatus;
import exceptions.InvalidAmountException;
import exceptions.RefundNotAllowedException;
import exceptions.TransactionNotFoundException;
import exceptions.UnsupportedPaymentMethodException;
import observer.PaymentObserver;
import provider.PaymentProvider;
import provider.PaymentResult;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// The single entry point merchants call. It never talks to a card network itself: it routes
// each request to an ordered chain of providers for that payment method, falling back to the
// next one on a decline, and guarantees the same idempotency key is never charged twice.
public class PaymentGateway {
    private final Map<PaymentMethod, List<PaymentProvider>> providerChains = new EnumMap<>(PaymentMethod.class);
    private final Map<String, Transaction> transactionsById = new HashMap<>();
    private final Map<String, Transaction> transactionsByIdempotencyKey = new HashMap<>();
    private final Map<String, PaymentProvider> providerByTransactionId = new HashMap<>();
    private final List<PaymentObserver> observers = new ArrayList<>();
    private int counter = 0;

    public void registerProvider(PaymentMethod method, PaymentProvider provider) {
        providerChains.computeIfAbsent(method, k -> new ArrayList<>()).add(provider);
    }

    public void addObserver(PaymentObserver observer) { observers.add(observer); }

    public synchronized Transaction charge(PaymentRequest request, String idempotencyKey) {
        Transaction existing = transactionsByIdempotencyKey.get(idempotencyKey);
        if (existing != null) {
            System.out.println("  [Idempotency] " + idempotencyKey + " already processed -> returning " + existing.getId());
            return existing;
        }
        if (request.getAmount() <= 0) {
            throw new InvalidAmountException("Amount must be positive, got " + request.getAmount());
        }
        List<PaymentProvider> chain = providerChains.get(request.getMethod());
        if (chain == null || chain.isEmpty()) {
            throw new UnsupportedPaymentMethodException("No provider configured for " + request.getMethod());
        }

        Transaction transaction = new Transaction("TXN-" + (++counter), request.getOrderId(), request.getAmount(), request.getMethod());
        transactionsById.put(transaction.getId(), transaction);
        transactionsByIdempotencyKey.put(idempotencyKey, transaction);

        for (PaymentProvider provider : chain) {
            PaymentResult result = provider.charge(request);
            if (result.isSuccess()) {
                transaction.setStatus(TransactionStatus.SUCCESS);
                transaction.setProviderUsed(provider.getName());
                providerByTransactionId.put(transaction.getId(), provider);
                notifySuccess(transaction);
                return transaction;
            }
        }
        transaction.setStatus(TransactionStatus.FAILED);
        notifyFailure(transaction);
        return transaction;
    }

    public synchronized Transaction refund(String transactionId, double amount) {
        Transaction transaction = transactionsById.get(transactionId);
        if (transaction == null) {
            throw new TransactionNotFoundException("Unknown transaction: " + transactionId);
        }
        if (transaction.getStatus() != TransactionStatus.SUCCESS) {
            throw new RefundNotAllowedException("Cannot refund a transaction that is " + transaction.getStatus());
        }
        PaymentProvider provider = providerByTransactionId.get(transactionId);
        provider.refund(transactionId, amount);
        transaction.setStatus(TransactionStatus.REFUNDED);
        notifyRefund(transaction);
        return transaction;
    }

    private void notifySuccess(Transaction t) { for (PaymentObserver o : observers) o.onSuccess(t); }

    private void notifyFailure(Transaction t) { for (PaymentObserver o : observers) o.onFailure(t); }

    private void notifyRefund(Transaction t) { for (PaymentObserver o : observers) o.onRefund(t); }
}
