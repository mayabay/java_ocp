/**
 * BS review chap. 7
 */
package pkg_7;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

/**
 *
 */
public class ConcurrentTest6 {

	private static int counter;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Future<?>> results = new ArrayList<>();
		
		ExecutorService service = Executors.newSingleThreadExecutor();
		
		IntStream.iterate( 0, i -> i+1 )
		.limit(5)
		.forEach(i -> results.add( service.submit(()-> counter++ )));
			// execute has void as result not a Future
		
		results.stream().forEach(f->{
			try {
				System.out.println(f.get());
			} catch (InterruptedException | ExecutionException e) {
				// 
				e.printStackTrace();
			}
		});
		
		service.shutdown();
	}

}
