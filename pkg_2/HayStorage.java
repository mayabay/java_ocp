package pkg_2;
public class HayStorage {
	
	private int quantity = 0;
	
	private HayStorage(){}

	private final static HayStorage instance  = new HayStorage();

	public static HayStorage getInstance(){
		return instance;
	}

	public synchronized void addHay(int amount){
		quantity += amount;
	}

	public synchronized boolean removeHay ( int amount ) {
		if (quantity < amount) return false;
		quantity -= amount;
		return true;
	} 

	public synchronized int getHayQuantitiy(){
		return quantity;
	}



}
