/**
 * KB 10
 */
package pkg_7;

import java.time.Instant;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 
 *
 */
public class WorkerTest1 {

	static int MAX_WORKER_COUNT = 4;

	private void do1(int n) {
			for( int k = 0; k < n; k++ ) {
				this.sleep(1400);
				timeLine.offer(Instant.now());
				System.out.println("\t.");
			}			
	}
	
	Runnable ronny = () -> { do1(2); };
	
	Queue<Instant> timeLine = new ConcurrentLinkedQueue<>();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		WorkerTest1 wk1 = new WorkerTest1();
		wk1.test1();
	}

	private void sleep( long milliSeconds ) {
		try{
			Thread.sleep(milliSeconds);
		}catch( InterruptedException e ) {
			e.printStackTrace();
		}
	}
	
	private void test1() {
		
		ExecutorService es = Executors.newFixedThreadPool(MAX_WORKER_COUNT);
		
		for( int i = 0; i < MAX_WORKER_COUNT; i++ ) {
//			Thread t = new Thread(ronny);
//			t.setName("thread-" + i);
			System.out.println("ronny " + i);
			Future<?> nothing = es.submit( ronny );
		}
		
		es.shutdown();
		
		boolean isFinished = false;
		try {
			isFinished = es.awaitTermination(6, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//es.shutdown();
		
		if (isFinished) {
			System.out.println("finished");
		}
		
		for( int i = 0; i < timeLine.size(); i++ ) {
			System.out.println( timeLine.poll() );
		}
		
		System.out.println("isShutdown() = " + es.isShutdown());
		
		//es.shutdown();
		//System.out.println("isShutdown() = " + es.isShutdown());
	}
	
	
	
}
