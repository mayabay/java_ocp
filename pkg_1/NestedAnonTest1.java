/**
 * 
 */
package pkg_1;

/**
 *
 *
 */
public class NestedAnonTest1 {

	interface Callable {
		int doit();
	}
	
	Callable calli = new Callable() {
		public int doit() { return 42; }
	};	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		NestedAnonTest1 nat1 = new NestedAnonTest1();
		nat1.fo();

	}

	private void fo() {
		
		Callable calli = new Callable() {
			public int doit() { return 42; }
			public void noReturn() {}
		};
		
		goForIt(calli);
		goForIt( new Callable() { 
			public int doit() { return 0; }
		} );
		
	}
	
	void goForIt( Callable callable ) {
		System.out.println(callable.doit());
	}
}
