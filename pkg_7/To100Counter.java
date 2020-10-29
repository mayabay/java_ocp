/**
 * KB 10 Exercise 10-1
 */
package pkg_7;

/**
 * 
 *
 */
public class To100Counter {

	private static Runnable canRun = () -> {
		for( int i = 1; i <= 100; i++ ) {
			
			if ( i % 10 == 0 ) {
				System.out.println("nr : " + i);
			}
			
			try {
				Thread.sleep(1000);
			}catch( InterruptedException e ) {
				e.printStackTrace();
				return;
			}
		}
	};
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Thread thread = new Thread( canRun );
		System.out.println("is alive? "  + thread.isAlive());
		thread.start();
	}

	
	
}
