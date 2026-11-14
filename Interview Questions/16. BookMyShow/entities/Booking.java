package entities;

import enums.BookingStatus;

import java.time.LocalDateTime;
import java.util.List;

public class Booking {
    private final String id;
    private final User user;
    private final Show show;
    private final List<ShowSeat> seats;
    private final double amount;
    private final LocalDateTime expiresAt;
    private BookingStatus status = BookingStatus.PENDING;

    public Booking(String id, User user, Show show, List<ShowSeat> seats, double amount, LocalDateTime expiresAt) {
        this.id = id;
        this.user = user;
        this.show = show;
        this.seats = seats;
        this.amount = amount;
        this.expiresAt = expiresAt;
    }

    public String getId() { return id; }

    public User getUser() { return user; }

    public Show getShow() { return show; }

    public List<ShowSeat> getSeats() { return seats; }

    public double getAmount() { return amount; }

    public LocalDateTime getExpiresAt() { return expiresAt; }

    public BookingStatus getStatus() { return status; }

    public void setStatus(BookingStatus status) { this.status = status; }

    @Override
    public String toString() {
        StringBuilder ids = new StringBuilder();
        for (ShowSeat s : seats) {
            if (ids.length() > 0) ids.append(",");
            ids.append(s.getSeat().getId());
        }
        return id + " [" + user + ", seats " + ids + ", Rs." + amount + ", " + status + "]";
    }
}
