package followingstrategypattern;

import followingstrategypattern.Strategy.SpecialDrivingStrategy;

public class SportsCar extends ParentVehicle {

	SportsCar() {
		super(new SpecialDrivingStrategy());
	}

}
