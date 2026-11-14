import entities.Team;
import enums.ExtraType;
import enums.MatchFormat;
import exceptions.InvalidDeliveryException;
import observer.CommentaryObserver;
import observer.ScoreCardObserver;
import service.CricketMatch;

public class Main {
    public static void main(String[] args) {

        Team india = new Team("India");
        Team australia = new Team("Australia");

        // A short 2-over-a-side match so the whole innings fits in one demo
        System.out.println("=== India vs Australia, 2 overs a side ===");
        CricketMatch match = new CricketMatch(india, australia, MatchFormat.T20, 2);
        match.addObserver(new ScoreCardObserver());
        match.addObserver(new CommentaryObserver());
        match.start();

        System.out.println("\n-- India bat first --");
        bowl(match, "Starc", "Rohit", 4, ExtraType.NONE, false);
        bowl(match, "Starc", "Rohit", 1, ExtraType.NONE, false);
        bowl(match, "Starc", "Kohli", 0, ExtraType.WIDE, false);
        bowl(match, "Starc", "Kohli", 6, ExtraType.NONE, false);
        bowl(match, "Starc", "Kohli", 0, ExtraType.NONE, true);
        bowl(match, "Starc", "Pant", 1, ExtraType.NONE, false);
        bowl(match, "Starc", "Rohit", 2, ExtraType.NONE, false);

        try {
            bowl(match, "Starc", "Rohit", 1, ExtraType.NONE, false);   // same bowler, next over: not allowed
        } catch (InvalidDeliveryException e) {
            System.out.println("Rejected: " + e.getMessage());
        }

        bowl(match, "Cummins", "Rohit", 4, ExtraType.NONE, false);
        bowl(match, "Cummins", "Rohit", 4, ExtraType.NONE, false);
        bowl(match, "Cummins", "Rohit", 1, ExtraType.LEG_BYE, false);
        bowl(match, "Cummins", "Pant", 6, ExtraType.NONE, false);
        bowl(match, "Cummins", "Pant", 4, ExtraType.NONE, false);
        bowl(match, "Cummins", "Pant", 1, ExtraType.NONE, false);

        System.out.println("\n-- Innings 1 done, Australia need " + match.getTarget() + " to win --");

        System.out.println("\n-- Australia chase --");
        bowl(match, "Bumrah", "Warner", 6, ExtraType.NONE, false);
        bowl(match, "Bumrah", "Warner", 6, ExtraType.NONE, false);
        bowl(match, "Bumrah", "Warner", 6, ExtraType.NONE, false);
        bowl(match, "Bumrah", "Warner", 6, ExtraType.NONE, false);
        bowl(match, "Bumrah", "Warner", 6, ExtraType.NONE, false);
        bowl(match, "Bumrah", "Warner", 6, ExtraType.NONE, false);   // 36 reaches the target: chase won at the end of over 1, a full over early

        System.out.println("\nStatus: " + match.getStatus());
        System.out.println("Result: " + match.getResult());

        System.out.println("\n-- Rejections --");
        try {
            bowl(match, "Hazlewood", "Warner", 1, ExtraType.NONE, false);   // match already over
        } catch (InvalidDeliveryException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
        CricketMatch another = new CricketMatch(india, australia, MatchFormat.ODI, 2);
        another.start();
        try {
            another.recordDelivery("Starc", "Rohit", 4, ExtraType.WIDE, true);   // wicket off a wide
        } catch (InvalidDeliveryException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
    }

    private static void bowl(CricketMatch match, String bowler, String batsman, int runs, ExtraType extraType, boolean wicket) {
        match.recordDelivery(bowler, batsman, runs, extraType, wicket);
    }
}
