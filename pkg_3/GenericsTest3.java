/**
 * 
 */
package pkg_3;

import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class GenericsTest3 {

	class Animal {}
	class Dog extends Animal {}
	class Cat extends Animal {}
	class DogKitman extends Dog {}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GenericsTest3 gt3 = new GenericsTest3();
		gt3.test1();
	}

	private void test1() {
		// List<Animal> ani1 = new LinkedList<DogKitman>();
		List<? extends Animal> ani2 = new LinkedList<DogKitman>();
		List<DogKitman> ani3 = new LinkedList<DogKitman>();
		
	}
	
	private void checkAnimal( List<? extends Animal> list ) {
		System.out.println( list.get(0) );
		System.out.println( list.add(null) );
		// System.out.println( list.add(new Object()) ); // DNC
		// System.out.println( list.add(new Cat()) );
	}
	
	private void addAnimal( List<? super Dog> list ) {
		System.out.println( list.add(null) );
		System.out.println( list.add(new Dog()) );
		//System.out.println( list.add(new Cat()) );
		System.out.println( list.add( new DogKitman() )  );
	}
	
//	private void addAnimal( List<Object> list ) {
//		ERASURE of m() addAnimal is same method as another m() in this class
//	}
}
