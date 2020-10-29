/**
 * KB 10 
 */
package pkg_7;

/**
 * 
 *
 */
public class NameRunnable implements Runnable{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	

	}

	@Override
	public void run() {
		for( int i = 0; i < 4; i++ ){
			System.out.println("Run by " + Thread.currentThread().getName());
			
			try {
				Thread.sleep(1000);
			}catch( InterruptedException e ) {
				e.printStackTrace();
				return;
			}
			
		}
	}
	
}
