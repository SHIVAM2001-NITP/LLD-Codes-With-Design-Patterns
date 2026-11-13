package entities;

import enums.SpotType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParkingFloor {
    private final int number;
    private final List<ParkingSpot> spots = new ArrayList<>();

    public ParkingFloor(int number) {
        this.number = number;
    }

    public int getNumber() { return number; }

    public void addSpot(SpotType type) {
        String id = "F" + number + "-" + type.name().charAt(0) + (countOfType(type) + 1);
        spots.add(new ParkingSpot(id, number, type));
    }

    public Optional<ParkingSpot> findFreeSpot(SpotType type) {
        for (ParkingSpot spot : spots) {
            if (spot.getType() == type && spot.isFree()) {
                return Optional.of(spot);
            }
        }
        return Optional.empty();
    }

    public int freeSpots(SpotType type) {
        int count = 0;
        for (ParkingSpot spot : spots) {
            if (spot.getType() == type && spot.isFree()) {
                count++;
            }
        }
        return count;
    }

    private int countOfType(SpotType type) {
        int count = 0;
        for (ParkingSpot spot : spots) {
            if (spot.getType() == type) {
                count++;
            }
        }
        return count;
    }
}
