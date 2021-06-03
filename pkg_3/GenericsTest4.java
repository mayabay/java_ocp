/**
 * 
 */
package pkg_3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

/**
 *
 */
public class GenericsTest4 {

	class Animal {}
	class Dog extends Animal {}
	class Cat extends Animal {}
	class DogKitman extends Dog {}	
	
	class MyGeneric<T extends Animal,R > { // DNC R super Dog
		T t;
		R r;
		MyGeneric( T t, R r ){
			this.t = t;
			this.r = r;
		}
		R compute(T t) {
			// return (R)t;	// warn: unchecked cast from T to R
			R r = null;
			return r;
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GenericsTest4 gt4 = new GenericsTest4();
		gt4.test1();
	}

	private void test1() {
		List<Dog> dogs = new Vector<>();
		//do1( dogs ); DNC not applicable
		
		List<?> dogs2 = new Vector<>();
		//do1( dogs2 ); DNC not applicable
		
		List list = new ArrayList(); 
		list.add(42);
		list.add("42");
		
		do1( list ); // type safety warning
		do3( list );	// raw to generic "back to the future"
		do2( list );
		
		List<String> listS = new LinkedList<>();
		listS.add( "42" );
		
		do2( listS );	// gen to raw, NO WARNING
	}
	
	private void do1( List<Object> list ) {
	}
	
	private void do2( List list ) {
		 list.add(new Cat());
	}

	private void do3( List<Integer> list ) {
		
	}	
	
}
