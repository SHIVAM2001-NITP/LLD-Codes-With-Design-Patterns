package entities;

import enums.ReservationStatus;

public class Reservation {
    private final String id;
    private final Customer customer;
    private final Vehicle vehicle;
    private final DateRange period;
    private final long totalPrice;
    private ReservationStatus status = ReservationStatus.ACTIVE;

    public Reservation(String id, Customer customer, Vehicle vehicle, DateRange period, long totalPrice) {
        this.id = id;
        this.customer = customer;
        this.vehicle = vehicle;
        this.period = period;
        this.totalPrice = totalPrice;
    }

    public String getId() { return id; }

    public Customer getCustomer() { return customer; }

    public Vehicle getVehicle() { return vehicle; }

    public DateRange getPeriod() { return period; }

    public long getTotalPrice() { return totalPrice; }

    public ReservationStatus getStatus() { return status; }

    public void setStatus(ReservationStatus status) { this.status = status; }

    @Override
    public String toString() {
        return "Reservation " + id + " [" + customer + " -> " + vehicle + ", " + period + ", $" + totalPrice + ", " + status + "]";
    }
}
