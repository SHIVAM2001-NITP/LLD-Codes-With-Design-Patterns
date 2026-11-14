package exceptions;

public class BookingExpiredException extends RuntimeException {
    public BookingExpiredException(String message) {
        super(message);
    }
}
