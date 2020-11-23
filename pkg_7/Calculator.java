/**
 * KB 10.4
 */
package pkg_7;

/**
 * 
 *
 */
public class Calculator implements Runnable {
	int total;
	
	@Override
	public void run() {
		synchronized (this) {
			for ( int i = 0; i < 100; i++ ) {
				total += 1;
			}
			//notifyAll();
			notify();
		}
	}
	
	
}
