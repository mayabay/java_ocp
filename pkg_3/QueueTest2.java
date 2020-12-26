package pkg_3;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.NavigableSet;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

public class QueueTest2 {
	
	class NumberBox<T extends Number> {
		private Set<Number> numbers;
		{ numbers = new TreeSet<>();  }
		NumberBox(){}
		NumberBox( Number...numbers   ){ this.numbers.addAll(Arrays.asList(numbers)); }
		void printPositionInfo( Number number ) {
			System.out.println("analyze number box = " + numbers);
			if ( numbers.size() < 4 ) { System.out.println("Minimum 4 numbers needed."); return; }
			NavigableSet<Number> ns = (NavigableSet<Number>)this.numbers;
			System.out.println( "floor("+number+") = " + ns.floor(number) );
			System.out.println( "ceiling("+number+") = " + ns.ceiling(number) );
			System.out.println( "lower("+number+") = " + ns.lower(number) );
			System.out.println( "higher("+number+") = " + ns.higher(number) );
		}
	}
	
	public static void main(String[] args) {
		
		QueueTest2 qt2 = new QueueTest2();
		qt2.test2();
		System.out.println("----------");
		
		Queue<Integer> intQueue = new LinkedList<>();
		
		
		// offer poll peek
		System.out.println( intQueue.offer(1) );
		System.out.println( intQueue.offer(2) );
		System.out.println( intQueue.offer(3) );
		
		System.out.println( intQueue.peek() );
		System.out.println( intQueue.peek() );
		System.out.println( intQueue.poll() );
		System.out.println( intQueue.poll() );
		System.out.println( intQueue.poll() );
		
		
		System.out.println( intQueue.peek() );	// null if no more elements
		System.out.println( intQueue.poll() );	// null if no more elements
		
		System.out.println("\n");
		
		
		// add remove element
		Queue<Integer> intQueue2 = new LinkedList<>();

		System.out.println( intQueue2.add(1) );
		System.out.println( intQueue2.add(2) );
		System.out.println( intQueue2.add(3) );
		
		System.out.println( intQueue2.element() );
		System.out.println( intQueue2.element() );
		System.out.println( intQueue2.remove() );
		System.out.println( intQueue2.remove() );
		System.out.println( intQueue2.remove() );
	
	//	System.out.println( intQueue2.element() ); // throws Exception if no more elements
	//	System.out.println( intQueue2.remove() );  // throws Exception if no more elements
		
		
		System.out.println("\n");
		
		// front back loading ArrayDeque / doubleEnded Queue
		System.out.println("ArrayDeque - nicht Queue");
		
	//	Queue<Integer> intQueue3 = new ArrayDeque<>();		***push() und pop() gehen nicht mit Queue, nur mit ArrayDeque
		ArrayDeque<Integer> intQueue3 = new ArrayDeque<>();
		
		System.out.println( intQueue3.offer(1) );			// 1 ->
		
		System.out.println( intQueue3.add(2) );				// 2, 1 ->
		System.out.println( intQueue3.offer(20) );			// 20, 2, 1 ->
		
		/* void !!! */ 		intQueue3.push(3) ;				// 20, 2, 1, 3 ->
		/* void !!! */ 		intQueue3.push(4) ;				// 20, 2, 1, 3, 4 ->
		System.out.println(	intQueue3.poll() ) ;			// 4
		System.out.println(	intQueue3.pop() ) ;				// 3
		System.out.println(	intQueue3.pop() ) ;				// 1
		System.out.println(	intQueue3.pop() ) ;				// 2
		
		System.out.println(	intQueue3.peek() ) ;			// 20
		System.out.println(	intQueue3.pop() ) ;				// 20
//		System.out.println(	intQueue3.pop() ) ;				// Exception
		

		
		
	}

	
	private void test2() {
		NumberBox<Number> nb = new NumberBox<Number>( 2,5,8,12,19 );
		nb.printPositionInfo(8);
	}
}