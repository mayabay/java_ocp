package pkg_5;

import java.time.Instant;
import java.time.ZoneId;
import java.time.zone.ZoneRules;

public class ZoneTest1 {

	public static void main(String[] args) {
		ZoneId usPacific = ZoneId.of("US/Pacific");
		ZoneRules zr = usPacific.getRules();
		System.out.println("DST active in Zone US/Pacific : " +  zr.isDaylightSavings(Instant.now()) );
		System.out.println("current offset : " + zr.getDaylightSavings(Instant.now()));	// PT1H DST gap
		System.out.println( zr );

	}

}
