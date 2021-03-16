/**
 * review bs ch 7 questions
 */
package pkg_7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.DoubleStream;

/**
 *
 */
public class ConcurrentTest4 {

	private AtomicInteger i1 = new AtomicInteger(0);
	private int i2 = 0;
	
	private class PrintConstants {
		
		double d1 = 3.5731;
		double d2 = 2.1567;
		
		void doo() {
			
			ExecutorService service = Executors.newScheduledThreadPool(10);
			
			DoubleStream.of(d1,d2)
			.forEach(
					c -> service.submit( () -> System.out.println(c*10) )
					);
			
			service.execute(()->System.out.println("printed"));
			
			// service hangs forever, if it is not shutdown
			service.shutdown();
		}
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ConcurrentTest4 cct4 = new ConcurrentTest4();
		//cct4.test1();
		cct4.test2();
	}

	private void test1() {
		this.new PrintConstants().doo();
	}
	
	private void test2() {
		ExecutorService service = Executors.newSingleThreadExecutor();
		
		service.submit( () -> { 
			for( int i = 1; i <=100; i++ ) {
				i1.getAndIncrement(); i2++; 
			}
		} );
		try { Thread.sleep(1000); }
		catch( InterruptedException e) {
			//
		}
		service.shutdown();
		
		System.out.println(i1 + " : " + i2);
	}
	
	
}
