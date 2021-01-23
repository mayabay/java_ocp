/**
 * OCP 6.5
 */
package pkg_6;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author andreas
 *
 */
public class HandleAndDeclarePatternTest1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException, SQLException {
		HandleAndDeclarePatternTest1 hadpt1 = new HandleAndDeclarePatternTest1(); 
		hadpt1.caller();
		hadpt1.caller2();
		hadpt1.caller3();
	}

	/* (1) old style */
	private void caller() throws IOException, SQLException {
		try {
			this.someMethod();	
		}catch( IOException e ) {
			// log
			throw e;
		}
		catch( SQLException e ) {
			// log
			throw e;
		}		
	}
	
	/* (2) with multi catch */
	private void caller2() throws IOException, SQLException {
		try {
			this.someMethod();	
		}catch( IOException | SQLException e ) {
			// log
			throw e;
		}	
	}	
	

	/* (3) with Exception catch */
	private void caller3() throws IOException, SQLException {
		try {
			this.someMethod();	
		}catch( Exception e ) {
			// log
			throw e;
		}	
	}		
	
	/* this method gets called from caller()
	 * watch what happens, if you remove SQLException from the signature
	 *  */
	private void someMethod() throws IOException, SQLException  {
		
		Double d = Double.parseDouble("4.56");	// 4,56 throws a NumberFormatException
		throw new IOException("Some io exception");
		
	}
	
}
