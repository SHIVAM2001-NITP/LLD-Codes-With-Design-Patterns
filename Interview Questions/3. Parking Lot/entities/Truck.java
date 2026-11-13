package entities;

import enums.VehicleType;

public class Truck extends Vehicle {

    public Truck(String plateNumber) {
        super(plateNumber, VehicleType.TRUCK);
    }
}
