import entities.Board;
import entities.Move;
import entities.Position;
import enums.GameStatus;
import exceptions.InvalidMoveException;
import service.Game;

public class Main {
    public static void main(String[] args) {

        System.out.println("=== A short opening (Ruy Lopez-ish) ===");
        Game game = new Game();
        move(game, "e2", "e4");
        move(game, "e7", "e5");
        move(game, "g1", "f3");
        move(game, "b8", "c6");
        move(game, "f1", "b5");
        game.getBoard().print();
        System.out.println("Status: " + game.getStatus() + ", turn: " + game.getCurrentTurn());

        System.out.println("\n-- Invalid moves --");
        try {
            move(game, "a7", "a4");                 // wrong piece shape (pawn jumping 3)
        } catch (InvalidMoveException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
        try {
            move(game, "e4", "e5");                  // not Black's turn's piece, but it's Black to move: White piece selected
        } catch (InvalidMoveException e) {
            System.out.println("Rejected: " + e.getMessage());
        }

        System.out.println("\n=== Fool's mate: fastest possible checkmate ===");
        Game fools = new Game();
        move(fools, "f2", "f3");
        move(fools, "e7", "e5");
        move(fools, "g2", "g4");
        Move mate = move(fools, "d8", "h4");
        System.out.println("Last move: " + mate);
        fools.getBoard().print();
        System.out.println("Status: " + fools.getStatus() + " (White has no legal move left)");

        System.out.println("\n-- No move is possible once the game is over --");
        try {
            move(fools, "e1", "e2");
        } catch (InvalidMoveException e) {
            System.out.println("Rejected: " + e.getMessage());
        }

        System.out.println("\n=== A move that ignores check, or exposes your own king, is rejected ===");
        Game check = new Game();
        move(check, "e2", "e4");
        move(check, "d7", "d5");
        move(check, "f1", "b5");    // Bb5+ : nothing blocks the b5-e8 diagonal yet, Black's king is in check
        System.out.println("Black is in " + check.getStatus() + " and must respond to it");
        try {
            move(check, "g8", "f6");    // a legal knight move, but it ignores the check
        } catch (InvalidMoveException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
        move(check, "b8", "c6");        // this blocks the check: now legal
        System.out.println("Nc6 blocks it -> status is back to " + check.getStatus());
    }

    private static Move move(Game game, String from, String to) {
        Move move = game.makeMove(Position.at(from), Position.at(to));
        System.out.println(move + "  [" + game.getStatus() + "]");
        return move;
    }
}
