/**
 * ListResourceBundle for fr_CAN
 * */
package pkg_5;

import java.util.Arrays;
import java.util.ListResourceBundle;

public class labels_fr_CAN extends ListResourceBundle {

	private void test() {
		int[] iArr = new int[2];
		int[] iArr2 = { 3,4 };
		printArray(new int[] { 5,6,7 });
		
	}
	
	private void printArray( int[] iArr ) {
		Arrays.toString(iArr);
	}
	
	protected Object[][] getContents() {
	
		return new Object[][] {
			{ "key","value" },
			{ "hello","Bonjour Java from Canada! labels_fr_CAN.java" }
			
		};
	}

}
