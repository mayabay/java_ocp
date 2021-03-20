/**
 * 
 */
package pkg_8;

import java.io.Console;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Objects;

/**
 * 
 */
public class ConsoleTest2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ConsoleTest2 ct1 = new ConsoleTest2();
		ct1.test1();
	}

	private void test1() {
		Console con = System.console(); 
		
		if ( Objects.isNull(con) ) {
			System.out.println("No Console available.");
			return;
		}
			
		//Reader r = con.reader();
		String s = con.readLine("input text here : ");
		System.out.println("your input : " + s);
		try {
			Writer w = con.writer();
			System.out.println("Your text here : ");
			if ( (s = con.readLine()) != null  ) {
				w.append(s);
			}  
			w.flush();			
		}catch(IOException io) {
			io.printStackTrace();
		}

	}
	
}
