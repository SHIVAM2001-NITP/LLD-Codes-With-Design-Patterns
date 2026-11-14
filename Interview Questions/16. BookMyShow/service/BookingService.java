package service;

import entities.Booking;
import entities.Seat;
import entities.Show;
import entities.ShowSeat;
import entities.User;
import enums.BookingStatus;
import enums.SeatStatus;
import exceptions.BookingExpiredException;
import exceptions.BookingNotFoundException;
import exceptions.InvalidBookingStateException;
import exceptions.PaymentFailedException;
import exceptions.SeatUnavailableException;
import strategy.PaymentStrategy;
import strategy.PricingStrategy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

// Booking is two steps, like the real app: (1) lock the chosen seats for a few minutes while
// the user pays, (2) confirm after payment. Seats are locked ALL-OR-NOTHING, and every change to a
// show's seat map happens while holding that show's monitor, so two people racing for the same
// seat can never both win, while different shows never block each other.
public class BookingService {
    private final CatalogService catalog;
    private final PricingStrategy pricing;
    private final TimeProvider time;
    private final int lockMinutes;
    private final Map<String, Booking> bookings = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger();

    public BookingService(CatalogService catalog, PricingStrategy pricing, TimeProvider time, int lockMinutes) {
        this.catalog = catalog;
        this.pricing = pricing;
        this.time = time;
        this.lockMinutes = lockMinutes;
    }

    // Step 1: hold the seats. Throws SeatUnavailableException (and holds nothing) if any is taken.
    public Booking lockSeats(User user, String showId, List<String> seatIds) {
        Show show = catalog.getShow(showId);
        synchronized (show) {
            LocalDateTime now = time.now();
            releaseExpiredLocks(show, now);

            List<ShowSeat> chosen = new ArrayList<>();
            for (String seatId : seatIds) {
                ShowSeat showSeat = show.getSeats().get(seatId);
                if (showSeat == null) {
                    throw new SeatUnavailableException("Seat " + seatId + " does not exist in " + show.getScreen().getName());
                }
                if (showSeat.getStatus() != SeatStatus.AVAILABLE) {
                    throw new SeatUnavailableException("Seat " + seatId + " is already " + showSeat.getStatus());
                }
                chosen.add(showSeat);
            }

            String bookingId = "BK-" + counter.incrementAndGet();
            LocalDateTime expiry = now.plusMinutes(lockMinutes);
            double amount = 0;
            for (ShowSeat showSeat : chosen) {
                showSeat.lock(bookingId, expiry);
                Seat seat = showSeat.getSeat();
                amount += pricing.priceFor(show, seat);
            }
            Booking booking = new Booking(bookingId, user, show, chosen, amount, expiry);
            bookings.put(bookingId, booking);
            return booking;
        }
    }

    // Step 2: pay. If the hold ran out, or payment fails, the seats go back on sale.
    public Booking confirm(String bookingId, PaymentStrategy payment) {
        Booking booking = find(bookingId);
        Show show = booking.getShow();
        synchronized (show) {
            if (booking.getStatus() != BookingStatus.PENDING) {
                throw new InvalidBookingStateException("Booking " + bookingId + " is " + booking.getStatus() + ", not PENDING");
            }
            if (!time.now().isBefore(booking.getExpiresAt())) {
                releaseSeats(booking, BookingStatus.EXPIRED);
                throw new BookingExpiredException("Seat hold for " + bookingId + " expired; please select seats again");
            }
            if (!payment.pay(booking.getAmount())) {
                releaseSeats(booking, BookingStatus.CANCELLED);
                throw new PaymentFailedException("Payment failed for " + bookingId + "; seats released");
            }
            for (ShowSeat showSeat : booking.getSeats()) {
                showSeat.book();
            }
            booking.setStatus(BookingStatus.CONFIRMED);
            return booking;
        }
    }

    // Cancel a pending or confirmed booking; its seats go back on sale
    public Booking cancel(String bookingId) {
        Booking booking = find(bookingId);
        synchronized (booking.getShow()) {
            if (booking.getStatus() != BookingStatus.PENDING && booking.getStatus() != BookingStatus.CONFIRMED) {
                throw new InvalidBookingStateException("Booking " + bookingId + " is " + booking.getStatus() + " and cannot be cancelled");
            }
            releaseSeats(booking, BookingStatus.CANCELLED);
            return booking;
        }
    }

    // Seats whose hold ran out go back on sale lazily, the next time anybody touches that show
    private void releaseExpiredLocks(Show show, LocalDateTime now) {
        for (ShowSeat showSeat : show.getSeats().values()) {
            if (showSeat.isLockExpired(now)) {
                Booking stale = bookings.get(showSeat.getLockedByBookingId());
                if (stale != null && stale.getStatus() == BookingStatus.PENDING) {
                    stale.setStatus(BookingStatus.EXPIRED);
                }
                showSeat.release();
            }
        }
    }

    private void releaseSeats(Booking booking, BookingStatus newStatus) {
        for (ShowSeat showSeat : booking.getSeats()) {
            showSeat.release();
        }
        booking.setStatus(newStatus);
    }

    private Booking find(String bookingId) {
        Booking booking = bookings.get(bookingId);
        if (booking == null) {
            throw new BookingNotFoundException("Unknown booking: " + bookingId);
        }
        return booking;
    }
}
