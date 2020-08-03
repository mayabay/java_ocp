package pkg_3;
import plants.*;
import java.util.*;
/** Learn Set Interface */
public class SetTest1 {
	
	public static void main(String[] args){
		do3();

	}

	/** Exercise with bounded wildcards */
	static void do1(){

		Box<? extends Plant> aBox = packFruitBox(42); 

		Set<Plant> plants = new HashSet<>();
		for( int i = 0; i < aBox.size(); i++ ){
			plants.add( aBox.get(i) );
		}

		Iterator<Plant> iter = plants.iterator();
		while( iter.hasNext() ){
			Plant p = iter.next();
			System.out.println( p );
		}
	}

	/*
	 * Box is defined as Box<T extends Plant>
	 * => Box<? extends Sweet> is not in bounds of T
	 * */
	//private static Box<? extends Sweet> packFruitBox( int count ){
	private static Box<? extends Plant> packFruitBox( int count ){
		Box<Ananas> anas = new BoxBanana<>();
		for ( int i = 0; i < count; i++ ){
			anas.add( new Hilo() );
		}

		return anas;
	}

	/** SortedSet and NavigableSet */
	static void do2(){
		SortedSet<Integer> soSet = new TreeSet<>();
		soSet.addAll( Arrays.asList(3,4,8,9,11) );
		
		System.out.println( soSet.first() );	// 3

		System.out.println( soSet.last() );		// 11

		NavigableSet<Integer> ns = (NavigableSet<Integer>)soSet;
		System.out.println( ns.lower(4) );		// 3
		System.out.println( ns.lower(3) );		// null

		System.out.println( ns.higher(9) );		// 11
		System.out.println( ns.higher(10) );	// 11

		System.out.println( ns.floor(4) );		// 4

		System.out.println( ns.floor(5) );		// 4
		
		System.out.println( ns.floor(2) );		// null

	}

	/** Working with a Queue */
	static void do3(){
		
		Queue<Citrus> q1 = new LinkedList<>();
		q1.addAll(  Arrays.asList( new Lime("Lime1"), new Lemon("Lemon2"), new Orange("Orange3")  ) );

		// queue has beginning (tail) and end (head)


		// add() or offer()  
		//q1.add( new Orange("Orange4") );
		q1.offer( new Orange("Orange4") );

		// 4 3 2 1 

		// check end
		System.out.println( q1.element() );		// Lime1 (1)
		System.out.println( q1.poll() );		// removes Lime1 (1)
		
		// remove from end remove() or poll()
		System.out.println( q1.remove() ); 		// removes (2)

		System.out.println( q1.peek() ) ;		// shows (3)
		
		System.out.println( q1.poll() );		// poll 3

		System.out.println( q1.peek() );		// show 4



	}
	
	private static void pColl( Collection<?> coll ){
		Iterator<?> iter = coll.iterator();
		while ( iter.hasNext() ){
			System.out.print( iter.next() );
		}
	}

}
