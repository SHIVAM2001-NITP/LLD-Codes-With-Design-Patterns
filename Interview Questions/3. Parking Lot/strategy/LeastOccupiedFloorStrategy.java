package strategy;

import entities.ParkingFloor;
import entities.ParkingSpot;
import enums.SpotType;

import java.util.List;
import java.util.Optional;

// Spreads vehicles out: picks the floor with the most free spots of the needed type
public class LeastOccupiedFloorStrategy implements SpotAssignmentStrategy {

    @Override
    public Optional<ParkingSpot> findSpot(List<ParkingFloor> floors, SpotType type) {
        ParkingFloor best = null;
        for (ParkingFloor floor : floors) {
            if (floor.freeSpots(type) > 0 && (best == null || floor.freeSpots(type) > best.freeSpots(type))) {
                best = floor;
            }
        }
        return best == null ? Optional.<ParkingSpot>empty() : best.findFreeSpot(type);
    }
}
