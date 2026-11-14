package entities;

import enums.ExtraType;

// One ball bowled. `runs` is what came off the bat/byes, excluding the automatic
// one-run penalty a wide or no-ball adds on top (that is applied by Innings).
public class Delivery {
    private final String bowler;
    private final String batsman;
    private final int runs;
    private final ExtraType extraType;
    private final boolean wicket;

    public Delivery(String bowler, String batsman, int runs, ExtraType extraType, boolean wicket) {
        this.bowler = bowler;
        this.batsman = batsman;
        this.runs = runs;
        this.extraType = extraType;
        this.wicket = wicket;
    }

    public String getBowler() { return bowler; }

    public String getBatsman() { return batsman; }

    public int getRuns() { return runs; }

    public ExtraType getExtraType() { return extraType; }

    public boolean isWicket() { return wicket; }

    // A wide or no-ball does not consume one of the over's six legal deliveries
    public boolean isLegal() { return extraType != ExtraType.WIDE && extraType != ExtraType.NO_BALL; }

    @Override
    public String toString() {
        String extra = extraType == ExtraType.NONE ? "" : " " + extraType;
        String out = wicket ? " WICKET" : "";
        return bowler + " to " + batsman + ": " + runs + " run(s)" + extra + out;
    }
}
