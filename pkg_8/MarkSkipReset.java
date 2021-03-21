/**
 * 
 */
package pkg_8;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 */
public class MarkSkipReset {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MarkSkipReset msr = new MarkSkipReset();
		msr.test1();
		msr.test2();

	}

	private void test1() {
		String s = "XYZABC";
		try( BufferedOutputStream bos = new BufferedOutputStream( new FileOutputStream( new File("./tmp/msr.ser") ) );){
			bos.write( s.getBytes() );
		}catch( IOException e ) {
			e.printStackTrace();
		}
	}
	
	private void test2() {
		try (InputStream is = new BufferedInputStream( new FileInputStream( new File("./tmp/msr.ser") ) )){
			String r = pullBytes(is, 3);
			System.out.println(r);
		}catch( IOException e ) {
			e.printStackTrace();
		}
	}
	
	// XYZABC
	public static String pullBytes( InputStream is, int count ) throws IOException {
		is.mark(count);	// The readlimit arguments tells this input stream to allow
						// that many bytes to be read before the mark position getsinvalidated. 
		final StringBuilder sb = new StringBuilder();
		for( int i = 0; i < count; i++ ) {
			sb.append( (char)is.read() );
		}
		
		is.reset();
		is.skip(1);		
		sb.append((char)is.read());
		return sb.toString();
	}
	
}
