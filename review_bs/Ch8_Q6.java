/**
 * 
 */
package review_bs;
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
 * Serialize and Deserialize inner types
 */
public class Ch8_Q6 /*implements Ch8_Q6.SubSerializable*/ implements Serializable {
				// DNC cycle detected  a class cannot implement a sub type
	// RTE  java.io.NotSerializableException: review_bs.Ch8_Q6
	
	
	
	public interface SubSerializable extends Serializable {
		void serialize( ObjectOutputStream oos );
		void deserialize( ObjectInputStream ois );
	}

	class Animal {
		int ageInYears;
		Animal(){
			ageInYears = 42;
		}
	}
	
	class Dog extends Animal implements Serializable, SubSerializable {
		// RTE java.io.InvalidClassException: review_bs.Ch8_Q6$Dog; no valid constructor
		
		transient String name;
		int numLegs = 4;
		void removeLeg( int n ) { numLegs -= n;  }
		public Dog(){
			this.name = "Rex";
			this.removeLeg(1);
			this.ageInYears = 24;
		}
		
		public void serialize(ObjectOutputStream oos) {
			try {
				oos.writeObject(name);	
				oos.defaultWriteObject();
			}catch( IOException ioe ) {
				ioe.printStackTrace();System.exit(-1);
			}			
		}
		
		private void writeObject( ObjectOutputStream oos ) {
			this.serialize( oos );
		}
		
		public void deserialize(ObjectInputStream ois) {
			try {
				name = (String)ois.readObject();	
				ois.defaultReadObject();
			}catch(ClassNotFoundException | IOException e ) {
				e.printStackTrace();System.exit(-1);
			}				
		}
		
		private void readObject( ObjectInputStream ois ) {
			this.deserialize(ois);
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Ch8_Q6 test = new Ch8_Q6();
		test.test1();
		
		
		
	}

	private void test1() {
		File f = new File("./tmp");
		if (!f.exists()) f.mkdir();
		
		File fser = new File( "./tmp/chp8_q6.ser" );
		if (fser.exists()) fser.delete();
		
		Dog d = new Dog();
		d.numLegs -= 1; // is 2 now
		
		System.out.println("name : " + d.name);		// Rex
		System.out.println("numLegs : " + d.numLegs);	// 2
		System.out.println("ageInyears : " + d.ageInYears);	// 24
		
		try (ObjectOutputStream oos = new ObjectOutputStream( new BufferedOutputStream( new FileOutputStream(fser) ) );) {
			oos.writeObject(d);
		}catch( IOException ioe ) {
			ioe.printStackTrace(); System.exit(-1);
		}
		
		System.out.println("...");
		
		Dog d2 = null;
		try ( ObjectInputStream ois = new ObjectInputStream( new BufferedInputStream( new FileInputStream( fser ) ) ); ) {
			d2 = (Dog)ois.readObject();	// RTE java.io.InvalidClassException: review_bs.Ch8_Q6$Dog; no valid constructor
			
			/* 
			Thrown when the Serialization runtime detects one of the following problems with a Class.
			The serial version of the class does not match that of the class descriptor read from the stream
			The class contains unknown datatypes
			The class does not have an accessible no-arg constructor
			
			https://stackoverflow.com/questions/7144912/why-is-a-serializable-inner-class-not-serializable
			
			Is there a way to serialize a non-static inner class without the enclosing class,
			 e.g. by making the reference to the outer class transient?
			No. What would happen when you deserialize such a class and then try
			 to call an instance method of the outer class? A NullPointerException?
			 
			 https://github.com/pmd/pmd/issues/2992 <- read this
			 Avoid serializable inner classes
			 
			 * */
			
		}catch( ClassNotFoundException | IOException e ) {
			e.printStackTrace();System.exit(-1);
		}
		
		System.out.println("name : " + d2.name);
		System.out.println("numLegs : " + d2.numLegs);
		System.out.println("ageInyears : " + d2.ageInYears);		
		
	}
	
	
}
