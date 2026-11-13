package pieces;

import entities.Board;
import entities.Position;

public class Queen extends Piece {

    public Queen(enums.Color color) { super(color); }

    @Override
    public boolean canMove(Board board, Position from, Position to) {
        boolean straight = from.getRow() == to.getRow() || from.getCol() == to.getCol();
        boolean diagonal = Math.abs(to.getRow() - from.getRow()) == Math.abs(to.getCol() - from.getCol());
        return (straight || diagonal) && !from.equals(to)
                && board.isPathClear(from, to) && Board.notOwnPiece(board, this, to);
    }

    @Override
    public char symbol() { return color == enums.Color.WHITE ? 'Q' : 'q'; }
}
