package strategy;

import entities.Room;

import java.util.Comparator;
import java.util.List;

// Smallest room that still fits, so big rooms stay free for big meetings
public class BestFitRoomStrategy implements RoomSelectionStrategy {

    @Override
    public Room select(List<Room> candidates) {
        return candidates.stream().min(Comparator.comparingInt(Room::getCapacity)).get();
    }
}
