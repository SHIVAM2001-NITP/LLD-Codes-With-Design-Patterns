import entities.Bike;
import entities.Car;
import entities.ParkingFloor;
import entities.ParkingTicket;
import entities.Truck;
import enums.SpotType;
import enums.VehicleType;
import exceptions.InvalidTicketException;
import exceptions.ParkingFullException;
import service.ParkingLot;
import strategy.HourlyPricingStrategy;
import strategy.LeastOccupiedFloorStrategy;
import strategy.NearestSpotStrategy;
import strategy.PricingStrategy;
import util.ManualClock;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Map<VehicleType, Long> rates = new EnumMap<>(VehicleType.class);
        rates.put(VehicleType.BIKE, 20L);
        rates.put(VehicleType.CAR, 40L);
        rates.put(VehicleType.TRUCK, 100L);
        PricingStrategy pricing = new HourlyPricingStrategy(rates);

        // ---------- Lot 1: nearest spot first ----------
        System.out.println("=== Lot 1: nearest spot strategy ===");
        ManualClock clock = new ManualClock(Instant.parse("2026-01-01T09:00:00Z"));
        ParkingLot lot = new ParkingLot(buildFloors(), new NearestSpotStrategy(), pricing, clock);

        ParkingTicket bike = lot.park(new Bike("KA01-B-1"));
        ParkingTicket car1 = lot.park(new Car("KA01-C-1"));
        ParkingTicket car2 = lot.park(new Car("KA01-C-2"));
        ParkingTicket car3 = lot.park(new Car("KA01-C-3"));
        ParkingTicket truck = lot.park(new Truck("KA01-T-1"));
        for (ParkingTicket t : new ParkingTicket[]{bike, car1, car2, car3, truck}) {
            System.out.println("Issued " + t);
        }
        lot.displayAvailability();

        System.out.println("\n-- All compact spots are taken --");
        try {
            lot.park(new Car("KA01-C-4"));
        } catch (ParkingFullException e) {
            System.out.println("Rejected: " + e.getMessage());
        }

        System.out.println("\n-- Time passes, vehicles leave --");
        clock.advance(Duration.ofMinutes(150));
        System.out.println(car1.getVehicle() + " leaves after 2h30 -> fee " + lot.unpark(car1.getId()));
        clock.advance(Duration.ofMinutes(90));
        System.out.println(truck.getVehicle() + " leaves after 4h -> fee " + lot.unpark(truck.getId()));
        System.out.println(bike.getVehicle() + " leaves after 4h -> fee " + lot.unpark(bike.getId()));
        lot.displayAvailability();

        System.out.println("\n-- The freed compact spot is reused --");
        System.out.println("Issued " + lot.park(new Car("KA01-C-4")));

        System.out.println("\n-- Ticket cannot be used twice --");
        try {
            lot.unpark(car1.getId());
        } catch (InvalidTicketException e) {
            System.out.println("Rejected: " + e.getMessage());
        }

        // ---------- Lot 2: same code, different strategy ----------
        System.out.println("\n=== Lot 2: least occupied floor strategy ===");
        ParkingLot spread = new ParkingLot(buildFloors(), new LeastOccupiedFloorStrategy(), pricing, clock);
        for (int i = 1; i <= 3; i++) {
            System.out.println("Issued " + spread.park(new Car("KA02-C-" + i)));
        }
        spread.displayAvailability();
    }

    // Floor 1: 1 bike, 1 compact, 1 large.  Floor 2: 1 bike, 2 compact, 1 large.
    private static List<ParkingFloor> buildFloors() {
        ParkingFloor f1 = new ParkingFloor(1);
        f1.addSpot(SpotType.BIKE);
        f1.addSpot(SpotType.COMPACT);
        f1.addSpot(SpotType.LARGE);

        ParkingFloor f2 = new ParkingFloor(2);
        f2.addSpot(SpotType.BIKE);
        f2.addSpot(SpotType.COMPACT);
        f2.addSpot(SpotType.COMPACT);
        f2.addSpot(SpotType.LARGE);

        List<ParkingFloor> floors = new ArrayList<>();
        floors.add(f1);
        floors.add(f2);
        return floors;
    }
}
