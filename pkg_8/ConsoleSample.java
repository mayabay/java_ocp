/**
 * BS 8.4.1
 */
package pkg_8;

import java.io.Console;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.Arrays;

/**
 * 
 *
 */
public class ConsoleSample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ConsoleSample cs = new ConsoleSample();
		cs.test1();
	}

	private void test1() {
		Console console = System.console();
		if ( console == null ) {
			throw new IllegalStateException("No console available!");
		}
		
		Reader reader = console.reader();
		PrintWriter writer = console.writer();
		
		System.out.println("enter command (x for exit, p for password)");
		
		while ( true ) {				
			console.flush();		// call flush before readLine() 
			String input =  console.readLine();	
			if (input.equals("x")) break;
			

			//System.out.println("you entered " + input);
			console.format("you entered %s%n", input);			
			
			if ( input.equals("p") ) {
				console.flush();
				char[] password = console.readPassword("enter your password : ");
				console.flush();
				char[] verify = console.readPassword("verify your password : ");
				console.printf("Your inputs match %S : ", (Arrays.equals(password, verify) ? "yes" : "no"));
				
				console.format("your password is %d characters long %n", password.length);
				this.clearArray(password); this.clearArray(verify);
			}
		}
		
		System.out.println("end");
		
	}
	
	private void clearArray(char[] arr) {
		for( int i = 0; i < arr.length; i++ ) {
			arr[i] = 'x';
		}
	}
	
}
