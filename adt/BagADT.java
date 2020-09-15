package adt;

/**
 * ADT Bag
 * */
public interface BagADT<T> {

	/**
	 * Returns the current size of the bag.
	 * 
	 * @return element count in bag
	 * */
	int getCurrentSize();	
	
	/**
	 * Checks weather the bag is empty.
	 * 
	 * @return true, if empty
	 * */
	boolean isEmpty();
	
	/**
	 * Addd element t to bag.
	 * 
	 * @return true, if t was added
	 * */
	boolean add(T t);
	
	/**
	 * Removes an unspecified entry from the bag.
	 * 
	 * @return the removed entry or null 
	 * */
	T remove();
	
	/**
	 * Removes the specified entry from the bag
	 * 
	 * @return true, if successful
	 * */
	boolean remove(T t);
	
	/**
	 * Removes all entries from the bag
	 * */
	void clear();
	
	/**
	 * Returns the number of occurrences of t
	 * 
	 * @return 
	 * */
	boolean getFreqencyOf(T t);
	
	/**
	 * 
	 * */
	boolean contains(T t);
	
	
	
	
	
}
