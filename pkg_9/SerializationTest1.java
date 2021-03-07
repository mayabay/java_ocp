/**
 * KB  5.5
 */
package pkg_9;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class Collar {
	private int size;
	Collar(int size){
		this.size = size;
	}
	int getSize() {
		return this.size;
	}
}

class Animal {
	protected String species;
	protected int countLiving;
	{ 
		species = "Canidae";
		countLiving = 42;
	}
	
	protected Animal() {}
	// if missing java.io.InvalidClassException: pkg_9.Dog; no valid constructor
	
	protected Animal( String species, int countLiving ) {
		this.species = species;
		this.countLiving = countLiving;
	}
}

class Dog extends Animal implements Serializable {
	
	private transient Collar collar;		// transient will skip field while serialzing
	private String name;
	Dog( Collar collar, String name ){
		super( "bla",-17 );
		this.collar = collar;
		this.name = name;
		//this.species = "Primates";
	}
	
	/*
	 * Classes that require special handling during the serialization and
	 *  deserialization process must implement special methods with these exact signatures:
	 *  
	 *  java.io.InvalidClassException: pkg_9.Dog; no valid constructor
	 *  if Animal has no no-arg ctor
	 *  
	 * */
	private void writeObject(ObjectOutputStream out) {
		System.out.println(".. writeObject");
		try {
			out.defaultWriteObject();
			out.writeInt(collar.getSize());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void readObject(ObjectInputStream in) {
		System.out.println(".. readObject");
		try {
			in.defaultReadObject();
			this.collar = new Collar(in.readInt());
		}catch( IOException | ClassNotFoundException e ) {
			e.printStackTrace();
		}
	}
	
	public String toString() {
		return "[Dog, name = "+name+", collar size = "+collar.getSize()+","
				+ " species = "+this.species+", countLivin = "+this.countLiving+"]";
			// Exception in thread "main" java.lang.NullPointerException if collar is transient
	}
}

/**
 * Serialize and deserialize java classes
 *
 */
public class SerializationTest1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Collar collar = new Collar(12);
		Dog dog = new Dog( collar, "fluffy");

		Path file = Paths.get(".", "src", "zoo", "fluffy_Dog.ser");
		try {
			if (Files.notExists(file))
				Files.createFile(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		SerializationTest1 st1 = new SerializationTest1();
		st1.serialize(file, dog);
		
		st1.deserialize(file);
	}

	private void serialize( Path file, Dog dog ) {
		
		try( FileOutputStream fos = new FileOutputStream( file.toFile() );
				ObjectOutputStream out = new ObjectOutputStream(fos);
				){
			out.writeObject(dog);
			
			
		}catch( IOException e ) {
			e.printStackTrace();
			return;
		}
	}
	
	private void deserialize( Path file ) {
		try( 
				FileInputStream fis = new FileInputStream(file.toFile());
				ObjectInputStream in = new ObjectInputStream(fis);
				){
			
			Dog  fluffy = (Dog) in.readObject();
			System.out.println("fluffy from file : " + fluffy);
			
		}catch( IOException | ClassNotFoundException e ) {
			e.printStackTrace();
			return;
		}
	}
	
}
