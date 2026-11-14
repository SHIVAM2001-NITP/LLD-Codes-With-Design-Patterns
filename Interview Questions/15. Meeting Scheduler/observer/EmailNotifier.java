package observer;

import entities.Meeting;
import entities.User;

// Simulates emailing every attendee an invite or a cancellation
public class EmailNotifier implements MeetingObserver {

    @Override
    public void onScheduled(Meeting meeting) {
        for (User u : meeting.getAttendees()) {
            System.out.println("  [Email -> " + u.getEmail() + "] Invite: '" + meeting.getTitle() + "' " + meeting.getSlot()
                    + " in " + meeting.getRoom().getName());
        }
    }

    @Override
    public void onCancelled(Meeting meeting) {
        for (User u : meeting.getAttendees()) {
            System.out.println("  [Email -> " + u.getEmail() + "] Cancelled: '" + meeting.getTitle() + "'");
        }
    }
}
