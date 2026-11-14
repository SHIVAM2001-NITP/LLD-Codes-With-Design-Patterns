package service;

import entities.Meeting;
import entities.Room;
import entities.TimeSlot;
import entities.User;
import enums.MeetingStatus;
import exceptions.MeetingNotFoundException;
import exceptions.NoRoomAvailableException;
import exceptions.UserBusyException;
import observer.MeetingObserver;
import strategy.RoomSelectionStrategy;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

// Single entry point. Scheduling is all-or-nothing: every attendee AND a room must be free
// for the slot, otherwise nothing is booked. Every method is synchronized so that two
// concurrent requests can never both grab the same person or room.
public class MeetingScheduler {
    private final List<Room> rooms = new ArrayList<>();
    private final Map<String, Calendar> userCalendars = new HashMap<>();
    private final Map<String, Calendar> roomCalendars = new HashMap<>();
    private final Map<String, Meeting> meetings = new HashMap<>();
    private final List<MeetingObserver> observers = new ArrayList<>();
    private final RoomSelectionStrategy roomStrategy;
    private int counter = 0;

    public MeetingScheduler(RoomSelectionStrategy roomStrategy) {
        this.roomStrategy = roomStrategy;
    }

    public void addRoom(Room room) {
        rooms.add(room);
        roomCalendars.put(room.getId(), new Calendar());
    }

    public void addObserver(MeetingObserver observer) { observers.add(observer); }

    public synchronized Meeting schedule(String title, User organizer, List<User> invitees, TimeSlot slot) {
        Set<User> attendees = new LinkedHashSet<>();     // de-duplicates; organizer always attends
        attendees.add(organizer);
        attendees.addAll(invitees);

        for (User user : attendees) {
            if (!calendarOf(user).isFree(slot)) {
                throw new UserBusyException(user + " is busy during " + slot);
            }
        }
        Room room = pickRoom(attendees.size(), slot);

        Meeting meeting = new Meeting("M-" + (++counter), title, organizer, new ArrayList<>(attendees), room, slot);
        for (User user : attendees) {
            calendarOf(user).book(slot);
        }
        roomCalendars.get(room.getId()).book(slot);
        meetings.put(meeting.getId(), meeting);

        for (MeetingObserver o : observers) o.onScheduled(meeting);
        return meeting;
    }

    public synchronized void cancel(String meetingId) {
        Meeting meeting = meetings.get(meetingId);
        if (meeting == null || meeting.getStatus() == MeetingStatus.CANCELLED) {
            throw new MeetingNotFoundException("No active meeting " + meetingId);
        }
        for (User user : meeting.getAttendees()) {
            calendarOf(user).release(meeting.getSlot());
        }
        roomCalendars.get(meeting.getRoom().getId()).release(meeting.getSlot());
        meeting.setStatus(MeetingStatus.CANCELLED);

        for (MeetingObserver o : observers) o.onCancelled(meeting);
    }

    // Earliest slot of the given length, starting no earlier than `from`, where everyone is free
    // and some room fits. Scans in 30 minute steps up to `until`.
    public synchronized Optional<TimeSlot> findEarliestSlot(List<User> attendees, Duration length,
                                                             LocalDateTime from, LocalDateTime until) {
        for (LocalDateTime start = from; !start.plus(length).isAfter(until); start = start.plusMinutes(30)) {
            TimeSlot candidate = new TimeSlot(start, start.plus(length));
            boolean everyoneFree = attendees.stream().allMatch(u -> calendarOf(u).isFree(candidate));
            if (everyoneFree && !freeRooms(attendees.size(), candidate).isEmpty()) {
                return Optional.of(candidate);
            }
        }
        return Optional.empty();
    }

    private Room pickRoom(int attendeeCount, TimeSlot slot) {
        List<Room> candidates = freeRooms(attendeeCount, slot);
        if (candidates.isEmpty()) {
            throw new NoRoomAvailableException("No free room for " + attendeeCount + " people during " + slot);
        }
        return roomStrategy.select(candidates);
    }

    private List<Room> freeRooms(int attendeeCount, TimeSlot slot) {
        List<Room> result = new ArrayList<>();
        for (Room room : rooms) {
            if (room.getCapacity() >= attendeeCount && roomCalendars.get(room.getId()).isFree(slot)) {
                result.add(room);
            }
        }
        return result;
    }

    private Calendar calendarOf(User user) {
        return userCalendars.computeIfAbsent(user.getId(), k -> new Calendar());
    }
}
