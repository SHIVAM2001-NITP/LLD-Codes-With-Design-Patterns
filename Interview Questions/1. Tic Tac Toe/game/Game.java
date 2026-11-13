package game;

import entities.Board;
import entities.Move;
import entities.Player;
import enums.GameStatus;
import exceptions.InvalidMoveException;
import strategy.ColumnWinningStrategy;
import strategy.DiagonalWinningStrategy;
import strategy.RowWinningStrategy;
import strategy.WinningStrategy;

import java.util.ArrayList;
import java.util.List;

// Orchestrates turns. It delegates "who has won" to the strategies and "what is on the grid" to the board.
public class Game {
    private final Board board;
    private final List<Player> players;
    private final List<WinningStrategy> winningStrategies;
    private int currentPlayerIndex = 0;
    private GameStatus status = GameStatus.IN_PROGRESS;
    private Player winner;

    private Game(Board board, List<Player> players, List<WinningStrategy> winningStrategies) {
        this.board = board;
        this.players = players;
        this.winningStrategies = winningStrategies;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Player getCurrentPlayer() { return players.get(currentPlayerIndex); }

    public GameStatus getStatus() { return status; }

    public Player getWinner() { return winner; }

    public Board getBoard() { return board; }

    // The current player plays on (row, col); returns the status after the move
    public GameStatus makeMove(int row, int col) {
        if (status != GameStatus.IN_PROGRESS) {
            throw new InvalidMoveException("The game is already over");
        }
        if (!board.isInside(row, col)) {
            throw new InvalidMoveException("Cell (" + row + "," + col + ") is outside the board");
        }
        if (!board.isEmpty(row, col)) {
            throw new InvalidMoveException("Cell (" + row + "," + col + ") is already taken");
        }

        Move move = new Move(getCurrentPlayer(), row, col);
        board.place(move);

        for (WinningStrategy strategy : winningStrategies) {
            if (strategy.isWinningMove(board, move)) {
                status = GameStatus.WON;
                winner = move.getPlayer();
                return status;
            }
        }
        if (board.isFull()) {
            status = GameStatus.DRAW;
            return status;
        }
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();   // next turn
        return status;
    }

    // Builder: configure board size, players and winning rules, then build()
    public static class Builder {
        private int boardSize = 3;
        private final List<Player> players = new ArrayList<>();
        private final List<WinningStrategy> strategies = new ArrayList<>();

        public Builder boardSize(int boardSize) {
            this.boardSize = boardSize;
            return this;
        }

        public Builder addPlayer(Player player) {
            players.add(player);
            return this;
        }

        public Builder addWinningStrategy(WinningStrategy strategy) {
            strategies.add(strategy);
            return this;
        }

        public Game build() {
            if (players.size() < 2) {
                throw new IllegalStateException("At least two players are needed");
            }
            if (strategies.isEmpty()) {          // sensible default rules
                strategies.add(new RowWinningStrategy());
                strategies.add(new ColumnWinningStrategy());
                strategies.add(new DiagonalWinningStrategy());
            }
            return new Game(new Board(boardSize), players, strategies);
        }
    }
}
