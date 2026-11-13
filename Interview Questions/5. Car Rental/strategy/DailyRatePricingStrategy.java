package strategy;

import entities.DateRange;
import enums.VehicleType;

import java.util.Map;

// Flat rate per day per vehicle type
public class DailyRatePricingStrategy implements PricingStrategy {
    private final Map<VehicleType, Long> ratePerDay;

    public DailyRatePricingStrategy(Map<VehicleType, Long> ratePerDay) {
        this.ratePerDay = ratePerDay;
    }

    @Override
    public long calculatePrice(VehicleType type, DateRange period) {
        return ratePerDay.get(type) * period.days();
    }
}
