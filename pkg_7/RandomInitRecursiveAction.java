/**
 * KB 11.5.3
 */
package pkg_7;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A ForkkJoinTask that is initializing a data set 
 * A recursive resultless ForkJoinTask
 */
public class RandomInitRecursiveAction extends RecursiveAction {

	private static final int THRESHOLD = 10_000;
	private int[] data ;
	private int start;
	private int end;
	
	public RandomInitRecursiveAction( int[] data, int start, int end ) {
		this.data = data;
		this.start = start;
		this.end = end;
	}

	@Override
	protected void compute() {
		if ( end - start <= THRESHOLD) {
			// do the task
			for ( int i = start; i < end; i++ )   {
				data[i] = ThreadLocalRandom.current().nextInt();
			}
		}else {
			int halfWay = ( (end - start) >>> 1 ) + start;
			RandomInitRecursiveAction a1 = new RandomInitRecursiveAction(data, start, halfWay);
			a1.fork();	// left half
			RandomInitRecursiveAction a2 = new RandomInitRecursiveAction(data, halfWay, end);
			a2.compute();
			a1.join();
		}
		
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		int a = 0;
		int b = 11;
		int half = ( a + b ) >>> 1;
		System.out.println("half = " + half);
		
		// plumbing
		// prepare data set
		int[] data = new int[ 10_000_000 ];
		// build the pool
		ForkJoinPool fjp = new ForkJoinPool();
		// build the action
		RandomInitRecursiveAction action = new RandomInitRecursiveAction(data, 0, data.length);
		// invoke action on pool
		fjp.invoke(action);
		
		System.out.println("value at 34789 " + data[34789]);
	}
}
