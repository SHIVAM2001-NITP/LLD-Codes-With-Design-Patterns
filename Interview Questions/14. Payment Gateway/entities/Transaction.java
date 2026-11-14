package entities;

import enums.PaymentMethod;
import enums.TransactionStatus;

public class Transaction {
    private final String id;
    private final String orderId;
    private final double amount;
    private final PaymentMethod method;
    private String providerUsed;
    private TransactionStatus status;

    public Transaction(String id, String orderId, double amount, PaymentMethod method) {
        this.id = id;
        this.orderId = orderId;
        this.amount = amount;
        this.method = method;
        this.status = TransactionStatus.PENDING;
    }

    public String getId() { return id; }

    public String getOrderId() { return orderId; }

    public double getAmount() { return amount; }

    public TransactionStatus getStatus() { return status; }

    public void setStatus(TransactionStatus status) { this.status = status; }

    public String getProviderUsed() { return providerUsed; }

    public void setProviderUsed(String providerUsed) { this.providerUsed = providerUsed; }

    @Override
    public String toString() {
        return id + " [order " + orderId + ", " + amount + ", " + status
                + (providerUsed != null ? " via " + providerUsed : "") + "]";
    }
}
