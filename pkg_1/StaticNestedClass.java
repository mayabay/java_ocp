/**
 * 
 */
package pkg_1;

/**
 *
 */
public class StaticNestedClass {

	static interface Admirable{
		void doStuff();
		default int compute(double d) {
			return (int)d;
		}
	}
	
	static final int VALUE = 42;
	private static final int VALUE_PRIVATE = 43;
	
	static class Nest {
		int doit() { return VALUE + VALUE_PRIVATE ; }
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StaticNestedClass snc1 = new StaticNestedClass();
		snc1.goForIt( () -> { ; } );
	}

	private void goForIt( Admirable admirable ) {
		System.out.println( admirable.compute(42.0) );
	}
	
	
}
