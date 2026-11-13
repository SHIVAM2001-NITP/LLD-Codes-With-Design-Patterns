package strategy;

import entities.Board;
import entities.Move;
import enums.Symbol;

// Wins when the whole row of the last move holds the player's symbol
public class RowWinningStrategy implements WinningStrategy {

    @Override
    public boolean isWinningMove(Board board, Move move) {
        Symbol symbol = move.getPlayer().getSymbol();
        for (int col = 0; col < board.getSize(); col++) {
            if (board.getSymbolAt(move.getRow(), col) != symbol) {
                return false;
            }
        }
        return true;
    }
}
