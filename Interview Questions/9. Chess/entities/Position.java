package entities;

// (row, col), both 0..7. row 0 is White's back rank, row 7 is Black's back rank.
public class Position {
    private final int row;
    private final int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    // Parses algebraic squares like "e2" -> col 4, row 1
    public static Position at(String square) {
        int col = square.charAt(0) - 'a';
        int row = square.charAt(1) - '1';
        return new Position(row, col);
    }

    public int getRow() { return row; }

    public int getCol() { return col; }

    public boolean isOnBoard() { return row >= 0 && row < 8 && col >= 0 && col < 8; }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Position)) return false;
        Position p = (Position) o;
        return row == p.row && col == p.col;
    }

    @Override
    public int hashCode() { return row * 8 + col; }

    @Override
    public String toString() { return "" + (char) ('a' + col) + (row + 1); }
}
