/**
 * BS 3
 */
package pkg_3;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Learn Queue and Deque 
 *
 */
public class SimpsonsInTheQueue {

	enum Simpsons { Homer, Marge, Bart, Lisa, Maggie }
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SimpsonsInTheQueue sitq = new SimpsonsInTheQueue();
		sitq.test1();
	}

	private void test1() {
		
		Deque<SimpsonsInTheQueue.Simpsons> deque = new LinkedList<SimpsonsInTheQueue.Simpsons>();
		
		deque.offer( SimpsonsInTheQueue.Simpsons.Lisa ); 	// L
		deque.offer( SimpsonsInTheQueue.Simpsons.Bart );	// B L
		deque.offer( SimpsonsInTheQueue.Simpsons.Marge );	// Mar B L
		
		System.out.println("t: " + deque.getLast() );
		System.out.println("h: " + deque.element() );
		System.out.println("h: " + deque.getFirst() );
		System.out.println("h: removed = " + deque.remove() );
		deque.push( SimpsonsInTheQueue.Simpsons.Homer );	// Mar B H 
		System.out.println("h: pushed " + deque.peek()  );
		
		System.out.println("size = " + deque.size());
		
		deque.addFirst( SimpsonsInTheQueue.Simpsons.Maggie );	// Mar B H Mag 
		System.out.println("h: " + deque. peek()  );
	}
	
}
