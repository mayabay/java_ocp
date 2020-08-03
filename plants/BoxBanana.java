package plants;
import java.util.*;
/**
 * A cardboardbox with fixed dimensions primarily used to transport bananas.
 *
 * */
public class BoxBanana<T extends Plant> implements Box<T> {

	/** Length of the box in m */
	public static final double length = 0.54;
	/** Width of the box in m */
	public static final double width = 0.39;
	/** Height of the box in m */
	public static final double height = 0.24;
	/** Volume of the box m*m*m */
	public static final double volume = 0.05;

	/* lists the contents od the box */
	private List<T> list = new ArrayList<T>();

	/** 
	 * Add T to the box
	 * @return true if successfull
	 * */
	public boolean add( T t ){
		return list.add( t );
	}

	/**
	 * Removes T from the box
	 * @return true, if successfull
	 * */
	public boolean remove( T t){
		return list.remove(t);
	}

	/**
	 * Look at an element, will not remove it
	 * @return T
	 * */
	public T get( int idx ){
		return list.get( idx );
	}

	/**
	 * Removes and returns T from the box
	 * @return T
	 * */
	public T removeAndGet( T t ){
		return list.remove( list.indexOf(t) );
	}

	/**
	 * Returns number of T in the box
	 * @return element count
	 * */
	public int size(){
		return list.size();
	}

	/**
	 * Returns T from box at specified index
	 * @param index postition
	 * @return T at that position, not in box anymore
	 * */
	public T remove(int index){
		return list.get( index );
	}

}
