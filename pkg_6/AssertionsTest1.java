/**
 * 
 */
package pkg_6;

/**
 *
 */
public class AssertionsTest1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AssertionsTest1 at1 = new AssertionsTest1();
		at1.test2();
	}

	private void test2() {
		int a = -1;
		assert a++ > 0 : "a must be greater than 0!";
	}
	
}
