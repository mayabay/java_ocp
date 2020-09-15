package pkg_4;
import java.util.Comparator;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;
/**
 * learn stream intermediate operations
 * */
public class StreamOpsTest1 {
	
	// increment to get unique nrs.
	private static int idSrc;
	
	// instance inner class
	private class IdBox {
		private int id;
		private String type;
		private IdBox(){ id = ++idSrc; }
		private int getId() { return id; }
		public String toString() { return "[Box id "+id+" ]"; }
	}
	
	// supplies a IdBox
	Supplier<IdBox> boxSupp = IdBox::new;
	
	// Random instance
	private Random r = new Random();
	
	/**
	 * main()
	 * */
	public static void main( String ... args ) {
		StreamOpsTest1 sot1 = new StreamOpsTest1();
		sot1.testIntermediateOps();
		//sot1.testFlatMap();
		
	}

	// get string Stream
	private Stream<String> getApes() {
		return Stream.of("bonobo", "gorilla", "orang utan", "shimpanzee", "bonobo" );
	}
	
	// get int stream
	private Stream<Integer> getInts(){
		return Stream.generate( ()->{
			return (int)(Math.random() * 10);
		} );
	}
	
	// get ascending int stream
	private Stream<Integer> getIntsAsc(){
		return Stream.iterate(1, i -> i +1 );
	}
	
	// play with intermediate Stream methods
	private void testIntermediateOps() {
		
		getApes()
			.filter( (s) -> s.startsWith("o") )
			.forEach(System.out::println);
		
		System.out.println("----------");
		
		getApes()
			.distinct()
			.forEach(System.out::println);
		
		System.out.println("----------");
		
		getInts()
			.limit(12)
			.skip(6)
			.forEach(System.out::print);
		
		System.out.println();
		System.out.println("----------");
		
		getIntsAsc()
			.skip(5)
			.limit(10)
			.forEach(System.out::println);
		
		System.out.println("----------");
		
		getInts()
			.map( t -> {
				return (char)t.intValue();
			} )
			.limit(12)
			.forEach(System.out::print);
	
		System.out.println();
		System.out.println("----------");
		
		r.ints(16)
				.map( t -> t/2 )
				.map( t -> {
					if (t < 0) { return Math.abs(t); }
					return t;
				} )
				.forEach(System.out::println);
		
		System.out.println("----------");
		
		getApes()
			.map( String::length )
			.forEach(System.out::println);
		
		System.out.println("----------");
		
		getApes()
			.sorted( Comparator.reverseOrder() )
			//.sorted( Comparator::reverseOrder )		// he type Comparator does not define reverseOrder(String, String) that is applicable here
			.distinct()
			.forEach(System.out::println);
		
	}
	
	// test flat map
	private void testFlatMap() {
		Stream<IdBox> iboxes = Stream.generate(boxSupp);
		
		iboxes
			.limit(12)
			.flatMap( t -> Stream.of(t.getId()) )
			.forEach( System.out::println );
		
	}	
	
	
}
