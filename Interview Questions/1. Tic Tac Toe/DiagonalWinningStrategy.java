// Wins when a whole diagonal holds the player's symbol (only checked if the move is on a diagonal)
public class DiagonalWinningStrategy implements WinningStrategy {

    @Override
    public boolean isWinningMove(Board board, Move move) {
        int n = board.getSize();
        Symbol symbol = move.getPlayer().getSymbol();

        if (move.getRow() == move.getCol() && allMatch(board, symbol, true)) {
            return true;                                   // main diagonal
        }
        return move.getRow() + move.getCol() == n - 1 && allMatch(board, symbol, false);   // anti diagonal
    }

    private boolean allMatch(Board board, Symbol symbol, boolean mainDiagonal) {
        int n = board.getSize();
        for (int i = 0; i < n; i++) {
            int col = mainDiagonal ? i : n - 1 - i;
            if (board.getSymbolAt(i, col) != symbol) {
                return false;
            }
        }
        return true;
    }
}
