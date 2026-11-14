package strategy;

import entities.Room;

import java.util.List;

// Strategy: given the rooms that are already free AND big enough, choose one.
public interface RoomSelectionStrategy {
    Room select(List<Room> candidates);
}
