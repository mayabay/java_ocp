package pkg_4;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.function.DoublePredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * OCP KB chapt. 9.4
 * 
 * */
public class KB9_4 {

	private class Reading{
		int year;
		int month;
		int day;
		double value;
		
		public Reading(int year, int month, int day, double value) {
			super();
			this.year = year;
			this.month = month;
			this.day = day;
			this.value = value;
		}

		@Override
		public String toString() {
			return "Reading [year=" + year + ", month=" + month + ", day=" + day + ", value=" + value + "]";
		}
		
		
	}
	
	public static void main(String[] args) {
		KB9_4 o = new KB9_4();
		//o.testStream1();
		o.readReadings();
	}

	private void testStream1() {
		//IntStream is = IntStream.of(2,5,7);	// 76
		IntStream is = IntStream.of(1,2,3,4,5,6);	// 58, 26 + 37 = 63 
		int identity = 0;
		OptionalInt opt1 =
				is
				.map( i -> i * i )
				.filter( i -> i > 20 )
				.map( i -> i + 1 )
				.reduce((i1, i2) -> i1 + i2 );
		
		opt1.ifPresent(System.out::println);	// 58
		
	}
	
	private void readReadings() {
		List<Reading> readings = new ArrayList<>();
		
		readings.add( new Reading(2002,6,23,405.65) );
		readings.add( new Reading(2003,3,2,408.32) );
		readings.add( new Reading(2004,7,12,406.21) );
		readings.add( new Reading(2005,1,13,406.28) );
		readings.add( new Reading(2006,11,30,405.64) );
		readings.add( new Reading(2007,4,21,409.11) );
		
		Predicate<Reading> p1 = re -> re.value >= 406.00;
		Predicate<Reading> p2 = re -> re.value <= 407.00;
		
		double d =
				readings.stream()
				.filter( p1.and(p2) )
				//.flatMap( r -> Stream.of(r.value) )
				.collect( Collectors.averagingDouble(r -> r.value) );
	
		System.out.println("avg for v: " + d);
	}
	
}
