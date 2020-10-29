/**
 * 
 */
package pkg_7;

import java.util.Random;

/**
 * Workers storing  items in warehouse and customers
 * are buying items from warehouse
 */
public class WareHouseBusiness {
	
	private class Wood{
		@Override
		public String toString() {
			return "[Wood]";
		}
	}
	
	private WareHouse<Wood> wareHouse;
	
	private Random random = new Random();
	
	private int storeEvents = 23;
	private int buyEvents = 22;
	
	private Runnable storingItems = () -> {
		for ( int i = 0; i < storeEvents; i++ ) {  
			int itemCountToStore = random.nextInt(99);
			this.wareHouse.storeItems(itemCountToStore);
			if ( buyEvents == 0 ) { System.out.println("storing: returns"); return; } 
			this.sleep(1500);
		}
	};
	
	private Runnable sellingItems = () -> {
		for ( int i = 0; i < buyEvents; i++ ) { 
			int itemCountToBuy = random.nextInt(99);
			this.wareHouse.sellItems(itemCountToBuy);
			if (storeEvents == 0) { System.out.println("buying: returns"); return; }
			this.sleep(1500);
		}
	};
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		WareHouseBusiness whb = new WareHouseBusiness();
		whb.wareHouse = new WareHouse<WareHouseBusiness.Wood>(250, whb.random.nextInt(27), whb.new Wood() );
		//System.out.println("warehouse created : " + whb.wareHouse);

		Thread t1 = new Thread( whb.storingItems );
		t1.setName("Fred");
		
		Thread t2 = new Thread( whb.sellingItems );
		t2.setName("Lucy");
		
		t1.start();
		whb.sleep(1500);
		t2.start();
		
		System.out.println("main thread ended");
		
	}

	private void sleep(long millis) {
		try {
			Thread.sleep(millis);
		}catch ( InterruptedException e ) {
			e.printStackTrace();
		}
		
	}
	
}
