package enums;

// The size of a spot. Each vehicle type needs one spot type.
public enum SpotType {
    BIKE,
    COMPACT,
    LARGE;

    public static SpotType forVehicle(VehicleType vehicleType) {
        switch (vehicleType) {
            case BIKE:
                return BIKE;
            case CAR:
                return COMPACT;
            default:
                return LARGE;
        }
    }
}
