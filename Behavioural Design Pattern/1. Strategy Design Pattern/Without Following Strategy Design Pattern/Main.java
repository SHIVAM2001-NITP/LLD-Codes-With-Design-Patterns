package withoutfollowing;
public class Main {
	public static void main(String[] args) {
		Vehicle v=new Vehicle();
		v.drive();
		
		Vehicle sv=new SportsVehicle();
		sv.drive();
		
		Vehicle sc= new SportsCar();
		sc.drive();

		Vehicle nv=new NormalVehicle();
		nv.drive();
	}
}
