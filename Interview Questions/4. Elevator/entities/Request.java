package entities;

import enums.Direction;

// A hall call: someone on `floor` pressed the UP or DOWN button
public class Request {
    private final int floor;
    private final Direction direction;

    public Request(int floor, Direction direction) {
        this.floor = floor;
        this.direction = direction;
    }

    public int getFloor() { return floor; }

    public Direction getDirection() { return direction; }

    @Override
    public String toString() {
        return "floor " + floor + " " + direction;
    }
}
