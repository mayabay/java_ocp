/**
 * BS 3
 */
package pkg_3;

import java.util.ArrayList;
import java.util.List;

/**
 * Raw Types
 *
 */
public class RawTypesTest1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RawTypesTest1 rtt1 = new RawTypesTest1();
		rtt1.test1();

	}

	private void test1() {
		List list = new ArrayList();
		list.add(1);
		list.add(2);
		list.add(3);
		//Integer i = list.get(0);
		Object i = list.get(0);
		//int i2 = list.get(0); // DNC
		
		// Integer i = (Integer)list.get(1);
		System.out.println(i);
	}
	
}
