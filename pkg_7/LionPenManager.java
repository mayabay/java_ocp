/**
 * BS 7.6.1
 * 
 * https://docs.oracle.com/javase/tutorial/essential/concurrency/forkjoin.html
 */
package pkg_7;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 *
 */
public class LionPenManager {

	private void removeAnimals() { System.out.println("Removing animals"); }
	private void cleanPen() { System.out.println("Cleaning the pen"); }
	private void addAnimals() { System.out.println("Adding animals"); }
	
	// private void performTask() {
	private void performTask( CyclicBarrier c1, CyclicBarrier c2 ) {
		try {
			removeAnimals();
			c1.await();
			cleanPen();
			c2.await();
			addAnimals();
		}catch( BrokenBarrierException | InterruptedException e ) {
			// handle e
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService service = null;
		try {
			service = Executors.newFixedThreadPool(4);
			LionPenManager manager = new LionPenManager();
			CyclicBarrier c1 = new CyclicBarrier(4);
			CyclicBarrier c2 = new CyclicBarrier(4, () -> System.out.println("*** Pen cleaned!") );
			for(int i = 0; i < 4; i++) {
				service.submit( () ->  manager.performTask(c1,c2) );
			}
			
		}finally {
			if (service != null)
				service.shutdown();
		}

	}
	
	/*
	 * any thread executes unsynchronized
			Removing animals
			Removing animals
			Cleaning the pen
			Adding animals
			Removing animals
			Cleaning the pen
			Adding animals
			Removing animals
			Cleaning the pen
			Adding animals
			Cleaning the pen
			Adding animals
			
		with Cyclic barrier
	
			Removing animals
			Removing animals
			Removing animals
			Removing animals
			Cleaning the pen
			Cleaning the pen
			Cleaning the pen
			Cleaning the pen
			*** Pen cleaned!
			Adding animals
			Adding animals
			Adding animals
			Adding animals			
	 
	 
	 * */

}
