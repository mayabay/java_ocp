/**
 * 
 */
package pkg_3;

/**
 * 
 *
 */
public class AssertionTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AssertionTest at = new AssertionTest();
		at.test1();
	}
	
	private void test1() {
		int i = 7;
		assert ( i > 10 ) : i = 8;
		
		
	}

}
