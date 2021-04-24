package pkg_5;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;

public class TemporalsTest1 {

	public static void main(String[] args) {
		TemporalsTest1 tt1 = new TemporalsTest1();
		//tt1.test1();
		//tt1.test2();
		//tt1.test3();
		//tt1.springFwd();
		//tt1.fallBack();
		tt1.formatOutput();
	}

	private void test1() {
		LocalTime lt = LocalTime.parse("14:50");
		LocalTime lt2 = lt.with(ChronoField.HOUR_OF_DAY,2)
				.with(ChronoField.MINUTE_OF_HOUR,30);
		System.out.println(lt2);	// 2:30
		
		Locale locDE = new Locale("de", "DE");
		Locale.setDefault(locDE);
		DateTimeFormatter dtf_TimeShort = DateTimeFormatter.ofLocalizedTime( FormatStyle.SHORT );
		System.out.println( lt2.format( dtf_TimeShort ) );	// 2.30
	}
	
	
	private void test2() {
		LocalTime lt = LocalTime.parse("14:50");
		LocalTime lt2 = lt.plus(4, ChronoUnit.HOURS);			// 18:50
		LocalTime lt3 = lt.minus( Duration.ofMinutes(30) );		// 14:20
		LocalTime lt4 = lt.minus( 30, ChronoUnit.SECONDS );		// 14:49:30
		LocalTime lt5 = lt.with( ChronoField.SECOND_OF_MINUTE, 18 );  // 14:50:18
		//LocalTime lt6 = lt.with( TemporalAdjusters.firstDayOfYear() );	 // RTE 
				// java.time.temporal.UnsupportedTemporalTypeException: Unsupported field: DayOfYear
		LocalTime lt6 = lt.with( tp -> tp.minus(Duration.of(4,ChronoUnit.MINUTES)) );	// 14:46
		
		System.out.println( lt6 );
		
	}
	
	private void test3() {
		Instant intsNow = Instant.now();
		System.out.println( intsNow );	// 2021-04-15T03:26:21.668958Z
		
		Duration dur1 = Duration.of( 23, ChronoUnit.MINUTES );
		Duration dur2 = Duration.of( 63, ChronoUnit.MINUTES );
		Duration dur3 = Duration.of( 365, ChronoUnit.DAYS );
		System.out.println(dur1);	//PT23M
		System.out.println(dur2);	//PT1H3M
		System.out.println(dur3);	//PT8760H
		
		
	}
	
	private void springFwd() {
		System.out.println("--- spring fwd ----------------------------------");
		
		ZoneId zone = ZoneId.of("US/Eastern");
		LocalTime time = LocalTime.of(1, 30);
		LocalDate date = LocalDate.of(2016, 03, 13); 
		ZonedDateTime zdt = ZonedDateTime.of(LocalDateTime.of(date, time), zone);
		System.out.println( zdt );
		System.out.println("+1hr : " + zdt.plusHours(1) );
	}
	
	private void fallBack() {
		System.out.println("--- fall Back -----------------------------------");
		
		ZoneId zone = ZoneId.of("US/Eastern");
		LocalTime time = LocalTime.of(1, 30);
		LocalDate date = LocalDate.of(2016, 11, 06); 
		ZonedDateTime zdt = ZonedDateTime.of(LocalDateTime.of(date, time), zone);
		System.out.println( zdt );
		System.out.println("+1hr : " +  zdt.plusHours(1) );
		System.out.println("+2hr : " +  zdt.plusHours(2) );
		
	}	
	
	private void formatOutput() {
		LocalTime lt = LocalTime.of(14, 14);
		LocalDate ld = LocalDate.of(2011, 4,30);
		LocalDateTime ldt = LocalDateTime.of(ld, lt);
		ZonedDateTime zdt = ZonedDateTime.of(ldt, ZoneId.of("US/Eastern"));
		
		DateTimeFormatter dtfShort = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
		DateTimeFormatter dtfMedium = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
		DateTimeFormatter dtfLong = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
		DateTimeFormatter dtfFull = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL);
		
		System.out.println("short : " + dtfShort.format(zdt));	
				// short : 4/30/11, 2:14 PM
		System.out.println("medium : " + dtfMedium.format(zdt));
				// medium : Apr 30, 2011, 2:14:00 PM
		System.out.println("long : " + dtfLong.format(zdt));
				// long : April 30, 2011 at 2:14:00 PM EDT
		System.out.println("full : " + dtfFull.format(zdt));
				// full : Saturday, April 30, 2011 at 2:14:00 PM Eastern Daylight Time
		
	}
}
