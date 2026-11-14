package strategy;

import entities.Seat;
import entities.Show;

// The price printed on the show for that seat type
public class StandardPricingStrategy implements PricingStrategy {

    @Override
    public double priceFor(Show show, Seat seat) {
        return show.getBasePrice(seat.getType());
    }
}
