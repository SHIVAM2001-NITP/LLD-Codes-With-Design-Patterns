package followingstrategypattern.Strategy;
public class NormalDriveStrategy implements Strategy {

	@Override
	public void drive() {
		System.out.println("normal driving capability");
	}

}
