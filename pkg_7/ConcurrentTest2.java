/**
 * review bs Ch7 questions
 */
package pkg_7;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 *
 */
public class ConcurrentTest2 extends RecursiveTask<Integer> {

	private int[] elements;
	private int a;
	private int b;
	
	public ConcurrentTest2( int[] elements, int a, int b ) {
		this.elements = elements;
		this.a = a;
		this.b = b;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] e = { 8, -3, 2, -54 };
		int a = 0, b = e.length-1;
		
		// create FJT
		ConcurrentTest2 ct2 = new ConcurrentTest2(e, a, b);
		
		// create FJP
		ForkJoinPool fjp = new ForkJoinPool(1);
		
		Future<Integer> fu = fjp.submit(ct2);
		
		try {
			System.out.println("result = " + fu.get());
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		} catch (ExecutionException e1) {
			e1.printStackTrace();
		}		
		
		System.out.println("FIN");
	}

	@Override
	public Integer compute() {
		
		System.out.println("compute()");
		
		if ( (b - a) < 2 )
			return Math.min(elements[a], elements[b]);
		else {
			int m = a + ( (b-a)/2 );
			System.out.println(a + ", " + m + ", "+ b);
			ConcurrentTest2 taskLeft = new ConcurrentTest2(elements, a, m);
			taskLeft.fork();
			ConcurrentTest2 taskRight = new ConcurrentTest2(elements, m, b);
			int r = taskRight.compute();
			return Math.min(r,taskLeft.join() ) ;
		}
	}
	
	
}
