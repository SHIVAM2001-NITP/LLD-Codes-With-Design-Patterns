package entities;

import enums.ExtraType;
import exceptions.InvalidDeliveryException;

import java.util.ArrayList;
import java.util.List;

// One team's innings: its overs, its running score, and the rules that only make sense
// within a single innings (six legal balls per over, no bowler twice in a row).
public class Innings {
    private final Team battingTeam;
    private final Team bowlingTeam;
    private final int maxOvers;
    private final List<Over> overs = new ArrayList<>();
    private int totalRuns = 0;
    private int totalWickets = 0;
    private Over currentOver;

    public Innings(Team battingTeam, Team bowlingTeam, int maxOvers) {
        this.battingTeam = battingTeam;
        this.bowlingTeam = bowlingTeam;
        this.maxOvers = maxOvers;
    }

    public Team getBattingTeam() { return battingTeam; }

    public int getTotalRuns() { return totalRuns; }

    public int getTotalWickets() { return totalWickets; }

    public Over getCurrentOver() { return currentOver; }

    public int oversBowled() { return overs.size() - (currentOver != null && !currentOver.isComplete() ? 1 : 0); }

    public boolean isAllOut() { return totalWickets >= 10; }

    public boolean oversUp() { return oversBowled() >= maxOvers; }

    public boolean isComplete() { return isAllOut() || oversUp(); }

    public Delivery recordDelivery(String bowler, String batsman, int runs, ExtraType extraType, boolean wicket) {
        if (isComplete()) {
            throw new InvalidDeliveryException("This innings is already over");
        }
        if (wicket && (extraType == ExtraType.WIDE || extraType == ExtraType.NO_BALL)) {
            throw new InvalidDeliveryException("A wicket cannot be taken directly off a " + extraType + " (only a run-out can)");
        }
        if (currentOver == null || currentOver.isComplete()) {
            startOver(bowler);
        }

        Delivery delivery = new Delivery(bowler, batsman, runs, extraType, wicket);
        currentOver.add(delivery);
        totalRuns += runs + (extraType == ExtraType.WIDE || extraType == ExtraType.NO_BALL ? 1 : 0);
        if (wicket) {
            totalWickets++;
        }
        return delivery;
    }

    private void startOver(String bowler) {
        if (!overs.isEmpty()) {
            String previousBowler = overs.get(overs.size() - 1).getBowler();
            if (previousBowler.equals(bowler)) {
                throw new InvalidDeliveryException(bowler + " cannot bowl two overs in a row");
            }
        }
        currentOver = new Over(bowler);
        overs.add(currentOver);
    }
}
