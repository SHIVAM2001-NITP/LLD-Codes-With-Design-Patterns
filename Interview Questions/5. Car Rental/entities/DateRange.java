package entities;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

// A rental window, start inclusive, end inclusive
public class DateRange {
    private final LocalDate start;
    private final LocalDate end;

    public DateRange(LocalDate start, LocalDate end) {
        if (end.isBefore(start)) {
            throw new IllegalArgumentException("End date cannot be before start date");
        }
        this.start = start;
        this.end = end;
    }

    public LocalDate getStart() { return start; }

    public LocalDate getEnd() { return end; }

    public long days() { return ChronoUnit.DAYS.between(start, end) + 1; }

    public boolean overlaps(DateRange other) {
        return !start.isAfter(other.end) && !end.isBefore(other.start);
    }

    @Override
    public String toString() { return start + " to " + end; }
}
