/**
 * 
 */
package pkg_1;

/**
 * 
 */
public class NestedInnerLocalTest1 {

	interface Insider {
		void canDo();
	}
	
	float temperature;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		NestedInnerLocalTest1 nilt1 = new NestedInnerLocalTest1();
		nilt1.go();
	}

	void go() {
		int aVal = 3;
		abstract class DaddyInnerLocal {}	// abstract or final are ok
		final class InnerLocal extends DaddyInnerLocal implements Insider{	// no sub classing allowed now
			int n = 42;
			InnerLocal(){ n = aVal;  }	// DNC aVal++; local var must be final or effectively final
			int getN() {
				temperature = 37.5f;	// member of enclosing instance lives on heap
				return n + aVal + (int)temperature;
			}
			public void canDo() { System.out.println(getN()); }
		}
		final Insider insider = new InnerLocal();	// final ok
		further(insider);
		
	}
	
	void further( Insider insider ) {		// DNC InnerLocal inner 
		insider.canDo();
	}
	
}
