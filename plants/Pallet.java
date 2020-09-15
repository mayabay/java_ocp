package plants;

import java.util.Iterator;

/**
 * Pallet for storing various items
 * 
 * */
public interface Pallet<T, S> {

	/**
	 * returns the serial number of this pallet
	 * */
	public S getId();
	
	/**
	 * Returns pallets width
	 * 
	 * @return
	 * */
	double widthInMM();
	
	/**
	 * Returns pallets depth
	 * 
	 * @return
	 * */
	double depthInMM();
	
	/**
	 * Returns pallets height
	 * 
	 * @return
	 * */
	double heightInMM();
	
	/**
	 * Returns number of loaded items
	 * 
	 * @return
	 * */
	int items();
	
	/**
	 * Returns an iterator for the loaded items
	 * 
	 * */
	Iterator<T> iterator();
	
	/**
	 * Loads an item on to the pallet
	 * 
	 * @param T t
	 * @return true, if successful
	 * */
	boolean loadItem( T t );

	/**
	 * Unloads an item from the pallet
	 * 
	 * @param T t
	 * @return removed item 
	 * */	
	T unloadItem( T t );
	
	/**
	 * Removes all items  
	 * 
	 * */
	void clear();
	
}
