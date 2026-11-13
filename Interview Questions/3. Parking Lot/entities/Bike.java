package entities;

import enums.VehicleType;

public class Bike extends Vehicle {

    public Bike(String plateNumber) {
        super(plateNumber, VehicleType.BIKE);
    }
}
