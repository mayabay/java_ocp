/**
 * test DST change
 */
package pkg_5;
import java.time.Duration;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Set;

/**
 * 
 *
 */
public class DateTimeTest1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DateTimeTest1 dtt1 = new DateTimeTest1();
		//dtt1.listZones();
		//dtt1.test1();
		dtt1.test2();

	}

	private void listZones() {
		Set<String> zones = ZoneId.getAvailableZoneIds();
		zones.stream()
		
		.filter(s->s.contains("US"))
		
		.map( s -> ZoneId.of(s) )
		.peek( zid -> System.out.println( zid.getDisplayName(java.time.format.TextStyle.FULL, Locale.getDefault()) ) )
		.forEach(System.out::println);
		
	}
	
	/* US/Pacific Standard time to DST time */
	private void test1() {
		ZonedDateTime zdt1 = ZonedDateTime.of(2021,3,13, 21, 30, 0,0, ZoneId.of("US/Pacific") );
		System.out.println( zdt1.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME) );
		
		Duration du24Hrs = Duration.ofHours(24);
		Duration du1Day = Duration.ofDays(1);
		Period pe1Day = Period.of(0, 0, 1);
		
		System.out.println("..");
		
		System.out.println("add Duration of 24 hours: ");
		ZonedDateTime zdt1MofifiedByDuration = zdt1.plus(du24Hrs);
		System.out.println( zdt1MofifiedByDuration.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME) );
		
		System.out.println("..");
		
		System.out.println("add Duration of 1 Day: ");
		ZonedDateTime zdt1MofifiedByDuration1 = zdt1.plus(du1Day);
		System.out.println( zdt1MofifiedByDuration1.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME) );		
		
		System.out.println("..");
		
		System.out.println("add Peroid of 1 day: ");
		ZonedDateTime zdt1MofifiedByPeroid = zdt1.plus(pe1Day);
		System.out.println( zdt1MofifiedByPeroid.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME) );
		
	}
	
	/* US/Eastern from DST to Standard time */
	private void test2() {
		
		ZonedDateTime zdt1 = ZonedDateTime.of( 2021,11,6,18,0,0,0,ZoneId.of("US/Eastern") );
		System.out.println( zdt1 );
		
		System.out.println("..");
		
		Duration du1Day = Duration.ofDays(1);
		Period pe1Day = Period.of(0,0,1);
		
		System.out.println("..");
		
		System.out.println("Change with Duration of 1 day");
		
		ZonedDateTime modifiedByDuration = zdt1.plus(du1Day);
		System.out.println( modifiedByDuration );
		
		System.out.println("..");
		
		System.out.println("Change with Peroid of 1 day");
		
		ZonedDateTime modifiedByPeroid = zdt1.plus(pe1Day);
		System.out.println( modifiedByPeroid );		
	}
	
}
