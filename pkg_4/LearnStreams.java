package pkg_4;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;
/** 
 * Stream interface with static and default methods
 *
 * */
public class LearnStreams {

	private Comparator<String> comp = ( str1, str2 ) -> { return str1.compareTo( str2 ); };

	private Comparator<String> comp2 = String::compareTo;

	private Predicate<String> testStrLength = str -> str.length() < 5;

	public static void main(String[] args){
		LearnStreams ls = new LearnStreams();
		//ls.createStreams();
		//ls.testReduce();
		//ls.testReduceParalell();
		ls.testCollect();
	}

	private Stream<String> getApes(){
		return Stream.of( "Gorilla", "Bonobo", "OrangUtan" );
	}

	private void createStreams(){
		
		Stream<String> strStream = Stream.empty();

		Stream<Integer> singleStream = Stream.of( 1 );

		Stream<Integer> multiValueStream = Stream.of( 1, 2, 3, 4 );
		
		List<String> list = Arrays.asList( "a","b","c","d","e" );
		Stream<String> fromList = list.stream();

		// count()
		System.out.println( "count = " + multiValueStream.count() );

		Stream<String> apes = Stream.of( "Gorilla", "Bonobo", "OrangUtan" );
		
		// min() max()
		System.out.println( apes.min( comp ));	// Optional[Bonobo]

		//System.out.println( apes.max( comp2 ));	// java.lang.IllegalStateException: stream has already been operated upon or closed

		Stream<String> apes2 = Stream.of( "Gorilla", "Bonobo", "OrangUtan" );
		
		System.out.println( apes2.max( comp2 ));	// Optional[OrangUtan]

		// findAny()
		Optional<String> apeOpt = getApes().findAny();
		apeOpt.ifPresent( System.out::println );	// Gorilla

		// anyMatch()
		boolean startsWithCharB = getApes().anyMatch( str1 -> str1.startsWith("B") );
		System.out.println( "ape stream contains 'B' ape? " + startsWithCharB );	// true

		// noneMatch()
	    boolean lengthLess5 = getApes().noneMatch( testStrLength );
		System.out.println( "all apes identifier less than 5 chars = " + lengthLess5 );

		// foreach
		System.out.println( "forEach() :" );
		Stream<String> apes3 = getApes();
		apes3.forEach(System.out::println);

	}

	private void testReduce(){
		
		// T reduce( T, BinaryOperator<T> accumulator )
		Stream<Integer> ints = Stream.of( 1,3, 5 );
		int r = ints.reduce( 0, (i1, i2) -> { i1 += i2 * 2; return i1; }  );
		System.out.println( r );	// 18


		// same with Optional
		Stream<String> strs = Stream.of( "W","o","l","f"  );
		//String r2 = strs.reduce( "", (s1,s2) -> s1.concat(s2) );
		
		Optional<String> opt = strs.reduce( String::concat );
		opt.ifPresent( System.out::println );
	}

	private void testReduceParalell(){
		
		Stream<Integer> ints = Stream.of( 1,3,5,7,11,17 );

		int r = ints.reduce( 0, ( t1,t2 ) -> t1+t2, (u1, u2) -> u1 +=u2 );
		System.out.println( r );

	}

	private void testCollect(){
		Stream<String> strs = Stream.of( "W", "o", "l", "f" );

		StringBuffer buf = strs.collect( StringBuffer::new, (box, Str) -> box.append(Str), ( box1, box2 ) -> box1.append(box2) );

		System.out.println( buf );

		Stream<String> strs2 = Stream.of( "W", "o", "l", "f" );
		TreeSet<String> strTS = strs2.collect( TreeSet::new, TreeSet::add, TreeSet::addAll );
		System.out.println( strTS );

	}

}
