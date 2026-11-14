package strategy;

import entities.Room;

import java.util.List;

// Simplest policy: the first room that fits, in registration order
public class FirstAvailableRoomStrategy implements RoomSelectionStrategy {

    @Override
    public Room select(List<Room> candidates) {
        return candidates.get(0);
    }
}
