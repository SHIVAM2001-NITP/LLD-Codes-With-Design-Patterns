package pieces;

import entities.Board;
import entities.Position;

public class Rook extends Piece {

    public Rook(enums.Color color) { super(color); }

    @Override
    public boolean canMove(Board board, Position from, Position to) {
        boolean straight = from.getRow() == to.getRow() || from.getCol() == to.getCol();
        return straight && !from.equals(to) && board.isPathClear(from, to) && Board.notOwnPiece(board, this, to);
    }

    @Override
    public char symbol() { return color == enums.Color.WHITE ? 'R' : 'r'; }
}
