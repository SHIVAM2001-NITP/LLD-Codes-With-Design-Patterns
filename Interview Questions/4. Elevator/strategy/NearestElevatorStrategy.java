package strategy;

import entities.Elevator;
import entities.Request;

import java.util.List;

// Prefer the closest elevator that can pick the caller up on the way (idle, or already heading there in the
// same direction). If none can, fall back to the one with the fewest pending stops.
public class NearestElevatorStrategy implements ElevatorSelectionStrategy {

    @Override
    public Elevator select(List<Elevator> elevators, Request request) {
        Elevator best = null;
        for (Elevator e : elevators) {
            if (e.canServeOnTheWay(request)
                    && (best == null || e.distanceTo(request.getFloor()) < best.distanceTo(request.getFloor()))) {
                best = e;
            }
        }
        if (best != null) {
            return best;
        }
        for (Elevator e : elevators) {
            if (best == null || e.pendingStops() < best.pendingStops()) {
                best = e;
            }
        }
        return best;
    }
}
