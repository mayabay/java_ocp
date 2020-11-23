/**
 * KB 11.5
 */
package pkg_7;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Peak finder as example for a RecursiveTask
 * ForkJoinTask with result
 */
public class FindMaxPositionRecursiveTask extends RecursiveTask<Integer> {

	private static final int THRESHOLD = 10_000;
	private int[] data;
	private int start;
	private int end;
	
	public FindMaxPositionRecursiveTask( int[] data, int start, int end ) {
		this.data = data;
		this.start = start;
		this.end = end;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// #1 data set
		int[] data = new int[ 10_000_000 ];

		// #2 create fj pool
		ForkJoinPool fjPool = new ForkJoinPool();
		
		// #3 fill array using existing ForkJoinTaks
		RandomInitRecursiveAction action = new RandomInitRecursiveAction(data, 0, data.length);
		fjPool.invoke(action);
		
		// #4 create ForkJoinTask "peak finder"
		FindMaxPositionRecursiveTask task = new FindMaxPositionRecursiveTask(data, 0, data.length);
		Integer position = fjPool.invoke(task);
		
		System.out.println("position = " + position + ", with value = " + data[position]);
		
	}

	@Override
	protected Integer compute() {
		if( end - start <= THRESHOLD ) {
			// do computation
			int position = 0;
			for ( int i = 0; i < data.length; i++ ) {
				if ( data[i] > data[position] ) { position = i; }
			}
			return position;
		}else {
			// fork, compute, join
			// #1 find half of work
			int halfWay = ((end - start) >>> 1) + start;
			
			// #2 create left half task and queue
			FindMaxPositionRecursiveTask t1 = new FindMaxPositionRecursiveTask(data, start, halfWay);
			t1.fork();
			
			// #3 create right half task and compute
			FindMaxPositionRecursiveTask t2 = new FindMaxPositionRecursiveTask(data, halfWay, end);
			int position2 = t2.compute();
			
			// #4 join left half
			int position1 = t1.join();
			
			// #5 determine greater value position
			if ( data[position1] > data[position2] ) { return position1; }
			else if ( data[position2] > data[position1] ) { return position2; }
			else { return position1 < position2 ? position1 : position2; }
		}
	}

}
