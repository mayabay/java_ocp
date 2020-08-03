package plants;
import java.util.*;
/** 
 * A cardboard box for plants of type T and 
 * some unique identifier of type S
 * */
public class CardboardBox<T extends Plant, S> implements Box<T> {

	/**
	 * No-arg constructor 
	 * */
	public CardboardBox(){}

	/**
	 * constructor 
	 * */
	// <S> declared here is another S as in loc 3
	public CardboardBox( S id ){
		this.id = id; 
	}

	// static field with a type parameter is not allowed
	//private static S sID;	// DNC 12: error: non-static type variable S cannot be referenced from a static context

	// what is S? It must be declared in class scope so in line 3
	private S id;

	private ArrayList<T> list = new ArrayList<>();

	// <S> declared here is another S as in loc 3 
	/** 
	 * getter for Id
	 * @return S id
	 * */
	public S getId(){
		return this.id;
	}

	/**
	 * Add T to box (add to list)
	 * @return true if successfull
	 * */
	public boolean add(T t){
		return list.add( t );
	}
	
	/**
	 * Remove t from box
	 * @return true if successfull
	 * */
	public boolean remove(T t){
		return list.remove(t);
	}

	/**
	 * Removes and returns t from box
	 * @return T not in box anymore
	 * */
	public T removeAndGet(T t){
		return list.remove( list.indexOf(t) ); 
	}

	/**
	 * Getter for T by index
	 * @return T at index 
	 * */
	public T get( int index ){
		return list.get(index);
	}

	/**
	 * Returns count of box elements
	 * @return number of elements
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

	/**
	 * String representation of this object
	 * @return string representation
	 * */
	public String toString(){
		StringBuffer type = new StringBuffer();
		if ( list.size() > 0 ){
			Object o = list.get(0);
			Class<?> cl = o.getClass();
			type.append( cl.getName() );
		}else{
			type.append("unknown");
		}
		return "CardboardBox with " + list.size() + " items of type: " + type;
	}

}
