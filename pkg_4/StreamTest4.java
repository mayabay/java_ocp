/**
 * BS 4
 */
package pkg_4;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.DoubleSupplier;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Collectors
 * You can only use Collectors with Stream'<T'>
 */
public class StreamTest4 {

	private static double counter;
	
	private static long counterForLong;
	
	private static DoubleSupplier dsupp = () -> counter + 0.2;
	
	private static StreamTest4 INSTANCE = new StreamTest4();
	
	private static Supplier<SimpleLongContainer> SimpleLongContainerSupp = () -> INSTANCE.new SimpleLongContainer();
	
	class SimpleLongContainer {
		long lo;
		SimpleLongContainer(){
			this.lo = ++StreamTest4.counterForLong;
		}
		SimpleLongContainer( long lo ){
			this.lo = lo;
		}
		long getLo() { return lo; }
		public String  toString() {
			return "["+lo+"]";
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StreamTest4 st4 = INSTANCE; //new StreamTest4();
		
		//st4.test1();
		//st4.test2();
		//st4.test3();
		//st4.test4();
		st4.test5();
	}

	private void test1() {
		Stream<String> strings = Stream.of( "John", "Fred", "Claudia", "Carl", "Rachel", "Elizabeth","Rupert" );
		double avgStringLength = 
//		strings
//			.mapToInt( String::length )
//			.collect( Collectors.averagingInt( l -> l ) );
		
		strings
			.collect( Collectors.averagingInt( String::length ) );
				
		System.out.printf("avgStringLength %1$4.2f ", avgStringLength);
	}
	
	private void test2() {
		//LongStream longs = LongStream.iterate(42L, Long::sum );	// Long sum() takes 2 arg.
																// Unary op. only 1 arg.
		LongStream longs = LongStream.iterate(42L, lo -> ++lo );
		
		Set<SimpleLongContainer> set =
		longs
			.limit(8)
			.peek(System.out::println)
			.filter(lo -> lo % 2 != 0)
			
			.mapToObj( SimpleLongContainer::new )
			.collect(Collectors.toSet());
		System.out.println("----");
		System.out.println( set );
	}
	
	/* partition longs 
	 * */
	private void test3() {
		LongStream longs = LongStream.iterate(1L, lo -> ++lo );
		Map<Boolean,List<Long>> map =
		longs
			.limit(10)
			.mapToObj(Long::valueOf)	// Long::new 
			.collect( Collectors.partitioningBy( lo -> lo % 2 == 0 ) );
		System.out.println(map);
	}
	
	/* map doubles to Strings then map by double value
	 * */
	private void test4() {
		DoubleStream dstream = DoubleStream.generate( dsupp );
		
		TreeMap<Integer,String> map = 
		
		dstream
			.limit(12)
			.peek(System.out::println)
			//.mapToObj( String::valueOf )
			
			.mapToObj( d -> String.format( "%1$4.2f",d ) )	// Exception in thread "main"
														//java.util.IllegalFormatConversionException: f != java.lang.String
			
			.collect( Collectors.toMap( (String s) -> s.length(),
					Function.identity(),
					(s1,s2)-> s1+s2,
					TreeMap::new ) );
		
		System.out.println(map);
			
	}
	
	/* longs to objects
	 * */
	private void test5() {
		LongStream longs = LongStream.of( 1,2,3,4,5,6,7,8,9 );
		Map<Boolean,Long> map = 
		longs
			.mapToObj( l -> { 
				SimpleLongContainer slc = SimpleLongContainerSupp.get();
				slc.lo = l;
				return slc;
			} )
			.collect( Collectors.partitioningBy( slc -> slc.lo % 2 == 0,
				Collectors.mapping(slc->slc.lo, Collectors.counting() ) ) );
		System.out.println(map);
	}
	
}
