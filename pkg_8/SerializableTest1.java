/**
 * BS 8.3.3
 */
package pkg_8;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Learn Serialization
 */
public class SerializableTest1 implements Serializable {

	private static final long serialVersionUID = 2L;
	
	class Tail implements Serializable {
		private static final long serialVersionUID = 2L;
		int length;
	}
	
	class Cat implements Serializable {
		
		// if not available, will be implemented by compiler
		private static final long serialVersionUID = 3L;
		String name;
		Tail tail;
		String furColor;
		int legs = 0;
		{ legs = 4; }
		Cat(Tail tail, String furColor){
			this.tail = tail;
			//this.furColor = furColor;
			this.furColor = "green";
		}
		public String toString() {
			return "[Cat, name = "+this.name+","
					+ " with tail length = "+this.tail.length+","
					+ " furColor = "+this.furColor+","
					+ " legs = "+ this.legs +" ]";
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		File file = new File( ConfigObj.IMMO_DATA_PATH + File.separatorChar + ("Queue.cat.data") );
		
		SerializableTest1 st1 = new SerializableTest1();
		
		Deque<Cat> catQueue = new LinkedList<>();
		
		// mitzi
		Cat cat = st1.new Cat(st1.new Tail(), "black");
		cat.name = "Mitzi";
		cat.tail.length = 12;
		// minka
		Cat cat2 = st1.new Cat(st1.new Tail(), "white");
		cat2.name = "Minka";
		cat2.tail.length = 7;
		
		catQueue.offer(cat);
		catQueue.offer(cat2);
		
		// serialize
		//st1.test1( catQueue, file );
		// deserialize
		st1.printCatQueue(file);
	}

	private void test1( Queue<Cat> catQueue, File file ) {
		
		
		
		try(	ObjectOutputStream out = new ObjectOutputStream(
					new BufferedOutputStream( new FileOutputStream(file) )
				);
			)
			{
			boolean catsInQueue = true;
			while (catsInQueue) {
				Cat cat = catQueue.poll();
				if (cat != null) {
					out.writeObject(cat);
				}else {
					catsInQueue = false;	
				}
			}
			//out.writeObject(cat); 		// RTE java.io.NotSerializableException
										// because tail field is not Serializable
		}catch( IOException e ) {
			e.printStackTrace();
		}
	}
	
	private void printCatQueue( File file ) {
		if (! file.exists() ) {
			throw new IllegalArgumentException("file not found!");
		}
		
		Queue<Cat> queue = new LinkedList<>();
		
		try ( ObjectInputStream in = new ObjectInputStream( 
				new BufferedInputStream(
						new FileInputStream(file)
						)
				
				);
		) {
			
			while(true) {
				Object obj = in.readObject();
				if ( obj instanceof Cat )
					queue.offer( (Cat)obj );		
			}
		}catch( EOFException e ) {
			//e.printStackTrace();
			System.out.println("file end reached");
		}catch( IOException e ) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	
		System.out.println("queue = " + queue);
		
	}
	
}
