package strategy;

import entities.DateRange;
import enums.VehicleType;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Map;

// Decorator: wraps another strategy and gives a discount for every weekend day in the period
public class WeekendDiscountPricingStrategy implements PricingStrategy {
    private final PricingStrategy delegate;
    private final double weekendDiscount;

    public WeekendDiscountPricingStrategy(PricingStrategy delegate, double weekendDiscount) {
        this.delegate = delegate;
        this.weekendDiscount = weekendDiscount;
    }

    @Override
    public long calculatePrice(VehicleType type, DateRange period) {
        long base = delegate.calculatePrice(type, period);
        long dailyAverage = base / period.days();
        long weekendDays = countWeekendDays(period);
        long discount = (long) (weekendDays * dailyAverage * weekendDiscount);
        return base - discount;
    }

    private long countWeekendDays(DateRange period) {
        long count = 0;
        for (LocalDate d = period.getStart(); !d.isAfter(period.getEnd()); d = d.plusDays(1)) {
            if (d.getDayOfWeek() == DayOfWeek.SATURDAY || d.getDayOfWeek() == DayOfWeek.SUNDAY) {
                count++;
            }
        }
        return count;
    }
}
