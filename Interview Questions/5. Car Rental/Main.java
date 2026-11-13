import entities.Customer;
import entities.DateRange;
import entities.Reservation;
import entities.Vehicle;
import enums.VehicleType;
import exceptions.InvalidReservationException;
import exceptions.VehicleNotAvailableException;
import service.RentalStore;
import strategy.DailyRatePricingStrategy;
import strategy.PricingStrategy;
import strategy.WeekendDiscountPricingStrategy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Map<VehicleType, Long> rates = new EnumMap<>(VehicleType.class);
        rates.put(VehicleType.HATCHBACK, 30L);
        rates.put(VehicleType.SEDAN, 50L);
        rates.put(VehicleType.SUV, 80L);
        PricingStrategy pricing = new WeekendDiscountPricingStrategy(new DailyRatePricingStrategy(rates), 0.20);

        RentalStore store = new RentalStore(fleet(), pricing);
        Customer alice = new Customer("C1", "Alice");
        Customer bob = new Customer("C2", "Bob");

        System.out.println("=== Search SUVs for 2026-01-02 (Fri) to 2026-01-05 (Mon) ===");
        DateRange window = new DateRange(LocalDate.of(2026, 1, 2), LocalDate.of(2026, 1, 5));
        List<Vehicle> suvs = store.search(VehicleType.SUV, window);
        suvs.forEach(System.out::println);

        System.out.println("\n=== Alice books the first SUV (weekend discount applies) ===");
        Reservation r1 = store.book(alice, suvs.get(0), window);
        System.out.println(r1);

        System.out.println("\n-- Bob tries to book the same SUV for an overlapping window --");
        try {
            store.book(bob, suvs.get(0), new DateRange(LocalDate.of(2026, 1, 4), LocalDate.of(2026, 1, 6)));
        } catch (VehicleNotAvailableException e) {
            System.out.println("Rejected: " + e.getMessage());
        }

        System.out.println("\n-- The same SUV is free again for a non-overlapping window --");
        Reservation r2 = store.book(bob, suvs.get(0), new DateRange(LocalDate.of(2026, 1, 6), LocalDate.of(2026, 1, 7)));
        System.out.println(r2);

        System.out.println("\n=== Alice returns her car, Bob cancels his ===");
        store.returnVehicle(r1.getId());
        store.cancel(r2.getId());
        System.out.println(r1);
        System.out.println(r2);

        System.out.println("\n-- A completed reservation cannot be cancelled --");
        try {
            store.cancel(r1.getId());
        } catch (InvalidReservationException e) {
            System.out.println("Rejected: " + e.getMessage());
        }

        System.out.println("\n-- An unknown reservation id --");
        try {
            store.returnVehicle("R-999");
        } catch (InvalidReservationException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
    }

    private static List<Vehicle> fleet() {
        List<Vehicle> fleet = new ArrayList<>();
        fleet.add(new Vehicle("V1", "Honda Jazz", VehicleType.HATCHBACK));
        fleet.add(new Vehicle("V2", "Honda City", VehicleType.SEDAN));
        fleet.add(new Vehicle("V3", "Toyota Fortuner", VehicleType.SUV));
        fleet.add(new Vehicle("V4", "Hyundai Creta", VehicleType.SUV));
        return fleet;
    }
}
