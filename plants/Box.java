package plants;
//interface Box<T super Hilo > {
/**
 * Represents a container for plants as a list with indices
 * */
public interface Box<T extends Plant > {
	
	/** 
	 * Add a plant
	 * @param T t a plant
	 * */
	boolean add(T t);

	/**
	 * Remove plant ffrom box
	 * @param T t a plant
	 * @return true, if successfull
	 * */
	boolean remove(T t);

	/**
	 * Returns T from the box
	 * @param index position of 
	 * */
	T remove(int index);

	/**
	 * Returns a plant at a specific index position
	 * @param index list index of plant
	 * @return T a plant
	 * */
	T get( int index );

	/**
	 * Returns size of the collection
	 * @return element count
	 * */
	int size();
}
