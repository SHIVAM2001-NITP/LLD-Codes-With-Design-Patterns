import entities.Elevator;
import enums.Direction;
import exceptions.InvalidFloorException;
import service.ElevatorController;
import strategy.LeastLoadedElevatorStrategy;
import strategy.NearestElevatorStrategy;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // ---------- Building with floors 0..10 and two elevators ----------
        System.out.println("=== Nearest elevator strategy (floors 0..10, elevator 1 at 0, elevator 2 at 8) ===");
        ElevatorController controller = new ElevatorController(cars(0, 8), new NearestElevatorStrategy(), 0, 10);

        controller.requestElevator(3, Direction.UP);       // elevator 1 is closer
        controller.requestElevator(6, Direction.DOWN);     // elevator 2 is closer
        controller.run(3);                                  // elevator 1 reaches floor 3, elevator 2 is on its way
        controller.selectFloor(1, 9);                       // the passenger at floor 3 wants floor 9

        System.out.println("\n-- A call arrives while elevator 1 is already going up: it picks the caller up on the way --");
        controller.requestElevator(5, Direction.UP);
        controller.run(2);
        controller.selectFloor(2, 1);                       // the passenger at floor 6 wants floor 1
        controller.run(8);

        System.out.println("\n-- Invalid requests --");
        try {
            controller.requestElevator(11, Direction.DOWN);
        } catch (InvalidFloorException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
        try {
            controller.requestElevator(0, Direction.DOWN);
        } catch (InvalidFloorException e) {
            System.out.println("Rejected: " + e.getMessage());
        }

        // ---------- Same code, different strategy ----------
        System.out.println("\n=== Least loaded strategy (both elevators at floor 0) ===");
        ElevatorController balanced = new ElevatorController(cars(0, 0), new LeastLoadedElevatorStrategy(), 0, 10);
        balanced.requestElevator(4, Direction.UP);
        balanced.requestElevator(5, Direction.UP);
        balanced.requestElevator(6, Direction.UP);
        balanced.run(6);
    }

    private static List<Elevator> cars(int startOfFirst, int startOfSecond) {
        List<Elevator> list = new ArrayList<>();
        list.add(new Elevator(1, startOfFirst));
        list.add(new Elevator(2, startOfSecond));
        return list;
    }
}
