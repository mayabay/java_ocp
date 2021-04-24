/**
 * 
 */
package review_kb;

import java.time.LocalDate;
import java.time.Month;

/**
 *
 */
public class Ch4 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Ch4 o = new Ch4();
		o.test1();
	}
	
	private void test1() {
		LocalDate d1 = LocalDate.of(2017,Month.NOVEMBER,28);
		System.out.println( d1.minusMonths(11) );
		
	}
	

}
