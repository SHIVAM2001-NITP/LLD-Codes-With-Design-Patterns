package exceptions;

// Thrown when a move is outside the board, on a taken cell, or made after the game ended
public class InvalidMoveException extends RuntimeException {
    public InvalidMoveException(String message) {
        super(message);
    }
}
