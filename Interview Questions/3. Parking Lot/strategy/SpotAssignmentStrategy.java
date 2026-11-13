package strategy;

import entities.ParkingFloor;
import entities.ParkingSpot;
import enums.SpotType;

import java.util.List;
import java.util.Optional;

// Strategy: which free spot should this vehicle get?
public interface SpotAssignmentStrategy {
    Optional<ParkingSpot> findSpot(List<ParkingFloor> floors, SpotType type);
}
