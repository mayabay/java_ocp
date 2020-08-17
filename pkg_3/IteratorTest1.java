package pkg_3;
import java.util.*;
class IteratorTest1{
	
	public static void main(String[] args){
		go();
	}

	private static void go(){

		//List<Dog> dogs = new LinkedList();	// warn: raw -> gen
		Queue<Dog> dogs = new LinkedList<>();	

		Dog d1 = new Dog( "aiko" );

		dogs.add( d1 );
		dogs.add( new Dog("clover") );
		dogs.add( new Dog("magnolia") );

		//Collections.sort( dogs );
		System.out.println( dogs );

		System.out.println( dogs.peek() );	// aiko

		

	}

}
