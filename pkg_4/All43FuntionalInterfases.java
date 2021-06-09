/**
 * 
 */
package pkg_4;

import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 *
 */
public class All43FuntionalInterfases {

	private static volatile int id_source;
	private volatile int id;	
	
	class Animal { public String toString() { return "[Animal]"; } }
	class Dog extends Animal { public String toString() { return "[Dog]"; } }
	class Cat extends Animal { public String toString() { return "[Cat]"; } }
	
	class SecretBox<T> {
		private final int ID;
		T t;
		SecretBox( T t ){ this.t = t; ID = ++id_source;  }
		T getContent() { return t; }
		public String toString() { return "[SecretBox ("+ID+"): " + t.toString() + "]"; }
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		All43FuntionalInterfases a43fi = new All43FuntionalInterfases();
		a43fi.go();
	}

	private void go() {
		System.out.println("test1: " +  test1( n -> n*n, 2 ) );	// 4
		System.out.println("test2: " +  test2( (n,m) -> n*m, 2 , 4) ); // 8
		System.out.println("test3: " +  test3( (Integer n) -> (double)(n/2), 4) );	// 2.0 
		// (int n) DNC Lambda expression's parameter n is expected to be of type Integer
		System.out.println("test4: " + test4( (SecretBox<Integer> sb) -> sb.getContent() != null ,new SecretBox<>(42) ) );
		System.out.println("test5: " + test5( () -> new SecretBox<>(new Dog()) ) );
		System.out.println("test6: " + test6( d -> {} , 34.56));
	}
	
	private int test1( UnaryOperator<Integer> unop, int n ) { 
		return  unop.apply(n);
	} 

	private int test2( BinaryOperator<Integer> binop, int n, int m ) { 
		return  binop.apply(n,m);
	}
	
	private double test3( Function<Integer, Double> func, int n ) { 
		return  func.apply(n);
	} 		

	private boolean test4 ( Predicate<All43FuntionalInterfases.SecretBox<Integer>> pred,
			All43FuntionalInterfases.SecretBox<Integer> sb  ) {
		return pred.test( sb );
	}
	
	private SecretBox<Animal> test5( Supplier<SecretBox<Animal>> supp ){	// DNC Dog
		return supp.get();
	}
	
	private boolean test6( Consumer<Double> cons, double d ) {
		cons.accept(d);
		return true;
	}
	
}
