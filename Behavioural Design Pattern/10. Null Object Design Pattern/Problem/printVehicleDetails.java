public class printVehicleDetails {

    private static void printVehichleDetail(Vehicle vehicle){
        System.out.println("Seating Capacity: "+vehicle.getSeatingCapacity());
        System.out .println("Fuel Tank Capacity: "+vehicle.getTankCapacity());
    }
    //IT will throw NULL Pointer Exception when Vehicle object is NULL
}
