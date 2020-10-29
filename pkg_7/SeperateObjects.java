/**
 * https://www.youtube.com/watch?v=LCSqZyjBwWA&ab_channel=JakobJenkov
 */
package pkg_7;

/**
 * @author Andreas Mann (lokal)
 *
 */
public class SeperateObjects {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Runnable runnable1 = new MyRunnable();
		//Runnable runnable2 = new MyRunnable();
		
		Thread thread1 = new Thread( runnable1, "Thread 1" );
		Thread thread2 = new Thread( runnable1, "Thread 2" );

		thread1.start();
		thread2.start();
	}

}
