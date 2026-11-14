package observer;

import entities.Delivery;
import service.CricketMatch;

// Another subscriber: ball-by-ball commentary, independent of the scorecard
public class CommentaryObserver implements MatchObserver {

    @Override
    public void onBallBowled(CricketMatch match, Delivery delivery) {
        System.out.println("  [Commentary] " + delivery);
    }
}
