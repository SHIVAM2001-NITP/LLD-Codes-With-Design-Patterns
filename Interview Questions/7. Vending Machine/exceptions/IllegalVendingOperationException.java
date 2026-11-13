package exceptions;

// A state rejects an operation that makes no sense right now (e.g. inserting a coin before selecting)
public class IllegalVendingOperationException extends RuntimeException {
    public IllegalVendingOperationException(String message) {
        super(message);
    }
}
