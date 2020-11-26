/**
 * BS 8.2.3
 */
package pkg_8;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 *
 */
public class MarkAStreamTest1 {

	private static final String WIN_USER_HOME = "USERPROFILE";
	private static final String UNIX_USER_HOME = "HOME";
	private static final String IMMO_DATA_DIR_NAME = "immodata";
	private static final String IMMO_DATA_PATH;
	
	static {
		IMMO_DATA_PATH = File.separatorChar == '/' ? 
				(System.getenv(UNIX_USER_HOME) + File.separatorChar + IMMO_DATA_DIR_NAME) 
				:
				(System.getenv(WIN_USER_HOME) + File.separatorChar + IMMO_DATA_DIR_NAME);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MarkAStreamTest1 mast1 = new MarkAStreamTest1();
		mast1.test2();
	}

	private void test1() {
		//File immodataRoot = new File( IMMO_DATA_PATH );
		File abcFile = new File(IMMO_DATA_PATH + File.separatorChar + "ABC.txt");
		
		BufferedReader in = null;
		
		try{
			in = new BufferedReader( new FileReader( abcFile ) );
			int c = 0;
			while( (c = in.read()) != -1 ) {
				System.out.print( Character.valueOf( (char)c ));
			}
		} catch( FileNotFoundException e ) {
			e.printStackTrace();
		} catch( IOException e ) {
			e.printStackTrace();
		}
		finally {
			try {
				if (in != null) in.close();	
			}catch( IOException e ) {
				e.printStackTrace();
			}
			
		}
	}
	
	
	private void test2() {
		File abcFile = new File(IMMO_DATA_PATH + File.separatorChar + "ABC.txt");
		try( 
				BufferedReader in = new BufferedReader(
					new InputStreamReader(
							new FileInputStream( abcFile )
					)
				); 
			){
			int A = in.read();
			System.out.println( (char)A );	// 65
			int B = in.read();			// 66
			System.out.println( (char)B );
			if ( in.markSupported() ) {
				in.mark(2);	// only 2 more chars allowed to read
				System.out.println( (char)in.read() );	// 67 C
				System.out.println( (char)in.read() );	// 68 D
				System.out.println( (char)in.read() );	// 69 E
				System.out.println( (char)in.read() );	// 70 F
				in.reset(); // now before 67
			}
			System.out.println( (char)in.read() );	// C
			long skipped = in.skip(4); // DEFG
			System.out.println("\tskipped " + skipped + " values");
			System.out.println( (char)in.read() );	// H
		}
		catch( IOException e ) {
			e.printStackTrace();
		}
	}
	
}
