/**
 * 
 */
package pkg_3;

import java.time.LocalDate;

/**
 * @author andreas
 *
 */
public class TestEquals {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestEquals te = new TestEquals();
		te.test1();

	}

	private void test1() {
		LocalDate ld1 = LocalDate.of(1973, 8,5);
		LocalDate ld2 = LocalDate.of(1973, 8,5);
		System.out.println("hc ld1 = " + ld1.hashCode() + ", ld2 = " + ld2.hashCode());
		// two identical local dates have same hashcode
	}
	
}
