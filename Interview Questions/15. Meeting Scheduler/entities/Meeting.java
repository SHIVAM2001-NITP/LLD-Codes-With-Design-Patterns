package entities;

import enums.MeetingStatus;

import java.util.List;

public class Meeting {
    private final String id;
    private final String title;
    private final User organizer;
    private final List<User> attendees;   // includes the organizer
    private final Room room;
    private final TimeSlot slot;
    private MeetingStatus status = MeetingStatus.SCHEDULED;

    public Meeting(String id, String title, User organizer, List<User> attendees, Room room, TimeSlot slot) {
        this.id = id;
        this.title = title;
        this.organizer = organizer;
        this.attendees = attendees;
        this.room = room;
        this.slot = slot;
    }

    public String getId() { return id; }

    public String getTitle() { return title; }

    public User getOrganizer() { return organizer; }

    public List<User> getAttendees() { return attendees; }

    public Room getRoom() { return room; }

    public TimeSlot getSlot() { return slot; }

    public MeetingStatus getStatus() { return status; }

    public void setStatus(MeetingStatus status) { this.status = status; }

    @Override
    public String toString() {
        return id + " '" + title + "' " + slot + " in " + room.getName() + " " + attendees + " [" + status + "]";
    }
}
