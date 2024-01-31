package LowLevelDesign.HandleNullObject;

import LowLevelDesign.HandleNullObject.Vehicle.Car;
import LowLevelDesign.HandleNullObject.Vehicle.NullVehicle;
import LowLevelDesign.HandleNullObject.Vehicle.Vehicle;

public class VehicleFactory {


    static Vehicle getVehicleObject(String typeOfVehicle){

        if("Car".equals(typeOfVehicle)) {

            return new Car();
        }

        return new NullVehicle();
    }
}
