package pieces;

import entities.Board;
import entities.Position;

public class Knight extends Piece {

    public Knight(enums.Color color) { super(color); }

    @Override
    public boolean canMove(Board board, Position from, Position to) {
        int dr = Math.abs(to.getRow() - from.getRow());
        int dc = Math.abs(to.getCol() - from.getCol());
        return (dr == 2 && dc == 1 || dr == 1 && dc == 2) && Board.notOwnPiece(board, this, to);
    }

    @Override
    public char symbol() { return color == enums.Color.WHITE ? 'N' : 'n'; }
}
