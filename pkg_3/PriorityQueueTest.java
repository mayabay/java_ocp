/**
 * 
 */
package pkg_3;

import java.util.PriorityQueue;

/**
 * Learn PriorityQueue
 */
public class PriorityQueueTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PriorityQueueTest pqt = new PriorityQueueTest();
		pqt.test1();
	}

	private void test1() {
		String s1 = "Amy";
		String s2 = "Rudolf";
		String s3 = "Francis";
		
		PriorityQueue<String> pq = new PriorityQueue<String>();
		
		pq.offer(s2);	// Rudolf
		pq.offer(s3);	// Francis
		pq.offer(s1);	// Amy
		
		System.out.println(pq);
		
		System.out.println( pq.peek() );
		System.out.println("\tremove " + pq.poll() );
		
		System.out.println( pq.peek() );
		System.out.println("\tremove " + pq.poll() );
	}
	
}
