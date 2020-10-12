/**
 * Learn try-with-resource
 */
package pkg_6;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Andreas Mann (lokal)
 *
 */
public class LearnTryWithResources {

	private class JammedTurkeyCage implements AutoCloseable {

		private String id;
		
		public JammedTurkeyCage(String id) {
			this.id = id;
		}
		
		@Override
		public void close() throws IllegalStateException {
			throw new IllegalStateException("Cage door does not close! [id="+id+"] ");
		}
		
		public void addTurkey(String turkeyName) {
			System.out.println(turkeyName + " was added.");
		}

		@Override
		public String toString() {
			return "JammedTurkeyCage [id=" + id + "]";
		}
		
		
	}
	
	private static final String pathToFile1 = "D:\\projects\\eclipse-workspace\\java_ocp\\src\\pkg_6\\data.txt";
	private static final String pathToFile2 = "D:\\projects\\eclipse-workspace\\java_ocp\\src\\pkg_6\\data_copy.txt";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LearnTryWithResources lwr = new LearnTryWithResources();
		//lwr.test1();
		//lwr.test2();
		lwr.test3();
	}

	@SuppressWarnings("finally")
	private void test3(){
		
		try( JammedTurkeyCage jtc = new JammedTurkeyCage("1"); JammedTurkeyCage jtc2 = new JammedTurkeyCage("2"); ){
			jtc.addTurkey("Fred"); jtc.addTurkey("Wilma");
			throw new IllegalStateException("Turkey ran off!");
		}
//		catch( IllegalStateException e ) {
//			System.out.println("e in try : " + e.getMessage());
//			for ( Throwable t : e.getSuppressed() ) {
//				System.out.println("\t surpressed in e : " + t.getMessage());
//			}
//		}
		finally {
			System.out.println("finally");
			throw new RuntimeException("Exeption in finally!");
		}
		
	}
	
	private void test2() {
		
		File file = new File(pathToFile1);
		
		byte[] buffer = new byte[256];
		
		try (BufferedInputStream buffIn = new BufferedInputStream(new FileInputStream(pathToFile1));) {
			while( buffIn.read(buffer, 0, buffer.length) != -1  ) {
				System.out.println("buffer read: ");
				for( byte b : buffer ) {
					if ( b != 0 ) {
						System.out.print( b + " ") ;
						//System.out.print( b != 0 ?  b + " " : "-" );
						System.out.print( new Character((char)b) );
						
						System.out.println();						
					}
				}
			}
			
		}
		catch( IOException e ) {
			
		}
	}
	
	private void test1() {
		
		// "D:\\projects\\eclipse-workspace\\java_ocp\\src\\pkg_5"
		File fileIn = new File( pathToFile1 );
		File fileOut = new File( pathToFile2 );
		
		if (fileOut.exists()) {
			fileOut.delete();
		}
		
		String line = "";
		
		int lineNr = 1;
		
		try( 	BufferedReader buf = new BufferedReader(new FileReader(fileIn));
				BufferedWriter bufOut = new BufferedWriter(new FileWriter(fileOut));
				
				){
			while ( (line = buf.readLine()) != null ) {
				System.out.printf("line nr: %1$d : %2$s \n", lineNr, line);
				bufOut.write(line);
				lineNr++;
			}
			
			
		}catch( IOException e ) {
			e.printStackTrace();
		}
		
	}
	
}
