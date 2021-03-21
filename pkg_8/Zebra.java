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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *   
 */
public class Zebra implements Serializable {

	public static final long serialUID = 1L;
	private transient String name = "Goerge";
	private static String birthPlace = "Africa";
	private transient Integer age;
	private java.util.List<Zebra> friends = new java.util.ArrayList<>();
	private Object tail = null;
	{ age = 10; }
	public Zebra() {
		this.name = "Sophia";
	}
	
	@Override
	public String toString() {
		return "Zebra [name=" + name + ", age=" + age + ", friends=" + friends + ", tail=" + tail + "]";
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Zebra z = new Zebra();
		//Zebra z = new Zebra(); z.tail = "tail";
		Zebra z= new Zebra(); 	z.tail = 42;
		try( ObjectOutputStream oos = new ObjectOutputStream(
				new BufferedOutputStream (
						new FileOutputStream(new File("./tmp/Zebra.ser"))
					)
				) 
			){
			oos.writeObject(z);
		}catch( IOException e ) {
			e.printStackTrace();
		}

		System.out.println("now deserialize ...");
		
		Zebra zebraAlive = null;
		
		try( ObjectInputStream ois = new ObjectInputStream(
				new BufferedInputStream(
						new FileInputStream(new File("./tmp/Zebra.ser"))
					)
				) 
			){
			zebraAlive = (Zebra)ois.readObject();
		}catch(IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("zebra from file ");
		System.out.println(zebraAlive);
	}

	
	
}
