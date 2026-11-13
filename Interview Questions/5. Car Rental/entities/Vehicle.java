package entities;

import enums.VehicleType;

public class Vehicle {
    private final String id;
    private final String model;
    private final VehicleType type;

    public Vehicle(String id, String model, VehicleType type) {
        this.id = id;
        this.model = model;
        this.type = type;
    }

    public String getId() { return id; }

    public String getModel() { return model; }

    public VehicleType getType() { return type; }

    @Override
    public String toString() {
        return model + " (" + type + ", " + id + ")";
    }
}
