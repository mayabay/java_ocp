/**
 * 
 */
package review_bs;

import java.util.function.Predicate;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

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
		//o.q3();
		//o.q6();
		//o.q11();
		o.q14();


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

	private void q6(){
		//Stream<String> s = Stream.generate( ()-> "meow" );
		Stream<String> s = Stream.of( "", "" );
		boolean match = s
		.peek((sstr)-> System.out.print("element down .. |") )
		.peek(System.out::print)
		.peek((str)-> System.out.print("| .. ") )
		.allMatch( String::isEmpty );
		System.out.println(match);
	}

	private void q11(){
		String s =
		Stream.iterate( 1, x -> x++ )
		.limit(5)
		.map(x-> "" + x )
		.collect(Collectors.joining() );

		System.out.println( s );
	}

	private void q14(){
		DoubleStream ds = DoubleStream.of( 1.1,1.2,1.3 );
		IntStream is = ds.mapToInt( x -> (int)x );
		Stream<Integer> is2 = ds.mapToInt( x -> (int)x ).mapToObj( x->x ) ;
	}

}
