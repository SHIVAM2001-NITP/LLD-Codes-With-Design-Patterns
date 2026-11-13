package entities;

import pieces.Piece;

public class Move {
    private final Position from;
    private final Position to;
    private final Piece piece;
    private final Piece captured;

    public Move(Position from, Position to, Piece piece, Piece captured) {
        this.from = from;
        this.to = to;
        this.piece = piece;
        this.captured = captured;
    }

    public Position getFrom() { return from; }

    public Position getTo() { return to; }

    @Override
    public String toString() {
        return piece.symbol() + " " + from + "-" + to + (captured != null ? " (captures " + captured.symbol() + ")" : "");
    }
}
