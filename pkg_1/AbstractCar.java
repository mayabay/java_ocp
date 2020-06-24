package pkg_1;
public abstract class AbstractCar extends AbstractVehicle {
	//abstract void goUpHill();	// DNC 3: error: goUpHill() in AbstractCar cannot override goUpHill() in AbstractVehicle
	public abstract void goUpHill(); // when inherit + redeclare as abstract all Overrifing rules apply	

}
