/**
 * 
 */
package pkg_6;

/**
 * 
 */
public class ExceptionsTest1 {

	class FatherException extends Exception {}
	class ChildException extends FatherException {}
	
	class Closy implements AutoCloseable{
		@Override
		public void close() {
			System.out.println("\tclosing Closy ..");
		}
		String sayHello() {
			return "hello";
		}		
	}

	class Door implements AutoCloseable{
		@Override
		public void close() {
			System.out.println("\tclosing Door ..");
			throw new RuntimeException("cannot close Door ..");
		}
		String sayHello() {
			return "hello";
		}		
	}	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExceptionsTest1 e = new ExceptionsTest1();
		//e.test1();
		//e.test2();
		e.test3();
	}

	private  void test1() {
		
//		try( Closy c = new Closy(); )
//			System.out.println(c.sayHello());
		
		try( Closy c = new Closy(); ){
			System.out.println(c.sayHello());
		}
	}
	
	
	/*
		in twr
			closing Door ..
			closing Closy ..
		caught : cannot close Door ..
		in finally
	 * */
	private void test2() {
	
		try( Closy c = new Closy(); Door door = new Door() ){
			System.out.println("in twr");
		}catch( Exception e ) {
			System.out.println("caught : " + e.getMessage());
		}finally {
			System.out.println("in finally");
		}
		
	}
	
	private void test3() {
		
		try( Closy c = new Closy(); Door door = new Door() ){
			System.out.println("in twr");
			throw new Exception( "Exc in twr happened!" );
		}catch( Exception e ) {
			System.out.println("caught : " + e.getMessage());
			System.out.println("caught : " + e.getMessage() + ", with surpressed e : " + e.getSuppressed().length);
		}finally {
			System.out.println("in finally");
		}
		
	}	
	
	private void test4() throws ChildException{
		try {
			throw new FatherException();
		}catch( FatherException e ) {
			// e = new RuntimeException();
				// DNC RuntimeException is not a Father Exception
			//throw e;
		}
	}
	
}
