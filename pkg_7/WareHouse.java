/**
 * 
 */
package pkg_7;

/**
 * A place where goods are stored and sold to customers
 *
 */
public class WareHouse<T> {

	private T storageType;
	
	private int maxCapacity = 99;
	
	private int itemsCount;
	
	public WareHouse( int maxCapacity, int initialItemCount, T t ) {
		this.storageType = t;
		
		this.maxCapacity = maxCapacity;
		this.itemsCount = initialItemCount;
		System.out.println("warehouse created for " + storageType + ", maxCapacity : " + maxCapacity + ", in store : " + initialItemCount);
	}
	
	public int getItemCount() {
		return itemsCount;
	}
	
	/**
	 * Stores items in warehouse
	 * @param 
	 * @return count of items stored
	 * */
	public synchronized void storeItems( int count ) {
		System.out.println(Thread.currentThread().getName() +  " wants store : " + count + ", itemsCount = " + itemsCount);
		while( (itemsCount + count) > maxCapacity ) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if ( (itemsCount + count) < maxCapacity ) { 
				itemsCount += count;
				this.notify();
				System.out.println(Thread.currentThread().getName() +  " stored : " + count);
				return;
			}
		}
		itemsCount += count;
		this.notify();
		System.out.println(Thread.currentThread().getName() +  " stored : " + count);		
	}
	
	/**
	 * Sell items to customer
	 * 
	 * @param item count customer wants to buy
	 * @return item count sold
	 * */
	public synchronized void sellItems( int count ) {
		System.out.println(Thread.currentThread().getName() +  " wants buy : " + count + ", itemsCount = " + itemsCount);
		int itemsBought = 0;
		while ( itemsCount < count ) {
			try {
				this.wait();
				if ( itemsCount > count ) {
					itemsCount -= count;
					this.notify();
					System.out.println(Thread.currentThread().getName() +  " bought : " + count);
					return;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		itemsCount -= count;
		this.notify();
		System.out.println(Thread.currentThread().getName() +  " bought : " + count);		
	}
	
	@Override
	public String toString () {
		return "[Warehouse for " + storageType + " with " + itemsCount + " items in store]";
	}
	
}
