package followingstrategypattern.Strategy;

public class SpecialDrivingStrategy implements Strategy {
    @Override
    public void drive() {
        System.out.println("special driving capability");
    }
}
