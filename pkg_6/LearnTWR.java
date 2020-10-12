/**
 * Learn try with resource
 * 
 * */
package pkg_6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LearnTWR {

	private class Bag implements AutoCloseable {
		public void close() throws IOException {
			//System.out.println("Bag is closed.");
			throw new IOException("Bag could not be closed!");
		}
	}
	
	private class Box implements AutoCloseable {
		public void close() throws Exception {
			throw new Exception("Box could not be closed!");
			//System.out.println("Box is closed.");
		}
	}
	
	private String  pathToFile = "C:\\Users\\Andreas\\Documents\\sql_notes.sql";
	
	public static void main(String[] args)  {
		LearnTWR ltwr = new LearnTWR();
		try {
			ltwr.test1();	
		}catch( Exception e ) {
			System.err.println("main() : " + e.getMessage());
			for ( Throwable t : e.getSuppressed() )
				System.err.println("\tsuppressed: " + t.getMessage());			
		}
		
	}
	
	@SuppressWarnings("finally")
	private void test1() throws Exception {
		try (	BufferedReader bufReader = new BufferedReader(new FileReader(pathToFile));
				Bag bag = new Bag();
				Box box = new Box();
				) {
			System.out.println("try");
			String line;
			while( (line = bufReader.readLine()) != null ) {
				System.out.println(line);
			}
			throw new RuntimeException("try runtime");
		}
		catch( RuntimeException e ) {
			System.err.println(e.getMessage());
			for ( Throwable t : e.getSuppressed() )
				System.err.println("\tsuppressed: " + t.getMessage());			
			//throw e;
			//throw new IOException("IOExc : file not found1");
		}
		catch( IOException e ) {
			System.err.println(e.getMessage());
			for ( Throwable t : e.getSuppressed() )
				System.err.println("\tsuppressed: " + t.getMessage());
			throw e;
		}
		catch( Exception e ) {
			System.err.println(e.getMessage());
			for ( Throwable t : e.getSuppressed() )
				System.err.println("\tsuppressed: " + t.getMessage());
			throw e;
		}		
		finally {
			System.out.println("finally");
			//throw new IOException("IOExc in finally : file not found1");
		}
	}
	
	private void handleException(Exception e) {
		
	}
	
}
