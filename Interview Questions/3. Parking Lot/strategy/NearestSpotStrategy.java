package strategy;

import entities.ParkingFloor;
import entities.ParkingSpot;
import enums.SpotType;

import java.util.List;
import java.util.Optional;

// First free spot from the lowest floor: closest to the entry gate
public class NearestSpotStrategy implements SpotAssignmentStrategy {

    @Override
    public Optional<ParkingSpot> findSpot(List<ParkingFloor> floors, SpotType type) {
        for (ParkingFloor floor : floors) {
            Optional<ParkingSpot> spot = floor.findFreeSpot(type);
            if (spot.isPresent()) {
                return spot;
            }
        }
        return Optional.empty();
    }
}
