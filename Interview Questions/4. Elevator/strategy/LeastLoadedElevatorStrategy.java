package strategy;

import entities.Elevator;
import entities.Request;

import java.util.List;

// Balances the work: fewest pending stops wins, the distance only breaks ties
public class LeastLoadedElevatorStrategy implements ElevatorSelectionStrategy {

    @Override
    public Elevator select(List<Elevator> elevators, Request request) {
        Elevator best = null;
        for (Elevator e : elevators) {
            if (best == null
                    || e.pendingStops() < best.pendingStops()
                    || (e.pendingStops() == best.pendingStops()
                        && e.distanceTo(request.getFloor()) < best.distanceTo(request.getFloor()))) {
                best = e;
            }
        }
        return best;
    }
}
