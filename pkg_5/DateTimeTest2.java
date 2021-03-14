/**
 * 
 */
package pkg_5;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 *
 */
public class DateTimeTest2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DateTimeTest2 dtt2 = new DateTimeTest2();
		//dtt2.test1();
		//dtt2.test2();
		// dtt2.test3();
		dtt2.test4();
	}

	private void test1() {
		
		LocalDateTime ldt = LocalDateTime.of(LocalDate.now(), LocalTime.now());
		System.out.println( ldt.format(DateTimeFormatter.ISO_TIME) );
		
	} 
	
	private void test2() {
		
		// LocalDateTime ldt = LocalDateTime.of(LocalDate.now(), LocalTime.now());
		LocalTime lt = LocalTime.now();
		System.out.println( lt.format(DateTimeFormatter.ISO_DATE) );
			// Exception in thread "main" java.time.temporal.UnsupportedTemporalTypeException: Unsupported field: Year
		
	} 	
	
	private void test3() {
		ZoneId z = ZoneId.of("US/Eastern");
		LocalDate ld_spring = LocalDate.of(2016, 03, 13);
		LocalDate ld_autumn = LocalDate.of(2016, 11, 6);
		LocalTime lt = LocalTime.of(2, 15);
		ZonedDateTime zdt = ZonedDateTime.of(ld_spring,lt,z);
		ZonedDateTime zdt2 = ZonedDateTime.of(ld_autumn,lt,z);
		
		System.out.println("given time 2:15 am ...");
		System.out.println("..");
		System.out.println( "2016-03-13 : " +  zdt );
		System.out.println("..");
		System.out.println( "2016-11-6 : " +  zdt2 );
		
	}
	
	private void test4() {
		String d1 = Duration.of(1, ChronoUnit.MINUTES).toString();
		String d2 = Duration.of(60, ChronoUnit.SECONDS).toString();
		String d3 = Duration.of(120, ChronoUnit.SECONDS).toString();
		String d4 = Duration.of(60, ChronoUnit.MINUTES).toString();
		
		System.out.println("Duration of 1 minute : " +  d1 );
		System.out.println("Duration of 60 sec : " +  d2 );
		System.out.println("Duration of 120 sec : " +  d3 );
		System.out.println("Duration of 60 min : " +  d4 );
		
		System.out.println("..");
		
		String p1 = Period.ofDays(1).toString();
		System.out.println("Period of 1 day " + p1 );
		
	}
	
}
