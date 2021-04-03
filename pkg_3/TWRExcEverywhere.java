/**
 * 
 */
package pkg_3;

import java.io.IOException;

/**
 * 
 *
 */
public class TWRExcEverywhere {

	class CannotCloser implements AutoCloseable {
		public void close() {
			throw new RuntimeException("too stupid to close");
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		TWRExcEverywhere t = new TWRExcEverywhere();
		t.test1();
	}
	
	private void test1() throws IOException {
		try( CannotCloser cc = new CannotCloser(); ){
			throw new IOException();
		}catch( Exception e ) {
			throw new RuntimeException("reset everything");
		}
	}

}
