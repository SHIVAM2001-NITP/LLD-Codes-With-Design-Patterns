package followingstrategypattern;

import followingstrategypattern.Strategy.SpecialDrivingStrategy;

public class SportsVehicle extends ParentVehicle {

	SportsVehicle() {
		super(new SpecialDrivingStrategy());
	}

}
