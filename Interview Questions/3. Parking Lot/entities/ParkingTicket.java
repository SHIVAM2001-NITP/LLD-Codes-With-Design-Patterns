package entities;

import java.time.Instant;

// Issued at the entry gate, handed back at the exit gate
public class ParkingTicket {
    private final String id;
    private final Vehicle vehicle;
    private final ParkingSpot spot;
    private final Instant entryTime;

    public ParkingTicket(String id, Vehicle vehicle, ParkingSpot spot, Instant entryTime) {
        this.id = id;
        this.vehicle = vehicle;
        this.spot = spot;
        this.entryTime = entryTime;
    }

    public String getId() { return id; }

    public Vehicle getVehicle() { return vehicle; }

    public ParkingSpot getSpot() { return spot; }

    public Instant getEntryTime() { return entryTime; }

    @Override
    public String toString() {
        return "Ticket " + id + " [" + vehicle + " -> spot " + spot.getId() + "]";
    }
}
