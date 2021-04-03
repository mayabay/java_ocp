/**
 * 
 */
package pkg_1.mouse;
import pkg_1.cat.Tom;

/**
 * learn access control
 *
 */
class Jerry extends Tom {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Jerry j1 = new Jerry();
		j1.test1();

	}

	private void test1() {
		System.out.println( "protected field : " + this.weightInGramm );
	}
	
	private void test2() {
		Tom tom = (Tom)this;
		//System.out.println( "protected field : " + tom.weightInGramm );
			// DNC not visible thru Tom type reference
		
		// default field from Tom not visible thru Tom or Jerry ref. type
		// System.out.println(this.ageInYears);	// DNC field not visible
		
	}
	
}
