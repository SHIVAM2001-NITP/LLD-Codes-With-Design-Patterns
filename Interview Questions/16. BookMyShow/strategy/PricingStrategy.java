package strategy;

import entities.Seat;
import entities.Show;

// Strategy: how much one seat costs for one show
public interface PricingStrategy {
    double priceFor(Show show, Seat seat);
}
