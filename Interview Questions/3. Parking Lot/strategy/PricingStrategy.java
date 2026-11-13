package strategy;

import entities.ParkingTicket;

import java.time.Instant;

// Strategy: how much does this ticket cost at exit time?
public interface PricingStrategy {
    long calculateFee(ParkingTicket ticket, Instant exitTime);
}
