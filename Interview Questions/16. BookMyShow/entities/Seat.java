package entities;

import enums.SeatType;

// A physical seat in a screen. It never changes; per-show availability lives in ShowSeat.
public class Seat {
    private final String id;      // e.g. "A1"
    private final SeatType type;

    public Seat(String id, SeatType type) {
        this.id = id;
        this.type = type;
    }

    public String getId() { return id; }

    public SeatType getType() { return type; }

    @Override
    public String toString() { return id; }
}
