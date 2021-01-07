/**
 *  BS 4
 */
package pkg_4;

import java.util.List;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Stream, primitive streams
 *
 */
public class StreamTest2 {

	private static int thingIdCounter;

	private static Random random = new Random(); 
	
	private Supplier<Thing> thingSupp = StreamTest2::randomTing;
	
	public static class Thing implements Comparable<Thing> {
		private static final String[] colors = {"red","green","blue","yellow","black","white","orange"};
		private int id;
		private String name;
		private double width;
		private double depth;
		private double area;
		private LocalDateTime created;
		private double temperature;
		private String color;
		private Thing() {}
		Thing(int id, String name, double width, double depth) {
			super();
			this.id = id;
			this.name = name;
			this.width = width;
			this.depth = depth;
			this.area = this.calculateArea();
			this.created = LocalDateTime.now();
			this.temperature = this.calculateTemperature();
			this.color = this.calcucateColor();
		}
		
		private double calculateArea() { return this.width * this.depth; }
		private double calculateTemperature() { return (30 + StreamTest2.random.nextInt(49)) + (StreamTest2.random.nextDouble() * 10); }
		private String calcucateColor() { return colors[ StreamTest2.random.nextInt(colors.length) ]; } 
		
		/**
		 * @return the id
		 */
		public int getId() {
			return id;
		}
		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}
		/**
		 * @return the width
		 */
		public double getWidth() {
			return width;
		}
		/**
		 * @return the depth
		 */
		public double getDepth() {
			return depth;
		}
		/**
		 * @return the area
		 */
		public double getArea() {
			return area;
		}
		
		/**
		 * @return the temperature
		 */
		public double getTemperature() {
			return temperature;
		}
		
		/**
		 * @return the color
		 */
		public String getColor() {
			return color;
		}		
		
		@Override
		public int compareTo( Thing other ) { return this.id - other.getId(); }
		
		@Override
		public int hashCode() {
			return 31 * id;
		}
		
		@Override
		public boolean equals( Object o ) {
			if ( o == null ) { return false; }
			if ( ! (o instanceof Thing) ) return false;
			Thing thing = (Thing)o;
			if (this.id != thing.getId()) return false;
			return true;
		}
		@Override
		public String toString() {
			String area = String.format("%1$5.2f", this.getArea());
			String temp = String.format("%1$5.2f", this.getTemperature());
			return "Thing [id=" + id + ", name=" + name +
					", area=" + area + ", temp. = "+temp+", color = "+color+"]";
		}
		
		
	}
	
	public static Thing randomTing(){
		try {
			Thread.sleep(256);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		String[] notANameArr = { "He", "Li", "Bu", "Ma", "Ko", "Xu", "Fe", "We", "Ri", "Zu" };
		String part1 = notANameArr[ random.nextInt( notANameArr.length ) ];
		String part2 = notANameArr[ random.nextInt( notANameArr.length ) ];
		int id =  ++thingIdCounter;
		return new Thing( id, (part1+part2), random.nextDouble(), random.nextDouble()  );
	}
	
	// thingSupp is instance so i need a surrounding  instance
	private static final StreamTest2 INSTANCE;
	
	static {
		INSTANCE = new StreamTest2();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StreamTest2 st2 = new StreamTest2();
		//st2.test1();
		//st2.test2();
		//st2.test3();
		//st2.test4();
		//st2.test5();
		st2.test6();
	}

	/**
	 * Delivers a random Thing object
	 * 
	 * @return Thing
	 * */
	public static Thing getThing() {
		return INSTANCE.thingSupp.get();
	}
	
	private void test1() {
		Stream<Thing> s1 = Stream.generate( this.thingSupp );
		Stream<Thing> s2 = Stream.generate( this.thingSupp );
		Stream<Thing> s3 = Stream.generate( this.thingSupp );
		
		// (1) collect into Collection
		Set<Thing> set =
				s1
				.peek(System.out::println)
				.limit(4)
				.collect(HashSet::new, Set::add, Set::addAll);
		
		// (2) count() as terminal op.
		long count =
		s2
		.limit(4)
		.count();
		
		// s1: ln 124 java.lang.IllegalStateException: stream has already been operated upon or closed
		
		System.out.printf( "%1$d %n", count );
		
		// (3) reduction to single Thing
		Thing thing = this.thingSupp.get();
		
		Thing t =
		s3
		.limit(5)
		.distinct()
		.reduce( thing, (t1,t2) -> {
			return new Thing( ++thingIdCounter,
					(t1.getName()+t2.getName()),
					(t1.getWidth()+t2.getWidth()),
					(t1.getDepth()+t2.getDepth())
					);
		} );
		
		System.out.println("t reduced = " +  t );
	}
	
	/* Thing to double
	 * mapToDouble
	 * */
	private void test2(){
		Stream<Thing> s1 = Stream.generate( this.thingSupp );
		
		double sum =
		s1
			.limit(4)
			.peek(System.out::println)
			.mapToDouble( Thing::getWidth )
			.reduce( 0.0, (d1,d2) -> d1+d2 );
	
		System.out.printf("sum of width = %1$3.2f %n ", sum);
	}
	
	/* IntStream to Stream<Thing> to Map<K, List<T>
	 * */
	private void test3() {
		IntStream i1 = IntStream.iterate( 10, i -> ++i );
		
		Map<Integer, List<Thing>> map =
		i1
			.limit(4)
			.peek(System.out::println)
			.mapToObj( i -> thingSupp.get() )
			//.collect( Collectors.groupingBy( Thing::getId ) );
			.collect( Collectors.groupingBy( (Thing t) -> {
				double temperature = t.getTemperature();
				return (int)(temperature/10);
			} ) );
			
		System.out.println( map );
	}

	/* partitioning only works for stream of T not for a DoubleStream
	 * */
	private void test4() {
		Stream<Thing> things = Stream.generate(this.thingSupp);
		Map<Boolean, List<Thing>> map =
		things
		.limit(4)
		//.mapToDouble( Thing::getTemperature )
		//.collect( Collectors.partitioningBy( d -> d > 50 ) );
		.collect( Collectors.partitioningBy(t -> t.getTemperature() >= 50) );
		System.out.println(map);
	}
	
	
	private void test5() {
		Stream<Thing> things = Stream.generate(this.thingSupp);
		String s =
		things
		.limit(4)
		.sorted()
		.map( Thing::getName )
		.collect( Collectors.joining("-") );
		System.out.println(s);
	}
	
	private void test6() {
		Stream<Thing> things = Stream.generate(this.thingSupp);
		Double avg =
		things
		.peek(System.out::println)
		.limit(12)
		//.mapToDouble(Thing::getTemperature)
		.collect( Collectors.averagingDouble( Thing::getTemperature ) );
		System.out.printf("avg = %1$3.2f C", avg);
	}
}
