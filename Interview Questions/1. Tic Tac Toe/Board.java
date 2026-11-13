// Knows the grid and nothing about the rules of winning
public class Board {
    private final int size;
    private final Symbol[][] cells;
    private int filledCells = 0;

    public Board(int size) {
        if (size < 3) {
            throw new IllegalArgumentException("Board size must be at least 3");
        }
        this.size = size;
        this.cells = new Symbol[size][size];
    }

    public int getSize() { return size; }

    public Symbol getSymbolAt(int row, int col) { return cells[row][col]; }

    public boolean isInside(int row, int col) {
        return row >= 0 && row < size && col >= 0 && col < size;
    }

    public boolean isEmpty(int row, int col) {
        return cells[row][col] == null;
    }

    public void place(Move move) {
        cells[move.getRow()][move.getCol()] = move.getPlayer().getSymbol();
        filledCells++;
    }

    public boolean isFull() {
        return filledCells == size * size;
    }

    public void print() {
        for (int r = 0; r < size; r++) {
            StringBuilder line = new StringBuilder();
            for (int c = 0; c < size; c++) {
                line.append(cells[r][c] == null ? "." : cells[r][c].name());
                if (c < size - 1) {
                    line.append(" | ");
                }
            }
            System.out.println(line);
        }
    }
}
