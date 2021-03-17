/**
 * review bs ch 7
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
public class ConcurrentTest5 {

	public static Integer performCount(int exhibitNumber) {
		return exhibitNumber;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService service = Executors.newSingleThreadExecutor();
		final List<Future<?>> results = new ArrayList<>();
		IntStream.range( 0,10 )
		.forEach( i -> results.add( service.submit( () -> performCount(i) ) ) );
		results.stream().forEach(f->printResults(f));
		service.shutdown();
	}

	private static void printResults(Future<?> f) {
		try {
			System.out.println(f.get());
		} catch (InterruptedException | ExecutionException e) {
			//
			e.printStackTrace();
		}
	}
	
}
