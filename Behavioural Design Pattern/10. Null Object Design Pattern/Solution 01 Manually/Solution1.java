public class Solution1 {
        private static void printVehichleDetail(Vehicle vehicle){
            if(vehicle!=null){
                System.out.println("Seating Capacity: "+vehicle.getSeatingCapacity());
                System.out .println("Fuel Tank Capacity: "+vehicle.getTankCapacity());
            }

        }
        //IT will Avoid the NULL Pointer Exception when Vehicle object is NULL but suppose if there were so many large code base then there we have to put at everywhere this if condition
}
