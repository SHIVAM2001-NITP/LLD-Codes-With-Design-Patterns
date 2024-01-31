package followingstrategypattern;

import followingstrategypattern.Strategy.Strategy;

public class ParentVehicle {
	
	Strategy obj;
	
	ParentVehicle(Strategy obj ){
		this.obj=obj;
	}
	public void drive() {
		obj.drive();
		
	}

}
