package enums;

public enum TransactionType {
    INBOUND,     // stock received
    OUTBOUND,    // stock sold/shipped
    RESERVED,    // held for an order, not yet shipped
    RELEASED,    // a reservation cancelled
    ADJUSTMENT   // manual correction (stock take, damage, ...)
}
