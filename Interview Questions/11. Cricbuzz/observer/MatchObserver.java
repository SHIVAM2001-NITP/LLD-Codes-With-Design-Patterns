package observer;

import entities.Delivery;
import entities.Innings;
import entities.Over;
import service.CricketMatch;

// Observer: each subscriber only overrides the events it actually cares about.
public interface MatchObserver {
    default void onBallBowled(CricketMatch match, Delivery delivery) { }

    default void onOverCompleted(CricketMatch match, Over over) { }

    default void onInningsCompleted(CricketMatch match, Innings innings) { }

    default void onMatchCompleted(CricketMatch match, String result) { }
}
