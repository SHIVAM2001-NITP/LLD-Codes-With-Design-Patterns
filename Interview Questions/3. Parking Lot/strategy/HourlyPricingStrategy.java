package strategy;

import entities.ParkingTicket;
import enums.VehicleType;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;

// Rate per hour for each vehicle type; every started hour is charged, minimum one hour
public class HourlyPricingStrategy implements PricingStrategy {
    private final Map<VehicleType, Long> ratePerHour;

    public HourlyPricingStrategy(Map<VehicleType, Long> ratePerHour) {
        this.ratePerHour = ratePerHour;
    }

    @Override
    public long calculateFee(ParkingTicket ticket, Instant exitTime) {
        long minutes = Duration.between(ticket.getEntryTime(), exitTime).toMinutes();
        long hours = Math.max(1, (minutes + 59) / 60);
        return hours * ratePerHour.get(ticket.getVehicle().getType());
    }
}
