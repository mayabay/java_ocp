package pkg_4;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Learn Collector methods in Collector class
 * 
 * */
public class CollectorsTest1 {

	public static void main(String[] args) {
		CollectorsTest1 ct1 = new CollectorsTest1();
		ct1.joining();
		ct1.averaging();
	}
	
	
	private void joining() {
		Stream<String> str = Stream.of( "Bert", "Peter", "Lisa" );
		String r = str.collect( Collectors.joining(", ") );
		System.out.println(r);
	}
	
	private void averaging() {
		
		Stream<Integer> ints = Stream.generate(()-> { return (int) (Math.random() *10) ; } );
		
		Double d =	ints	
						.limit(24)
						.peek(System.out::println)
						.collect( Collectors.averagingInt( Integer::intValue ) );
		
		System.out.println("mean = " +  d );
		
	}

}
