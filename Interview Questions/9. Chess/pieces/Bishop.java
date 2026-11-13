package pieces;

import entities.Board;
import entities.Position;

public class Bishop extends Piece {

    public Bishop(enums.Color color) { super(color); }

    @Override
    public boolean canMove(Board board, Position from, Position to) {
        boolean diagonal = Math.abs(to.getRow() - from.getRow()) == Math.abs(to.getCol() - from.getCol());
        return diagonal && !from.equals(to) && board.isPathClear(from, to) && Board.notOwnPiece(board, this, to);
    }

    @Override
    public char symbol() { return color == enums.Color.WHITE ? 'B' : 'b'; }
}
