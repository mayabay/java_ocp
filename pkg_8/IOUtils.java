/**
 * BS 8.3
 */
package pkg_8;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * file copy example
 *
 */
public class IOUtils {

	/**
	 * main
	 */
	public static void main(String[] args) {
		IOUtils.test1();

	}
	
	private static void test1() {
		File source = new File( ConfigObj.IMMO_DATA_PATH + File.separatorChar + "ThomasMann_TodInVenedig.txt" );
		if ( ! source.exists() ) throw new IllegalStateException("file not found: " + source);
		
		File destination = new File( ConfigObj.IMMO_DATA_PATH + File.separatorChar + "CopyOf_ThomasMann_TodInVenedig.txt" );
		System.out.println("copied : " + IOUtils.copyFile(source, destination));
		
		
	}
	
	/**
	 * copies a file from source to destination
	 * @param source
	 * @param destination
	 * @return true, on success
	 * */
	public static boolean copyFile (File source, File destination) {
		if( !source.exists() ) { throw new IllegalArgumentException("source does not exist!"); }
		
		try( BufferedInputStream in = new BufferedInputStream(new FileInputStream(source));
			 BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(destination))	
				){
			
			byte[] buf = new byte[ 1024 ];
			int bytesRead = 0;
			while ( (bytesRead = in.read(buf, 0, buf.length)) > 0 ) {
				out.write(buf, 0, bytesRead);
				out.flush();
			}
			
			return true;
			
		}catch( IOException e ) {
			e.printStackTrace();
			return false;
		}
	}

}
