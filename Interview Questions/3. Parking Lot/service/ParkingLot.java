package service;

import entities.ParkingFloor;
import entities.ParkingSpot;
import entities.ParkingTicket;
import entities.Vehicle;
import enums.SpotType;
import exceptions.InvalidTicketException;
import exceptions.ParkingFullException;
import strategy.PricingStrategy;
import strategy.SpotAssignmentStrategy;

import java.time.Clock;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Entry point of the system: park() at the entry gate, unpark() at the exit gate.
// The two decisions that vary (which spot, how much) are injected as strategies.
public class ParkingLot {
    private final List<ParkingFloor> floors;
    private final SpotAssignmentStrategy assignmentStrategy;
    private final PricingStrategy pricingStrategy;
    private final Clock clock;
    private final Map<String, ParkingTicket> activeTickets = new HashMap<>();
    private final Set<String> parkedPlates = new HashSet<>();
    private int ticketCounter = 0;

    public ParkingLot(List<ParkingFloor> floors, SpotAssignmentStrategy assignmentStrategy,
                      PricingStrategy pricingStrategy, Clock clock) {
        this.floors = floors;
        this.assignmentStrategy = assignmentStrategy;
        this.pricingStrategy = pricingStrategy;
        this.clock = clock;
    }

    // synchronized: two gates must never hand the same spot to two vehicles
    public synchronized ParkingTicket park(Vehicle vehicle) {
        if (parkedPlates.contains(vehicle.getPlateNumber())) {
            throw new IllegalStateException(vehicle + " is already parked");
        }
        SpotType needed = SpotType.forVehicle(vehicle.getType());
        ParkingSpot spot = assignmentStrategy.findSpot(floors, needed)
                .orElseThrow(() -> new ParkingFullException("No free " + needed + " spot for " + vehicle));

        spot.park(vehicle);
        ParkingTicket ticket = new ParkingTicket("T-" + (++ticketCounter), vehicle, spot, clock.instant());
        activeTickets.put(ticket.getId(), ticket);
        parkedPlates.add(vehicle.getPlateNumber());
        return ticket;
    }

    // returns the fee to pay and frees the spot
    public synchronized long unpark(String ticketId) {
        ParkingTicket ticket = activeTickets.remove(ticketId);
        if (ticket == null) {
            throw new InvalidTicketException("Unknown or already used ticket: " + ticketId);
        }
        long fee = pricingStrategy.calculateFee(ticket, clock.instant());
        ticket.getSpot().free();
        parkedPlates.remove(ticket.getVehicle().getPlateNumber());
        return fee;
    }

    public synchronized void displayAvailability() {
        for (ParkingFloor floor : floors) {
            System.out.println("  Floor " + floor.getNumber()
                    + ": BIKE " + floor.freeSpots(SpotType.BIKE)
                    + ", COMPACT " + floor.freeSpots(SpotType.COMPACT)
                    + ", LARGE " + floor.freeSpots(SpotType.LARGE) + " free");
        }
    }
}
