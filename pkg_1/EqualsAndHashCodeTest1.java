/**
 * KB 6.1
 */
package pkg_1;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

class BungiWungi implements Serializable, Comparable<BungiWungi> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int id;
	int val1;
	transient int val2;		// on deserialization val2 will be 0
	BungiWungi(int val1, int val2){
		this.id = EqualsAndHashCodeTest1.getCountVal();
		this.val1 = val1; this.val2 = val2;
	}
	int getVal1() { return val1; }
	int getVal2() { return val2; }
	public int hashCode() {
		return (val1 ^ 31) + (val2 ^ 31);
	}
	public boolean equals( Object o ) {
		if (o == null) return false;
		if ( ! (o instanceof BungiWungi) ) return false;
		if ( 
				((BungiWungi)o).val1 != this.val1
				&&
				((BungiWungi)o).val2 != this.val2
				) return false;
		return true;
	}
	public String toString() {
		return "[id = "+id+" : val1 = "+val1+", val2 = "+val2+"]";
	}
	public int compareTo( BungiWungi other ) {
		return this.val1 - other.val1;
	}
}

/**
 * Equals and hashcode contract
 *
 */
public class EqualsAndHashCodeTest1 {

	private static int counter;
	
	static synchronized int getCountVal() {
		return ++counter;
	} 
	
	private static final Path workingDirectory = Paths.get("/tmp"); // Paths.get(".","src");
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("current working directory = " + workingDirectory.toAbsolutePath());
		EqualsAndHashCodeTest1 eht1 = new EqualsAndHashCodeTest1();
		eht1.test1();
	}

	private void test1() {
		
		// (1) create  objects
		BungiWungi bw1 = new BungiWungi(3, 4);
		BungiWungi bw2 = new BungiWungi(3, 5);
		System.out.println("hc of bw1 = " + bw1.hashCode());
		System.out.println("hc of bw2 = " + bw2.hashCode());
		
		// (2) serialize
		this.serializeBW(bw1);		// 			/tmp/BungiWungi1.ser
		this.serializeBW(bw2);		// 			/tmp/BungiWungi2.ser
		
		// (3) put objects in HashSet
		Set<BungiWungi> set = new HashSet<>();
		set.add(bw1);
		set.add(bw2);
		System.out.println("set contains: ");
		System.out.println(set);
		
		
		BungiWungi searchForBW2 = new BungiWungi(3, 5);
		System.out.println("set contains bw 3,5 = " + (set.contains(searchForBW2)));
		
		BungiWungi searchForBW2Deserialized = this.deserializeBW( 
				new File( workingDirectory.toAbsolutePath() + "/BungiWungi2.ser" )
				);
		
		System.out.println("set contains bw 3,5 = " + (set.contains(searchForBW2Deserialized)));
	}
	
	private void serializeBW( BungiWungi bw ) {
		File file = new File( workingDirectory.toAbsolutePath() + "/BungiWungi"+bw.id+".ser" );
		
		if (file.exists()) file.delete();
		
		try( ObjectOutputStream out = new ObjectOutputStream (
				new BufferedOutputStream( new FileOutputStream( file ) )
				);
			)
				 {
			out.writeObject(bw);
			
		}catch( IOException ioe ) {
			ioe.printStackTrace();
		}
	}
	
	private BungiWungi deserializeBW( File f ) {
		if ( ! f.exists() ) {
			throw new IllegalStateException("file does not exist");
		}
		try(
				ObjectInputStream in = new ObjectInputStream( 
						new BufferedInputStream (
								new FileInputStream( f ))
						)
				){
			BungiWungi bw = (BungiWungi)in.readObject();
			return bw;
			
		}catch( IOException | ClassNotFoundException e ) {
			e.printStackTrace();
		}
		return null;
	}
	
}
