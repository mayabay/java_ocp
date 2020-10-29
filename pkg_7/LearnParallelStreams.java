/**
 * 
 */
package pkg_7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Andreas Mann (lokal)
 *
 */
public class LearnParallelStreams {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LearnParallelStreams lps = new LearnParallelStreams();
		//lps.test1();
		//lps.test2();
		//lps.test3();
		lps.test4();
	}

	private void test4() {
		System.out.println( 
				Arrays.asList(1,2,3,4,5,6)
				.parallelStream()
				.reduce(0, (a,b)-> (a-b))
		);
		// -8-5 = -13 -6 = -19
	}	
	
	private void test3() {
		//System.out.println( Arrays.asList(1,2,3,4,5,6).stream().findAny() ); // 1
		System.out.println( Arrays.asList(1,2,3,4,5,6).stream().parallel().findAny() ); // 4 or any other value
	}
	
	private void test2() {
		List<Integer> data = Collections.synchronizedList(new ArrayList<>());
		Arrays.asList(1,2,3,4,5,6).parallelStream()
		.map( i -> {
			data.add(i);
			return i;
		} )
		.forEachOrdered(i -> System.out.print(i + " "));
		
		System.out.println();
		
		for( Integer i : data ) {
			System.out.print( i + " ");
		}
	}
	
	private void test1() {
		Stream<Integer> ints = Stream.of( 1,2,3,4,5,6 );
		ints.parallel();
	}
	
}
