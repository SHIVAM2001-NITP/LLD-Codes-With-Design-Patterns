import entities.Booking;
import entities.Movie;
import entities.Screen;
import entities.Seat;
import entities.Show;
import entities.Theatre;
import entities.User;
import enums.SeatStatus;
import enums.SeatType;
import exceptions.BookingExpiredException;
import exceptions.InvalidBookingStateException;
import exceptions.PaymentFailedException;
import exceptions.SeatUnavailableException;
import service.BookingService;
import service.CatalogService;
import service.TimeProvider;
import strategy.CardPayment;
import strategy.StandardPricingStrategy;
import strategy.UpiPayment;
import strategy.WeekendSurgePricingStrategy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Main {

    // Rows A-B are PLATINUM, C-D GOLD, E SILVER; 6 seats per row
    private static Screen buildScreen(String id, String name) {
        List<Seat> seats = new ArrayList<>();
        String rows = "ABCDE";
        for (int r = 0; r < rows.length(); r++) {
            SeatType type = r < 2 ? SeatType.PLATINUM : (r < 4 ? SeatType.GOLD : SeatType.SILVER);
            for (int n = 1; n <= 6; n++) {
                seats.add(new Seat("" + rows.charAt(r) + n, type));
            }
        }
        return new Screen(id, name, seats);
    }

    public static void main(String[] args) throws Exception {

        // ---- catalog ----
        CatalogService catalog = new CatalogService();
        Theatre pvr = new Theatre("T1", "PVR Forum", "Bengaluru");
        Screen screen1 = buildScreen("S1", "Screen 1");
        pvr.addScreen(screen1);
        catalog.addTheatre(pvr);

        Movie dune = new Movie("MV1", "Dune: Part Two", "English", 166);
        Movie rrr = new Movie("MV2", "RRR", "Telugu", 182);
        catalog.addMovie(dune);
        catalog.addMovie(rrr);

        Map<SeatType, Double> prices = new EnumMap<>(SeatType.class);
        prices.put(SeatType.SILVER, 150.0);
        prices.put(SeatType.GOLD, 250.0);
        prices.put(SeatType.PLATINUM, 400.0);

        // 2026-11-16 is a Monday, 2026-11-21 a Saturday
        catalog.addShow(new Show("SH1", dune, pvr, screen1, LocalDateTime.of(2026, 11, 16, 18, 30), prices));
        catalog.addShow(new Show("SH2", dune, pvr, screen1, LocalDateTime.of(2026, 11, 21, 18, 30), prices));
        catalog.addShow(new Show("SH3", rrr, pvr, screen1, LocalDateTime.of(2026, 11, 16, 21, 30), prices));

        // A clock the demo can move forward instead of sleeping
        AtomicReference<LocalDateTime> clock = new AtomicReference<>(LocalDateTime.of(2026, 11, 15, 12, 0));
        TimeProvider time = clock::get;

        BookingService booking = new BookingService(catalog,
                new WeekendSurgePricingStrategy(new StandardPricingStrategy(), 0.25), time, 5);

        User alice = new User("U1", "Alice");
        User bob = new User("U2", "Bob");
        User carol = new User("U3", "Carol");

        System.out.println("=== 1. Browse: what is playing in Bengaluru? ===");
        System.out.println("Movies: " + catalog.getMoviesInCity("Bengaluru"));
        System.out.println("Shows of Dune: ");
        for (Show s : catalog.searchShows("Bengaluru", "Dune: Part Two")) {
            System.out.println("  " + s + " (" + s.countByStatus(SeatStatus.AVAILABLE) + " seats free)");
        }

        System.out.println("\n=== 2. Alice locks A1, A2 on the Monday show and pays by UPI ===");
        Booking b1 = booking.lockSeats(alice, "SH1", Arrays.asList("A1", "A2"));
        System.out.println("Locked: " + b1);
        booking.confirm(b1.getId(), new UpiPayment("alice@upi"));
        System.out.println("Result: " + b1);

        System.out.println("\n=== 3. Same seats on the SATURDAY show cost 25% more ===");
        Booking weekend = booking.lockSeats(alice, "SH2", Arrays.asList("A1", "A2"));
        System.out.println("Weekend price for A1+A2: Rs." + weekend.getAmount() + " (weekday was Rs." + b1.getAmount() + ")");
        booking.cancel(weekend.getId());

        System.out.println("\n=== 4. Bob tries A2 on the Monday show: already booked ===");
        try {
            booking.lockSeats(bob, "SH1", Arrays.asList("A2", "B1"));
        } catch (SeatUnavailableException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
        System.out.println("B1 was not left locked by the failed attempt: B1 is "
                + catalog.getShow("SH1").getSeats().get("B1").getStatus());

        System.out.println("\n=== 5. Bob's card is declined: seats go back on sale ===");
        Booking b2 = booking.lockSeats(bob, "SH1", Arrays.asList("C1", "C2"));
        try {
            booking.confirm(b2.getId(), new CardPayment("4111-XXXX", 100.0));
        } catch (PaymentFailedException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
        System.out.println("C1 is now " + catalog.getShow("SH1").getSeats().get("C1").getStatus());

        System.out.println("\n=== 6. Carol locks D1 but takes too long to pay (lock is 5 minutes) ===");
        Booking b3 = booking.lockSeats(carol, "SH1", Arrays.asList("D1"));
        System.out.println("Locked: " + b3);
        clock.set(clock.get().plusMinutes(6));
        try {
            booking.confirm(b3.getId(), new UpiPayment("carol@upi"));
        } catch (BookingExpiredException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
        System.out.println("D1 is now " + catalog.getShow("SH1").getSeats().get("D1").getStatus() + ", booking is " + b3.getStatus());

        System.out.println("\n=== 7. Alice cancels her confirmed booking; a second cancel is rejected ===");
        booking.cancel(b1.getId());
        System.out.println("Result: " + b1);
        try {
            booking.cancel(b1.getId());
        } catch (InvalidBookingStateException e) {
            System.out.println("Rejected: " + e.getMessage());
        }

        System.out.println("\n=== 8. Race: 20 users all try to lock seat E1 at the same moment ===");
        int users = 20;
        CountDownLatch ready = new CountDownLatch(users);
        CountDownLatch go = new CountDownLatch(1);
        CountDownLatch done = new CountDownLatch(users);
        AtomicInteger winners = new AtomicInteger();
        AtomicInteger losers = new AtomicInteger();
        for (int i = 0; i < users; i++) {
            final User u = new User("R" + i, "Racer" + i);
            new Thread(() -> {
                ready.countDown();
                try {
                    go.await();
                    booking.lockSeats(u, "SH3", Arrays.asList("E1"));
                    winners.incrementAndGet();
                } catch (SeatUnavailableException e) {
                    losers.incrementAndGet();
                } catch (InterruptedException ignored) {
                } finally {
                    done.countDown();
                }
            }).start();
        }
        ready.await();
        go.countDown();
        done.await();
        System.out.println("Winners: " + winners.get() + ", losers: " + losers.get() + " (exactly one winner)");
    }
}
