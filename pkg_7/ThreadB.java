/**
 * KB 10.4
 * */
package pkg_7;

public class ThreadB extends Thread {

	int total;
	
	public void run() {
		synchronized (this) {
			for (int i = 0; i < 100; i++) {
				total += 1;
			}
			notify();
		}
	}
}
