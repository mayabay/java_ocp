/**
 * 
 */
package review_bs;

import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * 
 *
 */
public class Ch4_Questions {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Ch4_Questions o = new Ch4_Questions();
		//o.q2();
		o.q3();

	}

	
	private void q2() {
		Predicate<? super String> pred = (s) -> s.startsWith("g"); 
		Stream<String> stream1 = Stream.generate( () -> "growl!" );
		Stream<String> stream2 = Stream.generate( () -> "growl!" );
		boolean b1 = stream1.anyMatch(pred);
		boolean b2 = stream2.allMatch(pred);
		System.out.println(b1 + " " + b2);
	}
	
	private void q3() {
		Predicate<? super String> pred = (s) -> s.length() > 3;
		Stream<String> stream = Stream.iterate("-", s ->  s + s);
		Stream<String> stream2 = Stream.iterate("-", s ->  s + s);
		boolean b1 = stream.peek(System.out::println).noneMatch(pred);
		/* noneMatch short circuits in case of first match
		 * - -- ---- false
		 */
		boolean b2 = stream2.peek(System.out::println).anyMatch(pred);
		System.out.println(b1 + " " + b2 );
	}
}
