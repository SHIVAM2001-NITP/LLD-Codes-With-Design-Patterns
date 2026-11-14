package service;

import entities.TimeSlot;

import java.util.ArrayList;
import java.util.List;

// The booked slots of ONE resource (a person or a room). Both are scheduled the same way,
// so one class serves both: a resource is free for a slot if nothing booked overlaps it.
public class Calendar {
    private final List<TimeSlot> booked = new ArrayList<>();

    public boolean isFree(TimeSlot slot) {
        for (TimeSlot existing : booked) {
            if (existing.overlaps(slot)) {
                return false;
            }
        }
        return true;
    }

    public void book(TimeSlot slot) { booked.add(slot); }

    public void release(TimeSlot slot) { booked.remove(slot); }
}
