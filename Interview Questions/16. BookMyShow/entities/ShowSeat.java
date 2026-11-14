package entities;

import enums.SeatStatus;

import java.time.LocalDateTime;

// One seat for ONE show: the same physical seat can be booked in the 6pm show and free in the 9pm show.
public class ShowSeat {
    private final Seat seat;
    private SeatStatus status = SeatStatus.AVAILABLE;
    private String lockedByBookingId;
    private LocalDateTime lockExpiry;

    public ShowSeat(Seat seat) { this.seat = seat; }

    public Seat getSeat() { return seat; }

    public SeatStatus getStatus() { return status; }

    public LocalDateTime getLockExpiry() { return lockExpiry; }

    public String getLockedByBookingId() { return lockedByBookingId; }

    public void lock(String bookingId, LocalDateTime expiry) {
        this.status = SeatStatus.LOCKED;
        this.lockedByBookingId = bookingId;
        this.lockExpiry = expiry;
    }

    public void book() {
        this.status = SeatStatus.BOOKED;
        this.lockExpiry = null;
    }

    public void release() {
        this.status = SeatStatus.AVAILABLE;
        this.lockedByBookingId = null;
        this.lockExpiry = null;
    }

    public boolean isLockExpired(LocalDateTime now) {
        return status == SeatStatus.LOCKED && !now.isBefore(lockExpiry);
    }
}
