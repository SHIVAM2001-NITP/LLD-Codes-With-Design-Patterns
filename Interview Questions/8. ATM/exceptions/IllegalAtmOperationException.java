package exceptions;

// A state rejects an operation that makes no sense right now (e.g. withdrawing before login)
public class IllegalAtmOperationException extends RuntimeException {
    public IllegalAtmOperationException(String message) {
        super(message);
    }
}
