// Wins when the whole column of the last move holds the player's symbol
public class ColumnWinningStrategy implements WinningStrategy {

    @Override
    public boolean isWinningMove(Board board, Move move) {
        Symbol symbol = move.getPlayer().getSymbol();
        for (int row = 0; row < board.getSize(); row++) {
            if (board.getSymbolAt(row, move.getCol()) != symbol) {
                return false;
            }
        }
        return true;
    }
}
