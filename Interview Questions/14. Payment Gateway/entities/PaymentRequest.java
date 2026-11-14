package entities;

import enums.PaymentMethod;

// What the merchant is asking to charge. `instrumentId` is the card number / UPI id / wallet
// id - generic on purpose, since the gateway never needs to know its exact shape.
public class PaymentRequest {
    private final String orderId;
    private final double amount;
    private final String currency;
    private final PaymentMethod method;
    private final String instrumentId;

    public PaymentRequest(String orderId, double amount, String currency, PaymentMethod method, String instrumentId) {
        this.orderId = orderId;
        this.amount = amount;
        this.currency = currency;
        this.method = method;
        this.instrumentId = instrumentId;
    }

    public String getOrderId() { return orderId; }

    public double getAmount() { return amount; }

    public String getCurrency() { return currency; }

    public PaymentMethod getMethod() { return method; }

    public String getInstrumentId() { return instrumentId; }
}
