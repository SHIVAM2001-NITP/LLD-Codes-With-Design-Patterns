package service;

import entities.Customer;
import entities.DateRange;
import entities.Reservation;
import entities.Vehicle;
import enums.ReservationStatus;
import exceptions.InvalidReservationException;
import exceptions.VehicleNotAvailableException;
import strategy.PricingStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Single entry point: search, book, cancel and return. Availability is computed from existing
// active reservations, not from a separate "is booked" flag, so no two steps can go out of sync.
public class RentalStore {
    private final List<Vehicle> fleet;
    private final PricingStrategy pricingStrategy;
    private final Map<String, Reservation> reservations = new HashMap<>();
    private int counter = 0;

    public RentalStore(List<Vehicle> fleet, PricingStrategy pricingStrategy) {
        this.fleet = fleet;
        this.pricingStrategy = pricingStrategy;
    }

    public synchronized List<Vehicle> search(enums.VehicleType type, DateRange period) {
        List<Vehicle> available = new ArrayList<>();
        for (Vehicle vehicle : fleet) {
            if (vehicle.getType() == type && isAvailable(vehicle, period)) {
                available.add(vehicle);
            }
        }
        return available;
    }

    public synchronized Reservation book(Customer customer, Vehicle vehicle, DateRange period) {
        if (!isAvailable(vehicle, period)) {
            throw new VehicleNotAvailableException(vehicle + " is not available for " + period);
        }
        long price = pricingStrategy.calculatePrice(vehicle.getType(), period);
        Reservation reservation = new Reservation("R-" + (++counter), customer, vehicle, period, price);
        reservations.put(reservation.getId(), reservation);
        return reservation;
    }

    public synchronized void cancel(String reservationId) {
        Reservation reservation = get(reservationId);
        if (reservation.getStatus() != ReservationStatus.ACTIVE) {
            throw new InvalidReservationException("Reservation " + reservationId + " is already " + reservation.getStatus());
        }
        reservation.setStatus(ReservationStatus.CANCELLED);
    }

    public synchronized void returnVehicle(String reservationId) {
        Reservation reservation = get(reservationId);
        if (reservation.getStatus() != ReservationStatus.ACTIVE) {
            throw new InvalidReservationException("Reservation " + reservationId + " is already " + reservation.getStatus());
        }
        reservation.setStatus(ReservationStatus.COMPLETED);
    }

    private boolean isAvailable(Vehicle vehicle, DateRange period) {
        for (Reservation reservation : reservations.values()) {
            if (reservation.getVehicle() == vehicle
                    && reservation.getStatus() == ReservationStatus.ACTIVE
                    && reservation.getPeriod().overlaps(period)) {
                return false;
            }
        }
        return true;
    }

    private Reservation get(String id) {
        Reservation reservation = reservations.get(id);
        if (reservation == null) {
            throw new InvalidReservationException("Unknown reservation: " + id);
        }
        return reservation;
    }
}
