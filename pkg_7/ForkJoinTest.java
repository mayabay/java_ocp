/**
 * KB 11.5
 */
package pkg_7;

/**
 * Learn how to solve problems with the fork join pool (a special executor
 * service)
 *
 */
public class ForkJoinTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ForkJoinTest fjt = new ForkJoinTest();
		fjt.test1();

	}

	private void test1() {
		System.out.println("processors : " + Runtime.getRuntime().availableProcessors());
		
	}
	
}
