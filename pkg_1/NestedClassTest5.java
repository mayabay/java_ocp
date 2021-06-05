/**
 * 
 */
package pkg_1;

class Crap {
	static class InnerStaticCrap {
		
	}
}

interface I5 {
	class Crap2 {}
	//private class Crap3 {}  // DNC The interface member type Crap3 can only be public
}

class Outer2 {
	
	public String s1 = "bla";
	private String s2 = "blub";
	
	class Inner {
		
		//static int n1 = 1;		// no static declarations allowed
		final static int n2 = 2;	// except as constants
		
		private String s2 = "fla";
		String go() {
			return this.s2 + Outer2.this.s2;
		}
	}
	
	public static interface InnerInterface {}
	private interface I2 {}
	interface I3 {}
	protected interface I4 {}
}

interface Confusing {
	int CONFI = 42;
	class Strange {
		interface Unbelievable {}
		<T,R> R computeT( T t ) { return (R)t; }	// warn unchecked cast
	}
}

class ICA implements Confusing, Confusing.Strange.Unbelievable {
	
	ICA instance1;
	ICA.ICB instance2;
	ICA.ICB.ICC instance3;
	
	private String sA = "sA";
	
	ICA(){
		instance1 = this;
		instance2 = this.new ICB(); // DNC this.new ICA.ICB()	
		instance3 = this.new ICB().new ICC();
	}
	
	void go(){
		instance3.go();
	}
	
	public class ICB {
		
		String sB = "sB";
		
		protected class ICC {
			String sC = "hi";
			void go() {
				System.out.println(  this.getConfusing( ICA.this )  ); // ok ICA.this; ICB.this is not Confusing
			}
			int getConfusing( Confusing confusing ) {
				return confusing.CONFI;
			}
		}
	}
}

/**
 *
 */
public class NestedClassTest5 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Crap.InnerStaticCrap isc = new Crap.InnerStaticCrap();
		//Crap.InnerStaticCrap isc2 = new InnerStaticCrap(); // DNC cannot be 
		// resolved to a type
		
		NestedClassTest5 nct5 = new NestedClassTest5();
		nct5.test1();
	}

	private void test1() {
		
	}
	
}
