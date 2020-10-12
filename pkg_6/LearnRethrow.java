/**
 * learn change in Exception catching from Java 6 to Java 7 
 */
package pkg_6;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

class MyException extends Exception {
	MyException(){}
	MyException( String msg ){
		super(msg);
	}
}

/**
 * @author Andreas Mann (lokal)
 *
 */
public class LearnRethrow {

	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		LearnRethrow lr = new LearnRethrow();
		lr.test1();
	}

	private void willThrow( boolean doIt ) throws /*MyException ,*/ParseException, RuntimeException{
		if ( doIt ) {
			//throw new IOException("IO");
			//throw new IOException("My");
			//throw new FileNotFoundException("fnf");
			throw new ParseException("",0);
		}
			
	}
	
	private void test1() throws MyException, IOException, ParseException {
		try {
			willThrow( true );
		}
//		catch( ParseException | IOException | MyException e )  {		// Java 6 style
//			System.out.println(e.getMessage());
//			throw e;
//		}
		catch( Exception e )  {						// Java 7 style
			System.out.println(e.getMessage());
			throw e;
		}		
	}
	
}
