/**
 * KB 10.4
 */
package pkg_7;

/**
 * 
 *
 */
public class Reader  extends Thread{

	Calculator c;
	
	Reader( Calculator c ){
		this.c = c;
	}
	
	@Override
	public void run() {
		synchronized(c) {
			try {
				System.out.println("Waiting for calcultion ...");
				c.wait();
			}catch(InterruptedException e) {
				//
			}
			System.out.println("Total is :" + c.total);
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Calculator calculator = new Calculator();
		new Reader(calculator).start();
		new Reader(calculator).start();
		new Reader(calculator).start();
		new Thread(calculator).start();

	}

}
