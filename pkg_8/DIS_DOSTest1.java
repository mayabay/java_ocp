/**
 * ESSER 11.4/ BS 8.3
 */
package pkg_8;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * DataInput/OutputStream 
 *
 */
public class DIS_DOSTest1 {

	private File counter = new File ( System.getProperty("user.dir") +
			File.separatorChar + "src" + File.separatorChar +
			"counter.dat" ); 
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DIS_DOSTest1 ddt1 = new DIS_DOSTest1();
		ddt1.createCounterFile();
		//ddt1.updateCounter(true);
		//ddt1.updateCounter(false);
		int i = ddt1.readCounter();	
		ddt1.writeCounter(++i);
		
	}

	private void createCounterFile() {
		System.out.println( "counter file = "
				+ counter.getAbsolutePath() );
		
		if ( ! counter.exists() ) { 
			try {
				System.out.println("created = " + counter.createNewFile());	
			}catch( IOException e ) {
				e.printStackTrace();System.exit(-1);
			}
			
		}
		else {
			System.out.println("counter exists");
		}
	}
	
	private int readCounter() {
		DataInputStream in = null;
		int i = 0;
		char c = 'c';
		try{
			in = new DataInputStream( new BufferedInputStream( new FileInputStream(counter) ) );
			c = in.readChar();
			i = in.readInt();	
		}catch( EOFException e ) {
			//e.printStackTrace();
		}
		catch( IOException e ) { 
			e.printStackTrace();
		}
		finally {
			if ( in != null ) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("read() c = " + c + ", i = " + i);
		return i;
	}
	
	private void writeCounter(int value) {
		DataOutputStream out = null;
		int i = 0;
		char c = 'c';
		try {
			out = new DataOutputStream( new BufferedOutputStream( new FileOutputStream(counter) ) );
			out.writeChar('c');
			out.writeInt(value);	
			out.flush();
		
		}catch( EOFException e ) {
			//e.printStackTrace();
		}
		catch( IOException e ) { 
			e.printStackTrace();
		}
		finally {
			if ( out != null ) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}	
	}	
	
	private void updateCounter(boolean initiate) {
		
		DataInputStream in = null;
		DataOutputStream out = null;
		
		int i = 0;
		char c = 'c';
		
		try{
			in = new DataInputStream( new BufferedInputStream( new FileInputStream(counter) ) );
			out = new DataOutputStream( new BufferedOutputStream( new FileOutputStream(counter) ) );
			
			if ( initiate ) {
				out.writeChar('c');
				out.writeInt(++i);	
				out.flush();
			}
			
			c = in.readChar();
			i = in.readInt();
			
			out.writeChar('c');
			out.writeInt(++i);
			
			out.flush();
			
		}catch( EOFException e ) {
			//e.printStackTrace();
		}
		catch( IOException e ) { 
			e.printStackTrace();
		}
		finally {
			if ( in != null ) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if ( out != null ) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		System.out.println("read() c = " + c + ", i = " + i);
		
	}
	
	
}
