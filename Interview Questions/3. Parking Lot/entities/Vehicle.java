package entities;

import enums.VehicleType;

public abstract class Vehicle {
    private final String plateNumber;
    private final VehicleType type;

    protected Vehicle(String plateNumber, VehicleType type) {
        this.plateNumber = plateNumber;
        this.type = type;
    }

    public String getPlateNumber() { return plateNumber; }

    public VehicleType getType() { return type; }

    @Override
    public String toString() {
        return type + " " + plateNumber;
    }
}
