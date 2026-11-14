package observer;

import entities.Meeting;

// Observer: each subscriber only overrides the events it cares about
public interface MeetingObserver {
    default void onScheduled(Meeting meeting) { }

    default void onCancelled(Meeting meeting) { }
}
