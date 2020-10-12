/**
 * Learn Locale in java.lang
 * 
 */
package pkg_5;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

/**
 * Locale in java.lang
 *
 */
public class LearnLocales {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LearnLocales ll = new LearnLocales();
		ll.printLocale();

	}
	
	private void printLocale() {
		Locale locale = Locale.getDefault();
		System.out.println("my Locale: " + locale);
		
		ZonedDateTime zdt = ZonedDateTime.of( LocalDateTime.now(), ZoneId.of("Europe/Berlin") );
		System.out.println("zdt with my locale:  " +  zdt );
		
		System.out.println("zdt with locale of de_DE FULL : " + zdt.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL).withLocale(new Locale("de"))  ) );
		System.out.println("zdt with locale of de_DE LONG : " + zdt.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG).withLocale(new Locale("de"))  ) );
		System.out.println("zdt with locale of de_DE MEDIUM: " + zdt.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(new Locale("de"))  ) );
		System.out.println("zdt with locale of de_DE SHORT: " + zdt.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).withLocale(new Locale("de"))  ) );
		System.out.println("zdt with locale of hi IN LONG: " + zdt.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG).withLocale(new Locale("hi", "IN"))  ) );
	}

}
