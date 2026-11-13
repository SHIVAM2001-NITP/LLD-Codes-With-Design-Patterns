package strategy;

import entities.Board;
import entities.Move;

// Strategy: one way of winning. New rules are new classes (Open/Closed).
public interface WinningStrategy {
    boolean isWinningMove(Board board, Move move);
}
