package service;

import entities.Elevator;
import entities.Request;
import enums.Direction;
import exceptions.InvalidFloorException;
import strategy.ElevatorSelectionStrategy;

import java.util.List;

// The panel of the building: hall calls come in through requestElevator(), the buttons inside a car through
// selectFloor(), and step() moves every elevator one tick. Which car answers a call is an injected strategy.
public class ElevatorController {
    private final List<Elevator> elevators;
    private final ElevatorSelectionStrategy selectionStrategy;
    private final int minFloor;
    private final int maxFloor;
    private int tick = 0;

    public ElevatorController(List<Elevator> elevators, ElevatorSelectionStrategy selectionStrategy, int minFloor, int maxFloor) {
        this.elevators = elevators;
        this.selectionStrategy = selectionStrategy;
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
    }

    // Hall call: someone outside pressed UP or DOWN
    public synchronized Elevator requestElevator(int floor, Direction direction) {
        checkFloor(floor);
        if (direction == Direction.IDLE) {
            throw new IllegalArgumentException("A hall call needs UP or DOWN");
        }
        if ((floor == maxFloor && direction == Direction.UP) || (floor == minFloor && direction == Direction.DOWN)) {
            throw new InvalidFloorException("No " + direction + " button on floor " + floor);
        }
        Request request = new Request(floor, direction);
        Elevator chosen = selectionStrategy.select(elevators, request);
        chosen.addStop(floor);
        System.out.println("Call at " + request + " -> elevator " + chosen.getId());
        return chosen;
    }

    // Destination button pressed inside an elevator
    public synchronized void selectFloor(int elevatorId, int floor) {
        checkFloor(floor);
        Elevator elevator = find(elevatorId);
        elevator.addStop(floor);
        System.out.println("Elevator " + elevatorId + ": passenger selects floor " + floor);
    }

    // Advance time by one tick and report what every moving elevator did
    public synchronized void step() {
        tick++;
        for (Elevator elevator : elevators) {
            String event = elevator.step();
            if (event != null) {
                System.out.println("  [t=" + tick + "] " + event);
            }
        }
    }

    public synchronized void run(int ticks) {
        for (int i = 0; i < ticks; i++) {
            step();
        }
    }

    private Elevator find(int id) {
        for (Elevator e : elevators) {
            if (e.getId() == id) {
                return e;
            }
        }
        throw new IllegalArgumentException("No elevator with id " + id);
    }

    private void checkFloor(int floor) {
        if (floor < minFloor || floor > maxFloor) {
            throw new InvalidFloorException("Floor " + floor + " does not exist (" + minFloor + ".." + maxFloor + ")");
        }
    }
}
