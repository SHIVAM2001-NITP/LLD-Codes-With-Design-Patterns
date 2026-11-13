package entities;

import enums.Direction;

import java.util.TreeSet;

// One car. It serves stops with the LOOK algorithm: keep going in one direction while there are
// stops ahead, then reverse. Stops are kept in two sorted sets so the next stop is found in O(log n).
public class Elevator {
    private final int id;
    private int currentFloor;
    private Direction direction = Direction.IDLE;
    private final TreeSet<Integer> upStops = new TreeSet<>();      // floors to visit while going up
    private final TreeSet<Integer> downStops = new TreeSet<>();    // floors to visit while going down

    public Elevator(int id, int startFloor) {
        this.id = id;
        this.currentFloor = startFloor;
    }

    public int getId() { return id; }

    public int getCurrentFloor() { return currentFloor; }

    public Direction getDirection() { return direction; }

    public int pendingStops() { return upStops.size() + downStops.size(); }

    // A floor above goes to the up set, below to the down set, the current floor to the set of the travel direction
    public void addStop(int floor) {
        if (floor > currentFloor || (floor == currentFloor && direction != Direction.DOWN)) {
            upStops.add(floor);
        } else {
            downStops.add(floor);
        }
    }

    // Would a hall call for (floor, direction) be picked up on the way, without reversing?
    public boolean canServeOnTheWay(Request request) {
        switch (direction) {
            case IDLE:
                return true;
            case UP:
                return request.getDirection() == Direction.UP && request.getFloor() >= currentFloor;
            default:
                return request.getDirection() == Direction.DOWN && request.getFloor() <= currentFloor;
        }
    }

    public int distanceTo(int floor) {
        return Math.abs(floor - currentFloor);
    }

    // One tick: either open the doors at a stop or move one floor. Returns what happened, or null if idle.
    public String step() {
        chooseDirection();
        if (direction == Direction.IDLE) {
            return null;
        }
        TreeSet<Integer> stops = direction == Direction.UP ? upStops : downStops;

        if (stops.remove(currentFloor)) {                      // a stop on the floor we are standing on
            return "Elevator " + id + ": doors open at floor " + currentFloor;
        }
        int from = currentFloor;
        currentFloor += direction == Direction.UP ? 1 : -1;
        String event = "Elevator " + id + ": " + from + " -> " + currentFloor + " (" + direction + ")";
        if (stops.remove(currentFloor)) {
            event += ", doors open";
        }
        return event;
    }

    private void chooseDirection() {
        if (direction == Direction.UP && upStops.isEmpty()) {
            direction = Direction.IDLE;
        } else if (direction == Direction.DOWN && downStops.isEmpty()) {
            direction = Direction.IDLE;
        }
        if (direction == Direction.IDLE) {                      // nothing ahead: take whatever is waiting
            if (!upStops.isEmpty()) {
                direction = Direction.UP;
            } else if (!downStops.isEmpty()) {
                direction = Direction.DOWN;
            }
        }
    }
}
