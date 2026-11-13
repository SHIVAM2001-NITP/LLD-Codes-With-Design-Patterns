package strategy;

import entities.DateRange;
import enums.VehicleType;

// Strategy: how much does a rental of this vehicle type for this period cost?
public interface PricingStrategy {
    long calculatePrice(VehicleType type, DateRange period);
}
