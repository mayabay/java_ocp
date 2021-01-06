/**
 * BS 4
 *  Collectors class
 *  terminating operation collect( Collector )
 *  
 *  A
 *  	averaginInt/Long/Double( ToIntFunction )	int applyAsInt( T t )
 *  	summingInt// ( ToIntFunction )
 *  	summarizingInt ( ToIntFunction ) IntSummaryStatistics
 *  
 *  B
 *  	long counting()
 *  	String joining() (CharSequence)
 *  	
 *  C 
 *  	Map<K, List<T>> groupingBy (Function classifier)
 *      Map<K, D>		groupingBy (classifier, dc)
 *      Map<K, D>		groupingBy (classifier,mf,dc)
 *  
 *  	Map<Boolean, List<T>> partitioningBy ( Pred )
 *  	Map<Boolean, D> partitioningBy ( Pred, dc )
 *  	
 *  	R mapping( mapper, dc )
 *  
 *  D
 *  	toCollection( supp )
 *  	toList()
 *  	toSet()
 *  	toMap( keyMapper, valueMapper  )
 */
package pkg_4;

import java.util.stream.LongStream;
import java.util.stream.Stream;

import pkg_4.StreamTest2.Thing;

import static java.util.stream.Collectors.*;

/**
 * 
 *
 */
public class StreamTest3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StreamTest3 st3 = new StreamTest3();
		st3.test_Collectors_A();

	}

	/* 
	 * https://stackoverflow.com/questions/30310749/why-dont-primitive-stream-have-collectcollector
	 * */
	private void test_Collectors_A() {
		// Collectors only work with Stream<T> not with primitive streams!!!
		// OK
		Stream<Thing> things = Stream.generate( StreamTest2::getThing );
		// Does not work!
		LongStream longs = LongStream.iterate(10L, l -> ++l );
		
		long count =
		things
		.limit(21)
		.peek( System.out::println )
		.collect( counting() );	
		// Type mismatch: cannot convert from Collector<Object,capture#1-of ?,Long> to Supplier<R>
		// collect(Supplier<R> supplier, ObjIntConsumer<R> accumulator, BiConsumer<R,R> combiner)
	}
	
}
