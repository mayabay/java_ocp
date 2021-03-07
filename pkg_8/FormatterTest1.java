/**
 * 
 */
package pkg_8;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

/**
 * tests for java.util.Formatter 
 * https://www.baeldung.com/java-string-formatter
 */
public class FormatterTest1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FormatterTest1 ft1 = new FormatterTest1();
		ft1.test1();

	}

	private void test1() {
		String str = "";
		Path path = Paths.get("/tmp","sub","sub");
		
		double d = 1.23456D;
		System.out.printf( "€ %3.2f%n",d );
		
		LocalDateTime ldt = LocalDateTime.now();
		
		System.out.printf( "date : %tY-%1$tm-%1$td , say '%12s' and %2$s %n", ldt, "hello");
		
	}
	
}
