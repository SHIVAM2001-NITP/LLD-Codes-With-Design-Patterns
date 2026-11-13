import entities.Player;
import enums.GameStatus;
import enums.Symbol;
import exceptions.InvalidMoveException;
import game.Game;

public class Main {
    public static void main(String[] args) {

        Player alice = new Player("Alice", Symbol.X);
        Player bob = new Player("Bob", Symbol.O);

        // ---------- Game 1: Alice wins with the top row ----------
        System.out.println("=== Game 1: win by row ===");
        Game game1 = Game.builder().addPlayer(alice).addPlayer(bob).build();
        play(game1, new int[][]{{0, 0}, {1, 0}, {0, 1}, {1, 1}, {0, 2}});

        // ---------- Game 2: draw ----------
        System.out.println("\n=== Game 2: draw ===");
        Game game2 = Game.builder().addPlayer(alice).addPlayer(bob).build();
        play(game2, new int[][]{{0, 0}, {0, 1}, {0, 2}, {1, 1}, {1, 0}, {1, 2}, {2, 1}, {2, 0}, {2, 2}});

        // ---------- Game 3: 4x4 board, Bob wins on the anti-diagonal ----------
        System.out.println("\n=== Game 3: 4x4 board, win by diagonal ===");
        Game game3 = Game.builder().boardSize(4).addPlayer(alice).addPlayer(bob).build();
        play(game3, new int[][]{{0, 0}, {0, 3}, {0, 1}, {1, 2}, {3, 3}, {2, 1}, {1, 1}, {3, 0}});

        // ---------- Invalid moves are rejected, the turn does not change ----------
        System.out.println("\n=== Invalid moves ===");
        Game game4 = Game.builder().addPlayer(alice).addPlayer(bob).build();
        game4.makeMove(1, 1);
        tryMove(game4, 1, 1);   // taken cell
        tryMove(game4, 3, 0);   // outside the board
        System.out.println("Still " + game4.getCurrentPlayer() + "'s turn");
    }

    private static void play(Game game, int[][] moves) {
        for (int[] move : moves) {
            Player player = game.getCurrentPlayer();
            GameStatus status = game.makeMove(move[0], move[1]);
            System.out.println(player + " plays (" + move[0] + "," + move[1] + ") -> " + status);
        }
        game.getBoard().print();
        if (game.getStatus() == GameStatus.WON) {
            System.out.println("Winner: " + game.getWinner());
        } else if (game.getStatus() == GameStatus.DRAW) {
            System.out.println("It is a draw");
        }
    }

    private static void tryMove(Game game, int row, int col) {
        try {
            game.makeMove(row, col);
        } catch (InvalidMoveException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
    }
}
