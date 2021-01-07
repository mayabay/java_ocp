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
 *      Map<K, D>		groupingBy (classifier,mapfactory,dc)
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

import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import pkg_4.StreamTest2.Thing;

import static java.util.stream.Collectors.*;

import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.function.Function;

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
		//st3.test_Collectors_A();
		//st3.test_Collectors_B();
		st3.test_Collectors_C();

	}

	private void test_Collectors_A() {
		
		Consumer<Thing> tcon = System.out::println;
		
		Stream<Thing> things = Stream.generate( StreamTest2::getThing );
		System.out.println("---- summingDouble()");
		double areaSum =
		things
		.limit(4)
		.peek(tcon)
		.collect( summingDouble( Thing::getArea ) );
		
		System.out.println("areaSum = " + areaSum);
		
		System.out.println("---- summingDouble()");
		Stream<Thing> things2 = Stream.generate( StreamTest2::getThing );
		// things Exception in thread "main" java.lang.IllegalStateException: stream has already been operated upon or closed
		double avgTemp =
		things2
		.limit(4)
		.peek(tcon)
		.collect( averagingDouble( Thing::getTemperature ) );		
		
		System.out.printf("avgTemp = %1$5.2f", avgTemp);
		
		System.out.println("---- summarizingDouble() for temperature");
		Stream<Thing> things3 = Stream.generate( StreamTest2::getThing );
		DoubleSummaryStatistics dstats  =
		things3
		.limit(4)
		.peek(tcon)
		.collect( summarizingDouble( Thing::getTemperature ) );		
		
		System.out.println( dstats );
	}
	
	/* 
	 * https://stackoverflow.com/questions/30310749/why-dont-primitive-stream-have-collectcollector
	 * */
	private void test_Collectors_B() {
		// Collectors only work with Stream<T> not with primitive streams!!!
		// OK
		Stream<Thing> things = Stream.generate( StreamTest2::getThing );
		// Does not work!
		LongStream longs = LongStream.iterate(10L, l -> ++l );
		
		System.out.println("---- couting()");
		long count =
		things
		.limit(3)
		.peek( System.out::println )
		.collect( counting() );	
		// Type mismatch: cannot convert from Collector<Object,capture#1-of ?,Long> to Supplier<R>
		// collect(Supplier<R> supplier, ObjIntConsumer<R> accumulator, BiConsumer<R,R> combiner)
		
		System.out.println("---- joining()");
		Stream<Thing> things2 = Stream.generate( StreamTest2::getThing );
		String s =
		things2
		.limit(3)
		.map(Thing::getName)
		.collect( joining(",") );
		System.out.println("names joined with , = " + s);
		
		System.out.println("---- minBy()");
		Comparator<Thing> byNameThingComp = (t1,t2) -> t1.getName().compareTo(t2.getName());
		Comparator<Thing> byTempThingComp = (t1,t2) ->  (int)(t1.getTemperature() - t2.getTemperature());
		
		Stream<Thing> things3 = Stream.generate( StreamTest2::getThing );
		Optional<Thing> opt3 =
		things3
		.limit(4)
		.peek(System.out::println)
		//.collect( minBy( (Thing t1, Thing t2) -> t1.getName().compareTo(t2.getName()) ) )
		//.collect( minBy( byNameThingComp ) )
		//.collect( minBy( byTempThingComp ) )
		.collect( minBy( (Thing t1, Thing t2) -> (int)(t1.getArea()-t2.getArea())  ) );
		;
		//System.out.println("min (by name) = " + opt3.orElseThrow( RuntimeException::new ) );
		System.out.println("min (by area) = " + opt3.orElseThrow( RuntimeException::new ) );
		
	}
	
	private void test_Collectors_C() {
		
		Consumer<Thing> tcon = System.out::println;
		
		System.out.println("---- grouping(class)");	// classifier provides map key
		Stream<StreamTest2.Thing> things = Stream.generate( StreamTest2::getThing );
		
		Map<String, List<Thing>> map =
		things
			.limit(4)
			.peek(tcon)
			.collect( groupingBy( Thing::getColor ) );
			
		System.out.println(map);
		
		System.out.println("---- grouping(class, dc)");	// classifier, downstream collector
		Stream<StreamTest2.Thing> things2 = Stream.generate( StreamTest2::getThing );
		
		Map<String,List<String>> map2 =
		things2
			.limit(4)
			.peek(tcon)
			.collect( groupingBy( Thing::getColor, mapping(Thing::getName, Collectors.toList()) ) );
		
		System.out.println(map2);
		
		System.out.println("---- grouping(class,mf,dc)");	// classifier, map factory , downstream collector, 
		Stream<StreamTest2.Thing> things3 = Stream.generate( StreamTest2::getThing );
		
		Map<String, Set<Thing>> map3 =
		things3
			.limit(4)
			.peek(tcon)
			.collect( groupingBy(Thing::getColor, TreeMap::new, Collectors.toSet()) );
		
		System.out.println(map3);
		
		System.out.println("---- partitioningBy(pred)");	// predicate puts T into two groups (true, false)
		Stream<StreamTest2.Thing> things4 = Stream.generate( StreamTest2::getThing );
		
		Map<Boolean, List<Thing>> map4 =
		things4
			.limit(4)
			.peek(tcon)
			.collect( partitioningBy( (StreamTest2.Thing t) -> t.getTemperature() >= 50 ) );
		
		System.out.println(map4);
			
		System.out.println("---- partitioningBy(pred, dc)");	// predicate puts T into two groups (true, false)
																// value of Boolean is further specified by dc
		
		Stream<StreamTest2.Thing> things5 = Stream.generate( StreamTest2::getThing );
		
		Map<Boolean, List<String>> map5 =
		
		things5
			.limit(4)
			.peek(tcon)
			.collect( partitioningBy( t -> t.getTemperature() >= 50, mapping( Thing::getName, toList() ) ) );
		
		System.out.println(map5);
		
		
		System.out.println("---- mapping(mapper, dc)");			// mapper changes T into U then U is used
																// as input for dc
		
		Stream<StreamTest2.Thing> things6 = Stream.generate( StreamTest2::getThing );
		
		//String s =
		Map<Boolean, List<Double>> map6 =
		things6
			.limit(4)
			.peek(tcon)
			//.collect( mapping(Thing::getColor, joining("-") ) );
			.collect( mapping( Thing::getTemperature, partitioningBy( cel -> cel >= 70 ) ) );
		
		//System.out.println(s);
		System.out.println( map6 );
	}
	
}
