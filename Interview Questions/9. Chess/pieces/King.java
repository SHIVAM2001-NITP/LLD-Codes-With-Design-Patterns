package pieces;

import entities.Board;
import entities.Position;
import enums.Color;

public class King extends Piece {

    public King(Color color) { super(color); }

    @Override
    public boolean canMove(Board board, Position from, Position to) {
        int dr = Math.abs(to.getRow() - from.getRow());
        int dc = Math.abs(to.getCol() - from.getCol());
        return dr <= 1 && dc <= 1 && (dr != 0 || dc != 0) && Board.notOwnPiece(board, this, to);
    }

    @Override
    public char symbol() { return color == Color.WHITE ? 'K' : 'k'; }
}
