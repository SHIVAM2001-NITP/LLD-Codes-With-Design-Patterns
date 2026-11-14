package observer;

import entities.Innings;
import entities.Over;
import service.CricketMatch;

// One subscriber: prints the running score after every over
public class ScoreCardObserver implements MatchObserver {

    @Override
    public void onOverCompleted(CricketMatch match, Over over) {
        Innings innings = match.getCurrentInnings();
        System.out.println("  [Scorecard] " + innings.getBattingTeam() + ": "
                + innings.getTotalRuns() + "/" + innings.getTotalWickets()
                + " after " + innings.oversBowled() + " over(s)");
    }
}
