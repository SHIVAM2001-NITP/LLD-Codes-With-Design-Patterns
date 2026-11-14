import entities.Meeting;
import entities.Room;
import entities.TimeSlot;
import entities.User;
import exceptions.InvalidTimeSlotException;
import exceptions.NoRoomAvailableException;
import exceptions.UserBusyException;
import observer.EmailNotifier;
import service.MeetingScheduler;
import strategy.BestFitRoomStrategy;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Main {
    private static final LocalDate DAY = LocalDate.of(2026, 11, 16);

    private static TimeSlot slot(int startHour, int startMin, int endHour, int endMin) {
        return new TimeSlot(DAY.atTime(startHour, startMin), DAY.atTime(endHour, endMin));
    }

    public static void main(String[] args) {

        User alice = new User("U1", "Alice", "alice@corp.com");
        User bob = new User("U2", "Bob", "bob@corp.com");
        User carol = new User("U3", "Carol", "carol@corp.com");
        User dave = new User("U4", "Dave", "dave@corp.com");
        User erin = new User("U5", "Erin", "erin@corp.com");
        User frank = new User("U6", "Frank", "frank@corp.com");

        MeetingScheduler scheduler = new MeetingScheduler(new BestFitRoomStrategy());
        scheduler.addRoom(new Room("R1", "Focus", 2));
        scheduler.addRoom(new Room("R2", "Boardroom", 8));
        scheduler.addObserver(new EmailNotifier());

        System.out.println("=== 1. Alice + Bob, 10:00-11:00 (best-fit picks the small Focus room) ===");
        Meeting m1 = scheduler.schedule("Design sync", alice, Arrays.asList(bob), slot(10, 0, 11, 0));
        System.out.println(m1);

        System.out.println("\n=== 2. Carol + Dave at the same time (Focus taken -> Boardroom) ===");
        Meeting m2 = scheduler.schedule("Planning", carol, Arrays.asList(dave), slot(10, 0, 11, 0));
        System.out.println(m2);

        System.out.println("\n=== 3. Back-to-back is fine: Alice + Carol, 11:00-12:00 ===");
        Meeting m3 = scheduler.schedule("Review", alice, Arrays.asList(carol), slot(11, 0, 12, 0));
        System.out.println(m3);

        System.out.println("\n-- Rejections --");
        try {
            scheduler.schedule("Clash", bob, Arrays.asList(erin), slot(10, 30, 11, 30));
        } catch (UserBusyException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
        try {
            scheduler.schedule("No room", erin, Arrays.asList(frank), slot(10, 0, 11, 0));
        } catch (NoRoomAvailableException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
        try {
            slot(12, 0, 11, 0);
        } catch (InvalidTimeSlotException e) {
            System.out.println("Rejected: " + e.getMessage());
        }

        System.out.println("\n=== 4. Cancel the Design sync, which frees Alice, Bob and Focus ===");
        scheduler.cancel(m1.getId());

        System.out.println("\n=== 5. Erin + Frank can now take 10:00-11:00 ===");
        System.out.println(scheduler.schedule("Retro", erin, Arrays.asList(frank), slot(10, 0, 11, 0)));

        System.out.println("\n=== 6. Earliest 1h slot for Carol + Dave from 10:00 (both busy until 11:00; Carol also 11-12) ===");
        Optional<TimeSlot> found = scheduler.findEarliestSlot(Arrays.asList(carol, dave), Duration.ofHours(1),
                DAY.atTime(10, 0), DAY.atTime(18, 0));
        System.out.println(found.map(Object::toString).orElse("none found"));

        System.out.println("\n=== 7. Nobody is free for a 10h meeting in a 9h window ===");
        Optional<TimeSlot> none = scheduler.findEarliestSlot(Collections.singletonList(alice), Duration.ofHours(10),
                DAY.atTime(9, 0), DAY.atTime(18, 0));
        System.out.println(none.map(Object::toString).orElse("none found"));
    }
}
