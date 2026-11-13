import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        // ---------- Scenario A: small board, scripted dice ----------
        System.out.println("=== Scenario A: 20 cells, scripted dice ===");
        List<BoardEntity> small = new ArrayList<>();
        small.add(new Ladder(3, 12));
        small.add(new Snake(17, 4));

        Game gameA = new Game(
                new Board(20, small),
                new FixedDice(3, 6, 5, 6, 6, 6, 6, 3, 4),
                Arrays.asList(new Player("Alice"), new Player("Bob")));
        play(gameA);

        // ---------- Scenario B: classic 100 cells, seeded random dice ----------
        System.out.println("\n=== Scenario B: 100 cells, random dice (seed 42) ===");
        List<BoardEntity> classic = new ArrayList<>();
        classic.add(new Snake(17, 7));
        classic.add(new Snake(54, 34));
        classic.add(new Snake(62, 19));
        classic.add(new Snake(98, 79));
        classic.add(new Ladder(3, 22));
        classic.add(new Ladder(5, 8));
        classic.add(new Ladder(11, 26));
        classic.add(new Ladder(20, 29));

        Game gameB = new Game(
                new Board(100, classic),
                new RandomDice(1, new Random(42)),
                Arrays.asList(new Player("Alice"), new Player("Bob"), new Player("Carol")));
        int turns = play(gameB);
        System.out.println("Game finished in " + turns + " turns");

        // ---------- Bad boards are rejected ----------
        System.out.println("\n=== Invalid boards ===");
        try {
            new Snake(5, 9);
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
        try {
            List<BoardEntity> clash = new ArrayList<>();
            clash.add(new Ladder(3, 12));
            clash.add(new Snake(3, 1));
            new Board(20, clash);
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
    }

    private static int play(Game game) {
        int turns = 0;
        while (!game.isOver()) {
            System.out.println(game.playTurn());
            turns++;
        }
        System.out.println("Winner: " + game.getWinner().getName());
        return turns;
    }
}
