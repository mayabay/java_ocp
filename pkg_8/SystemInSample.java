/**
 * BS 8.4.1
 */
package pkg_8;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 
 *
 */
public class SystemInSample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SystemInSample sis = new SystemInSample();
		sis.interactWithUser();
	}

	private void interactWithUser() {

		try( BufferedReader in = new BufferedReader( new InputStreamReader(System.in)); ) {

			uiloop:  while(true) {
				System.out.println("inout command (x for exit) : ");
				String userInput = in.readLine();
				System.out.println("command entered : " + userInput);
				if ( userInput.equals("x") ) { break uiloop; }
			}
		} catch( IOException e ) {
			e.printStackTrace(); 
		}		
		System.out.println("end");

	}
	
}
