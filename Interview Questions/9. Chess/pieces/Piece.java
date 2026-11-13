package pieces;

import entities.Board;
import entities.Position;
import enums.Color;

// A piece only knows its own movement pattern. It does not know whether a move would leave
// its own king in check - that rule lives one level up, in Game, where both kings are visible.
public abstract class Piece {
    protected final Color color;
    protected boolean hasMoved = false;

    protected Piece(Color color) {
        this.color = color;
    }

    public Color getColor() { return color; }

    public boolean hasMoved() { return hasMoved; }

    public void markMoved() { hasMoved = true; }

    // Is (from -> to) a shape this piece can make, on an otherwise empty board, given what
    // (if anything) sits on `to`? Sliding pieces also check that the path between is clear.
    public abstract boolean canMove(Board board, Position from, Position to);

    public abstract char symbol();   // uppercase = White, lowercase = Black

    @Override
    public String toString() { return color + " " + getClass().getSimpleName(); }
}
