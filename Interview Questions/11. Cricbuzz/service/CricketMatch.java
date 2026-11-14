package service;

import entities.Delivery;
import entities.Innings;
import entities.Over;
import entities.Team;
import enums.ExtraType;
import enums.MatchFormat;
import enums.MatchStatus;
import exceptions.InvalidDeliveryException;
import observer.MatchObserver;

import java.util.ArrayList;
import java.util.List;

// Subject of the Observer pattern and the orchestrator of the match: records each ball
// against the active innings, detects over/innings/match completion, and notifies
// every subscriber (scorecard, commentary, ...) without knowing what they do with it.
public class CricketMatch {
    private final Team firstBattingTeam;
    private final Team secondBattingTeam;
    private final MatchFormat format;
    private final int maxOvers;
    private final List<MatchObserver> observers = new ArrayList<>();
    private MatchStatus status = MatchStatus.SCHEDULED;
    private Innings firstInnings;
    private Innings secondInnings;
    private Innings currentInnings;
    private String result;

    public CricketMatch(Team firstBattingTeam, Team secondBattingTeam, MatchFormat format, int maxOvers) {
        this.firstBattingTeam = firstBattingTeam;
        this.secondBattingTeam = secondBattingTeam;
        this.format = format;
        this.maxOvers = maxOvers;
    }

    public void addObserver(MatchObserver observer) { observers.add(observer); }

    public MatchStatus getStatus() { return status; }

    public Innings getCurrentInnings() { return currentInnings; }

    public Integer getTarget() { return firstInnings == null ? null : firstInnings.getTotalRuns() + 1; }

    public String getResult() { return result; }

    public void start() {
        status = MatchStatus.LIVE;
        firstInnings = new Innings(firstBattingTeam, secondBattingTeam, maxOvers);
        currentInnings = firstInnings;
    }

    public synchronized void recordDelivery(String bowler, String batsman, int runs, ExtraType extraType, boolean wicket) {
        if (status != MatchStatus.LIVE) {
            throw new InvalidDeliveryException("The match is not live");
        }
        Delivery delivery = currentInnings.recordDelivery(bowler, batsman, runs, extraType, wicket);
        notifyBall(delivery);

        if (currentInnings.getCurrentOver().isComplete()) {
            notifyOverCompleted(currentInnings.getCurrentOver());
        }

        if (currentInnings == secondInnings && currentInnings.getTotalRuns() > firstInnings.getTotalRuns()) {
            finishMatch(secondBattingTeam + " won by " + (10 - secondInnings.getTotalWickets()) + " wicket(s)");
            return;
        }
        if (currentInnings.isComplete()) {
            notifyInningsCompleted(currentInnings);
            if (currentInnings == firstInnings) {
                secondInnings = new Innings(secondBattingTeam, firstBattingTeam, maxOvers);
                currentInnings = secondInnings;
            } else {
                int margin = firstInnings.getTotalRuns() - secondInnings.getTotalRuns();
                String outcome = margin == 0 ? "Match tied" : firstBattingTeam + " won by " + margin + " run(s)";
                finishMatch(outcome);
            }
        }
    }

    private void finishMatch(String outcome) {
        status = MatchStatus.COMPLETED;
        result = outcome;
        notifyMatchCompleted(outcome);
    }

    private void notifyBall(Delivery delivery) {
        for (MatchObserver observer : observers) {
            observer.onBallBowled(this, delivery);
        }
    }

    private void notifyOverCompleted(Over over) {
        for (MatchObserver observer : observers) {
            observer.onOverCompleted(this, over);
        }
    }

    private void notifyInningsCompleted(Innings innings) {
        for (MatchObserver observer : observers) {
            observer.onInningsCompleted(this, innings);
        }
    }

    private void notifyMatchCompleted(String result) {
        for (MatchObserver observer : observers) {
            observer.onMatchCompleted(this, result);
        }
    }
}
