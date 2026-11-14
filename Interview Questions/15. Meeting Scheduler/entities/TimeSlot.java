package entities;

import exceptions.InvalidTimeSlotException;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// A half-open interval [start, end): a meeting ending at 11:00 does not clash with one starting at 11:00.
public class TimeSlot {
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd MMM HH:mm");

    private final LocalDateTime start;
    private final LocalDateTime end;

    public TimeSlot(LocalDateTime start, LocalDateTime end) {
        if (!start.isBefore(end)) {
            throw new InvalidTimeSlotException("Start (" + start + ") must be before end (" + end + ")");
        }
        this.start = start;
        this.end = end;
    }

    public LocalDateTime getStart() { return start; }

    public LocalDateTime getEnd() { return end; }

    public Duration getDuration() { return Duration.between(start, end); }

    public boolean overlaps(TimeSlot other) {
        return start.isBefore(other.end) && other.start.isBefore(end);
    }

    @Override
    public String toString() {
        return FORMAT.format(start) + " - " + DateTimeFormatter.ofPattern("HH:mm").format(end);
    }
}
