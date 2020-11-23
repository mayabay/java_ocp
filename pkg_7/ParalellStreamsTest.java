/**
 * KB 11.6
 */
package pkg_7;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;

/**
 *
 */
public class ParalellStreamsTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ParalellStreamsTest pst = new ParalellStreamsTest();
		//pst.test1();
		pst. associativeOp();
	}

	
	private void test1() {
		List<Integer> nums = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		int sum = nums.stream()
				.parallel()
				.peek( n -> {
					System.out.println("Thread : " + Thread.currentThread().getName());
				} )
				.mapToInt(n -> n)
				.sum();
		System.out.println("sum = " + sum);
	}
	
	
	private void associativeOp() {
		List<Integer> nums = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

		OptionalDouble avg = nums.stream()
				.parallel()
				.peek( n -> {
					System.out.println("Thread : " + Thread.currentThread().getName());
				} )
				.mapToDouble(n -> n)
				.reduce( (d1,d2) -> (d1+d2) / 2 );
				
		avg.ifPresent( a -> {
			System.out.println("Avg of paralell stream with reduce " + a);
		} );
		
		OptionalDouble avg2 = nums.stream()
				.parallel()
				.peek( n -> {
					System.out.println("Thread : " + Thread.currentThread().getName());
				} )
				.mapToDouble(n -> n)
				.average();
				
		avg2.ifPresent( a -> {
			System.out.println("Avg of paralell stream with average " + a);	
		});
	}
}
