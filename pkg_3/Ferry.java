package pkg_3;
import java.util.*;
class Ferry {
	
	public static void main(String[] args){
		go2();
	}

	private static void go2(){
		TreeSet<Dog> dogs = new TreeSet<>();
		dogs.add( new Dog("magnolia") );
		dogs.add( new Dog("clover") );
		dogs.add( new Dog("martin") );
		dogs.add( new Dog("aiko") );

		System.out.println( dogs );

		System.out.println(  dogs.floor( new Dog("clover") ));  // clover

		System.out.println(  dogs.lower( new Dog("clover") ));	// aiko

		//System.out.println( dogs.pollFirst() ); // aiko

		//System.out.println( dogs.pollLast() ); // martin

		NavigableSet<Dog> nSetReverse = dogs.descendingSet();

		
		System.out.println( nSetReverse );


	} 



	private static void go(){
		TreeSet<Integer> times = new TreeSet<>();
		times.add( 1205 );
		times.add( 1505 );
		times.add( 1545 );
		times.add( 1830 );
		times.add( 2010 );
		times.add( 2100 );

		TreeSet<Integer> subset = new TreeSet<Integer>();
		subset = (TreeSet<Integer>)times.headSet( 1600 );
		System.out.println( subset );

		System.out.println("last in subset: " + subset.last() ); // 1545


		System.out.println("floor:  " +  times.floor( 1205 ) );

		System.out.println("lower: " + times.lower( 1205 ) );
		
		System.out.println("ceiling: " + times.ceiling( 2010 ) );

		System.out.println("higher:  " + times.higher( 2010 ) );


  	}


}
