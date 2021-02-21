/**
 * java.util.concurrent.locks
 */
package pkg_7;

import java.time.Instant;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import pkg_7.ThreadTest1.Event;

/**
 * Example for ReentrantReadWriteLock
 *
 */
public class ReentrantLocks {

	private class Event {
		Instant instant;
		String message;
		Event(Instant instant, String message) {
			super();
			this.instant = instant;
			this.message = Thread.currentThread().getName() + " : " + message;
			//System.out.println(this);
		}
		Event(String str){
			this(Instant.now(),str);
		}
		@Override
		public String toString() {
			return "[Event: "+instant+" : "+message+"]";
		}
	}	
	
	private Queue<ReentrantLocks.Event> log = new ConcurrentLinkedQueue<>(); 
	
	private static final int WAREHOUSE_MAX_CAPACITY = 200;
	
	private ReadWriteLock rwLock = new ReentrantReadWriteLock();

	private class Wood{
	}	
	
	private class Warehouse{
		
		private Wood[] woodStorage = new Wood[WAREHOUSE_MAX_CAPACITY];
	
		IntPredicate predPositionEmpty = i -> woodStorage[i] == null;
		IntPredicate predPositionFull = i -> woodStorage[i] != null;
		
		private List<Integer> getPositionsByPredicate( IntPredicate pred ) {
			List<Integer> freePositions =
			IntStream.range( 0, WAREHOUSE_MAX_CAPACITY )
			.filter(pred)
			.mapToObj( Integer::new )			// i -> Integer.valueOf(i)
			.collect( Collectors.toList() );
			return freePositions;			
		}
		
		private List<Integer> getFreePositions() {
			List<Integer> freePositions =
			IntStream.range( 0, WAREHOUSE_MAX_CAPACITY )
			.filter(i -> woodStorage[i] == null)
			.mapToObj( Integer::new )			// i -> Integer.valueOf(i)
			.collect( Collectors.toList() );
			return freePositions;
		}
		
		/** returns number of succesfully added Wood items */
		public int addWood( Wood[] wood ) {
			
			Lock rw = rwLock.writeLock();
			
			try {
				List<Integer> freePositions = this.getFreePositions();	// getPositionsByPredicate(predPositionEmpty)
				
				int storedItems = 0;
				int itemsToStore = wood.length;
				for( Integer i : freePositions ) {
					if ( itemsToStore < 0 ) { break; }
					woodStorage[i] = wood[--itemsToStore];
					storedItems++;
				}
				log.offer(new Event("stored Items: " + storedItems));
				return storedItems;
			}finally {
				rw.unlock();
			}
		}
		
		/** returns free wood space count */
		public int getFreeSpace() {
			Lock r = rwLock.readLock();
			
			try {
				return (this.getFreePositions()).size();
			}finally {
				r.unlock();
			}
		}
		
		/** Returns count of returned items */
		public Wood[] getWood( int count ) {
			
			Wood[] toReturn = new Wood[count];
		
			Lock rw = rwLock.writeLock();
			
			try {
				List<Integer> fullPositions = this.getPositionsByPredicate(predPositionFull);
				
				int returnedItems = 0;
				for( Integer i : fullPositions ) {
					if ( count <= 0 ) { break; }
					toReturn[returnedItems++] = woodStorage[i];
					count--;
				}
				log.offer(new Event("returned Items: " + returnedItems));				
				
				return toReturn;
				
			}finally {
				rw.unlock();
			}
		}
		
	}
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	
}
