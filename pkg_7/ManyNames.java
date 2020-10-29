/**
 * KB 10
 */
package pkg_7;

/**
 * 
 *
 */
public class ManyNames {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Make one Runnable
		Runnable nr = new NameRunnable();
		
		Thread one = new Thread(nr);
		one.setName("Fred");
		Thread two = new Thread(nr);
		two.setName("Lucy");
		Thread three = new Thread(nr);
		three.setName("Ricky");

		one.start();
		two.start();
		three.start();
		
		System.out.println("main finished");
	}

}
