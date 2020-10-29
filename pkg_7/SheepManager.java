/**
 * BS 7.3
 */
package pkg_7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Andreas Mann (lokal)
 *
 */
public class SheepManager {

	//private int sheepCount = 0;
	private AtomicInteger sheepCount = new AtomicInteger(0);
	
	private void incrementAndReport() {
		//System.out.print((++sheepCount) + " ");
		synchronized (this){
			System.out.print( sheepCount.incrementAndGet()  + " ");
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		ExecutorService es = null;
		try {
			es = Executors.newFixedThreadPool(20);
			
			SheepManager manager = new SheepManager();
			for( int i = 0; i < 10; i++ ) {
				synchronized (manager) {
					es.submit(manager::incrementAndReport);	
				}
				
			}
			
		}finally {
			if (es != null)
				es.shutdown();
		}

	}

}
