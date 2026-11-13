package entities;

import enums.Color;
import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;

// The 8x8 grid and the operations every piece needs from it: what is on a square, and whether
// the squares strictly between two aligned squares are all empty (for sliding pieces).
public class Board {
    private final Piece[][] grid = new Piece[8][8];

    public Board() {
        setupBackRank(0, Color.WHITE);
        setupBackRank(7, Color.BLACK);
        for (int col = 0; col < 8; col++) {
            grid[1][col] = new Pawn(Color.WHITE);
            grid[6][col] = new Pawn(Color.BLACK);
        }
    }

    private void setupBackRank(int row, Color color) {
        grid[row][0] = new Rook(color);
        grid[row][1] = new Knight(color);
        grid[row][2] = new Bishop(color);
        grid[row][3] = new Queen(color);
        grid[row][4] = new King(color);
        grid[row][5] = new Bishop(color);
        grid[row][6] = new Knight(color);
        grid[row][7] = new Rook(color);
    }

    public Piece getPiece(Position p) { return grid[p.getRow()][p.getCol()]; }

    public void setPiece(Position p, Piece piece) { grid[p.getRow()][p.getCol()] = piece; }

    // True if every square strictly between from and to (on a straight or diagonal line) is empty
    public boolean isPathClear(Position from, Position to) {
        int dr = Integer.signum(to.getRow() - from.getRow());
        int dc = Integer.signum(to.getCol() - from.getCol());
        int row = from.getRow() + dr;
        int col = from.getCol() + dc;
        while (row != to.getRow() || col != to.getCol()) {
            if (grid[row][col] != null) {
                return false;
            }
            row += dr;
            col += dc;
        }
        return true;
    }

    public static boolean notOwnPiece(Board board, Piece mover, Position to) {
        Piece target = board.getPiece(to);
        return target == null || target.getColor() != mover.getColor();
    }

    public Position findKing(Color color) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = grid[row][col];
                if (piece instanceof King && piece.getColor() == color) {
                    return new Position(row, col);
                }
            }
        }
        throw new IllegalStateException("No " + color + " king on the board");
    }

    // Is `target` attacked by any piece of `byColor`? Used both for check detection and to
    // forbid a king from moving next to an enemy king.
    public boolean isSquareAttacked(Position target, Color byColor) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = grid[row][col];
                if (piece != null && piece.getColor() == byColor && piece.canMove(this, new Position(row, col), target)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void print() {
        for (int row = 7; row >= 0; row--) {
            StringBuilder line = new StringBuilder((row + 1) + " ");
            for (int col = 0; col < 8; col++) {
                Piece piece = grid[row][col];
                line.append(piece == null ? '.' : piece.symbol()).append(' ');
            }
            System.out.println(line);
        }
        System.out.println("  a b c d e f g h");
    }
}
