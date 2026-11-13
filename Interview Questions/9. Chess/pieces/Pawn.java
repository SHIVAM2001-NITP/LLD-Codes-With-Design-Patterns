package pieces;

import entities.Board;
import entities.Position;
import enums.Color;

public class Pawn extends Piece {

    public Pawn(Color color) { super(color); }

    @Override
    public boolean canMove(Board board, Position from, Position to) {
        int direction = color == Color.WHITE ? 1 : -1;
        int startRow = color == Color.WHITE ? 1 : 6;
        int dr = to.getRow() - from.getRow();
        int dc = to.getCol() - from.getCol();
        boolean destinationEmpty = board.getPiece(to) == null;

        if (dc == 0 && dr == direction && destinationEmpty) {
            return true;                                                   // one step forward
        }
        if (dc == 0 && dr == 2 * direction && from.getRow() == startRow && destinationEmpty
                && board.getPiece(new Position(from.getRow() + direction, from.getCol())) == null) {
            return true;                                                   // two steps from the start row
        }
        // diagonal capture only
        return Math.abs(dc) == 1 && dr == direction && !destinationEmpty && board.getPiece(to).getColor() != color;
    }

    @Override
    public char symbol() { return color == Color.WHITE ? 'P' : 'p'; }
}
