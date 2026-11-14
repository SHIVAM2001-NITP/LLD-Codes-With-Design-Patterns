package entities;

import enums.TransactionType;

import java.time.Instant;

// An immutable audit trail entry: what moved, where, by how much, and when
public class Transaction {
    private final Product product;
    private final String warehouseId;
    private final TransactionType type;
    private final int quantity;
    private final Instant timestamp;

    public Transaction(Product product, String warehouseId, TransactionType type, int quantity, Instant timestamp) {
        this.product = product;
        this.warehouseId = warehouseId;
        this.type = type;
        this.quantity = quantity;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return timestamp + " [" + warehouseId + "] " + type + " " + quantity + "x " + product.getName();
    }
}
