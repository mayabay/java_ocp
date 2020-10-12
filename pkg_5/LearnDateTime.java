package pkg_5;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.zone.ZoneRules;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;



/**
 * Learn date an time pieces in ISO calendar system
 * 
 * */
public class LearnDateTime {

	public static void main(String[] args) {
		LearnDateTime learnDateTime = new LearnDateTime();
		
		//learnDateTime.printProperties();
		//learnDateTime.test1();
		
		learnDateTime.printTimeZones();
		learnDateTime.test2();
		
		
	}

	private void printProperties() {
		
		System.out.println("System properties:");
		System.out.println("---");
		
		Properties props = System.getProperties();
		Enumeration<Object> keys = props.keys();
		while( keys.hasMoreElements() ) {
			Object key = keys.nextElement();
			System.out.println( key  + " : " + props.getProperty(key.toString())  );
			
			
		}
	}
	
	private void test1() {
		
		LocalDate ldNow = LocalDate.now();
		System.out.println("date today: " + ldNow);
		
		LocalDate birthDay = LocalDate.of(1973, 8, 5);
		System.out.println("my birthday: " + birthDay);
		
		Period period1Week = Period.ofWeeks(1);
		System.out.println("period of 1 week: " + period1Week);
		
		Duration duration2Hours = Duration.ofHours( 2 );
		System.out.println("duration: " + duration2Hours);
		
		
	}
	
	private void test2() {
		// server in Melbourne receives information
		// user in Berlin is informed, when this happened in his local time
		
		LocalDateTime happened = LocalDateTime.of (2020,9,28,6,32,24,0);
		
		ZonedDateTime serverTimeMelbourne1 = ZonedDateTime.of( happened,ZoneId.of("UTC+10:00") );
		ZonedDateTime serverTimeMelbourne2 = ZonedDateTime.of( happened,ZoneId.of("GMT+10") );
		ZonedDateTime serverTimeMelbourne3 = ZonedDateTime.of( happened,ZoneId.of("UTC+10") );
		
		System.out.println("event happened (server time Melbourne, Victoria) :" + serverTimeMelbourne2);
		
		ZonedDateTime clientTimeBerlin = ZonedDateTime.of(happened, ZoneId.of("Europe/Berlin"));
		
		System.out.println("this was at local time Berlin: " + clientTimeBerlin.minusHours(10)  ) ;
		
		
		
	}
	
	private void printTimeZones() {
		
		System.out.println("Time zones:");
		
		ZonedDateTime eclipseDateTime = ZonedDateTime.of(2017, 8, 21, 10, 19, 0, 0, ZoneId.of("US/Pacific"));
		
		Set<String> zoneIds = ZoneId.getAvailableZoneIds();
		TreeSet<String> zoneIdsSorted = new TreeSet<>( zoneIds );
		
//		for( String str : zoneIdsSorted ) {
//			System.out.println(str);
//		}
		
		System.out.println( "zones: " + zoneIdsSorted.size() );
		
		zoneIdsSorted.stream()
			.filter( s -> s.startsWith("US") )
			.peek(System.out::print)
			.map( ZoneId::of )			// String to ZoneId
			.map(ZoneId::getRules)		// ZoneId to ZoneRules
			.map(ZoneRules::toString)
			.forEach(System.out::println);
	}
	
	
}
