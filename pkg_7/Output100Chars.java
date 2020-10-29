/**
 * Exercise 10-2 
 * KBB 10.3
 */
package pkg_7;

/**
 * 
 *
 */
public class Output100Chars implements Runnable {

	private char c = 'A'; 
	
	private StringBuffer sb = new StringBuffer( ("" + c) );
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Output100Chars r = new Output100Chars();
		Thread t1 = new Thread( r );
		Thread t2 = new Thread( r );
		Thread t3 = new Thread( r );
			
		t1.start();
		t2.start();
		t3.start();

	}

	public void run() {
		synchronized (sb) { 
			for (int i = 0; i < 100; i++) {
				System.out.print( sb );
			}
			this.sb.replace(0, sb.length(), ("" + ++c) );
			System.out.println("---");
		}
	}
	
}
