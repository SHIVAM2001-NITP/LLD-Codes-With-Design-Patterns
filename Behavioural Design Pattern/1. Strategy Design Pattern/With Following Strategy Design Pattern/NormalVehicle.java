package followingstrategypattern;

import followingstrategypattern.Strategy.NormalDriveStrategy;

public class NormalVehicle extends ParentVehicle{

	NormalVehicle() {
		super(new NormalDriveStrategy());
	
	}

}
