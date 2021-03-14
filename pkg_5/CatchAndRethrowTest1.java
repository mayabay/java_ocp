/**
 * Catch Exception and rethrow 
 */
package pkg_5;

import java.io.IOException;
import java.sql.SQLException;

/**
 *
 */
public class CatchAndRethrowTest1 {

	/**
	 * @param args
	 */
	public static void main(String[] args)  {
		CatchAndRethrowTest1 catch1 = new CatchAndRethrowTest1();
		try {
			catch1.test1();	
			
		}catch( Exception e ) {
			// ..
			e.printStackTrace();
		}
		

	}

	private void canThrow() throws SQLException {
		// ..
		throw new SQLException();
	}
	
	private void canThrow2() throws IOException {
		// ..
		throw new IOException();
	}	
	
	private void test1() throws SQLException, IOException {
		
		try {
			//this.canThrow();
			this.canThrow2();
		}catch(Exception e) {
			// log e
			throw(e);
		}
		
	}
	
}
