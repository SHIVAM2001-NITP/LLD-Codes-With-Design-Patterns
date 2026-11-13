package strategy;

import entities.Elevator;
import entities.Request;

import java.util.List;

// Strategy: which elevator should answer this hall call?
public interface ElevatorSelectionStrategy {
    Elevator select(List<Elevator> elevators, Request request);
}
