package strategy;

import entities.Seat;
import entities.Show;

import java.time.DayOfWeek;

// Wraps another pricing strategy and adds a surcharge on Saturday and Sunday shows
public class WeekendSurgePricingStrategy implements PricingStrategy {
    private final PricingStrategy base;
    private final double surcharge;      // 0.25 = +25%

    public WeekendSurgePricingStrategy(PricingStrategy base, double surcharge) {
        this.base = base;
        this.surcharge = surcharge;
    }

    @Override
    public double priceFor(Show show, Seat seat) {
        double price = base.priceFor(show, seat);
        DayOfWeek day = show.getStartTime().getDayOfWeek();
        boolean weekend = day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
        return weekend ? price * (1 + surcharge) : price;
    }
}
