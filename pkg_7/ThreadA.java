/**
 * KB 10.4
 */
package pkg_7;

/**
 * 
 *
 */
public class ThreadA {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		ThreadB b = new ThreadB();
		b.start();
		
		// Exception in thread "main" java.lang.IllegalMonitorStateException
//		try {
//			b.wait();
//		} catch (InterruptedException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
		synchronized (b) {
			try {
				System.out.println("Waiing for b to complete ...");
				b.wait();
			}catch(InterruptedException e) {
				//
			}
			System.out.println("Total is : " + b.total);
		}

	}

}
