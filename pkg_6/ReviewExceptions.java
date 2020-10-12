/**
 * Review exeptions
 */
package pkg_6;

import java.io.IOException;

/**
 * @author Andreas Mann (lokal)
 *
 */
public class ReviewExceptions {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ReviewExceptions re = new ReviewExceptions();
		re.test1();
		re.test2("user","Pa$$w0rd");
		//re.test3();	// DNC handle or declare
		//re.test4(); 	// DNC handle or declare
		re.test5();
		//re.test6();		// DNC 
	}

	private void test1() {
		
		try {
			System.out.println("hello");
		}
		finally {
			System.out.println("finally");
		}
		
	}
	
	private void test2(String loginName, String password) throws RuntimeException {
		if ( !loginName.equals("user") || !password.equals("Pa$$w0rd") ) {
			throw new UserLoginNotValidatedException( new RuntimeException("not validated1") );
		}
	}
	
	private void test3() throws Throwable {
		
	}	
	
	private void test4() throws Exception {
		
	}		
	
	private void test5() {
		try {
			
		}catch(Exception e) {
			// 
		}
	}
	
	private void test6() throws IOException {
		// IOException never thrown here
	}
	
	private void test7() {
//		try {}
//		catch( Exception | RuntimeException e ) { // The exception RuntimeException is already caught by the alternative Exception
//			//
//		}
	}
	
}
