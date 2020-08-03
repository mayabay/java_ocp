package pkg_3;
import java.util.*;
import plants.*;
/** 
 * Collection - Queue - Deque
 * LL (List and Queue) AD (Deque)
 * */
public class LearnQueues {

	private static int boxId = 0;

	// assembly line for plant boxes
	private Queue<Box<Plant>> queue = new LinkedList<>();

	// box palett = 36 boxes, shipping container 40ft = 23 paletts
	private static int containerBoxCapacity = 8; // 828

	public static void main(String[] args){
		
		LearnQueues lq = new LearnQueues();
		
		// build boxes with plants of certain type
		Box[] boxes = lq.getBoxes( containerBoxCapacity, Orange.class );
		
		// show all boxes
		pBox( boxes );

		// put boxes on assemnbly line
		int processed = lq.putOnAssemblyLine( lq.queue, boxes );	// unchecked invocation
		System.out.println( processed + " boxes have been loaded to line" );

		// work on assembly line
		int n = lq.addContent( lq.queue, Hilo.class );

		pQueue( lq.queue );

	}

	private static void pQueue( Queue<?> queue ){
		for( Object o : queue )
			System.out.println( o );

	}

	private static void pBox ( Box<?>[] boxes ){
		System.out.println( "print box content: " );
		for( Box b : boxes ){
			System.out.println("\t" +  b );
		}
	}

	/**
	 * Add additional plants to the box
	 * @param line the queue of boxes
	 * @param plantType class type of plant to add
	 * @return number of boxes processed
	 * @throws RuntimeException if plant instance could not be created
	 * */
	public int addContent( Queue<Box<Plant>> line, Class<? extends Plant> plantType ){
		int p = 0; // boxes processed
		System.out.println( "processing boxes ..." );

		for ( int i = 0; i < line.size(); i++ ){
			Box<Plant> box = line.poll();	// peek() does not remove box
			System.out.println( "\t box " + box + " processed." );
			try{
				box.add( plantType.newInstance() );
			}catch( IllegalAccessException | InstantiationException e ){
				throw new RuntimeException("plant object cannot be created");
			}
			line.offer( box );
		}
		return p;
	}

	/**
	 * Put boxes on assembly line
	 * @param line queue to add the boxes
	 * @param boxes array of boxes 
	 * @return number of boxes processed
	 * */
	public int putOnAssemblyLine( Queue<Box<Plant>> line, Box<Plant>[] boxes ){		// unchecked conversion
		// Queue<? super Box<Plant>> line // you cannot retrieve a Box<Plant> from that queue

		int boxesLoaded = 0;
		for ( int i = 0; i < boxes.length; i++ ){
			line.offer( boxes[i] );
			boxesLoaded++;
		}
		return boxesLoaded;
	}

	/**
	 * Returns an array of Boxes, each filed with Plants
	 * @param maxCount Nr of boxes to build 
	 * @param PlantType class representing the type of plant
	 * @return array of Box of requested type
	 * @throws RuntimeException if type cannot be instantiated
	 * */
    public Box<?>[] getBoxes( int maxCount, Class<? extends Plant> plantType ){
		
		//Box<?>[] boxes = new CardboardBox<Plant,Integer>[ this.queue.size() ]; // DNC 21: error: generic array creation
		Box<?>[] boxes = new CardboardBox<?,?>[ maxCount ]; 
		int idx = 0;	// start with 1st box

		while (maxCount > 0){
			CardboardBox<Plant, Integer> cb = new CardboardBox<>( ++boxId );
			try{
				cb.add(  plantType.newInstance() );
			}catch( IllegalAccessException | InstantiationException e ){
				throw new RuntimeException("plant object cannot be created");
			}
			boxes[idx] = cb;			
			System.out.println( "\t packing box "+ cb.getId() +" with " + plantType.getSimpleName() );
			idx++; 			// next slot
			maxCount--;		// 
		}

		return boxes;
	}	

}
