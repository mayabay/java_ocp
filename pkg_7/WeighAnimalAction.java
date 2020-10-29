/**
 * BS 7.6.2
 */
package pkg_7;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

/**
 *
 */
public class WeighAnimalAction extends RecursiveAction {

	private int start;
	private int end;
	private Double[] weights;
	
	/**
	 * @param start
	 * @param end
	 * @param weights
	 */
	private WeighAnimalAction(int start, int end, Double[] weights) {
		super();
		this.start = start;
		this.end = end;
		this.weights = weights;
	}

	@Override
	protected void compute() {
		if ( end - start <= 3 ) {
			// do the job
			for ( int i = start; i < end; i++ ) {
				weights[i] = (double)new Random().nextInt(100);
				System.out.println("Animal weighed = " + i);
			}
		}else {
			// fork new threads
			int middle = start + ((end - start) / 2);
			System.out.println("middle = " + middle + ", start = " + start + ", end = " + end);
			ForkJoinTask.invokeAll(new WeighAnimalAction(start, middle, weights),
						new WeighAnimalAction(middle, end, weights)	
					);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// (1) data set
		Double[] weights = new Double[10];
		// (2) the task
		ForkJoinTask<?> task = new WeighAnimalAction(0, weights.length, weights);
		// (3) worker pool
		ForkJoinPool pool = new ForkJoinPool();
		pool.invoke(task);
		
		// print results
		System.out.println();
		System.out.println("weights: ");
		Arrays.asList(weights).stream().forEach(d -> System.out.print(d.intValue() + " "));
	}

	
	
}
