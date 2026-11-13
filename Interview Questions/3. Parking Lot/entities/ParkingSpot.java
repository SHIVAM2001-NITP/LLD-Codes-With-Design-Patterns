package entities;

import enums.SpotType;

public class ParkingSpot {
    private final String id;
    private final int floorNumber;
    private final SpotType type;
    private Vehicle vehicle;          // null when the spot is free

    public ParkingSpot(String id, int floorNumber, SpotType type) {
        this.id = id;
        this.floorNumber = floorNumber;
        this.type = type;
    }

    public String getId() { return id; }

    public int getFloorNumber() { return floorNumber; }

    public SpotType getType() { return type; }

    public boolean isFree() { return vehicle == null; }

    public void park(Vehicle vehicle) {
        if (!isFree()) {
            throw new IllegalStateException("Spot " + id + " is already taken");
        }
        this.vehicle = vehicle;
    }

    public void free() {
        this.vehicle = null;
    }
}
