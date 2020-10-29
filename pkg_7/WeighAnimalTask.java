/**
 * 
 */
package pkg_7;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author Andreas Mann (lokal)
 *
 */
public class WeighAnimalTask extends RecursiveTask<Double> {

	private int start;
	private int end;
	private Double[] weights;
	
	/**
	 * @param start
	 * @param end
	 * @param weights
	 */
	private WeighAnimalTask(int start, int end, Double[] weights) {
		super();
		this.start = start;
		this.end = end;
		this.weights = weights;
	}

	@Override
	protected Double compute() {
		if ( end - start <= 3 ) {
			// do the job
			double sum = 0;
			for ( int i = start; i < end; i++ ) {
				weights[i] = (double)new Random().nextInt(100);
				System.out.println("Animal weighed = " + i);
				sum += weights[i];
			}
			// return result
			return sum;
		}else {
			// fork new threads
			int middle = start + ((end - start) / 2);
			System.out.println("middle = " + middle + ", start = " + start + ", end = " + end);
			RecursiveTask<Double> otherTask = new WeighAnimalTask(start, middle, weights);
			otherTask.fork();
			// return result
			return  new WeighAnimalTask(middle, end, weights).compute() + otherTask.join();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// (1) data set
		Double[] weights = new Double[10];
		// (2) the task
		ForkJoinTask<Double> task = new WeighAnimalTask(0, weights.length, weights);
		// (3) worker pool
		ForkJoinPool pool = new ForkJoinPool();
		Double sum = pool.invoke(task);
		
		// print results
		System.out.println("sum = " + sum);
	}

}
