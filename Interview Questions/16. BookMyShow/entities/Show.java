package entities;

import enums.SeatStatus;
import enums.SeatType;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

// A movie playing on a screen at a time. Owns the seat map; every change to it happens while
// holding this object's monitor (see BookingService), so one show is the unit of locking.
public class Show {
    private final String id;
    private final Movie movie;
    private final Theatre theatre;
    private final Screen screen;
    private final LocalDateTime startTime;
    private final Map<SeatType, Double> basePrices;
    private final Map<String, ShowSeat> seats = new LinkedHashMap<>();

    public Show(String id, Movie movie, Theatre theatre, Screen screen, LocalDateTime startTime, Map<SeatType, Double> basePrices) {
        this.id = id;
        this.movie = movie;
        this.theatre = theatre;
        this.screen = screen;
        this.startTime = startTime;
        this.basePrices = basePrices;
        for (Seat seat : screen.getSeats()) {
            seats.put(seat.getId(), new ShowSeat(seat));
        }
    }

    public String getId() { return id; }

    public Movie getMovie() { return movie; }

    public Theatre getTheatre() { return theatre; }

    public Screen getScreen() { return screen; }

    public LocalDateTime getStartTime() { return startTime; }

    public double getBasePrice(SeatType type) { return basePrices.get(type); }

    public Map<String, ShowSeat> getSeats() { return seats; }

    public long countByStatus(SeatStatus status) {
        return seats.values().stream().filter(s -> s.getStatus() == status).count();
    }

    @Override
    public String toString() {
        return id + " " + movie.getTitle() + " @ " + theatre.getName() + "/" + screen.getName() + " " + startTime.toLocalDate() + " " + startTime.toLocalTime();
    }
}
