package pkg_3;
import java.util.*;
class ArrayDequeTest1{

	public static void main(String[] args){
		go1();
	}

	private static void go1(){
		int[] iar = { 10,9,8,7,6,5 };

		ArrayDeque<Integer> a = new ArrayDeque<>(2);
		ArrayDeque<Integer> b = new ArrayDeque<>(2);
		ArrayDeque<Integer> c = new ArrayDeque<>(2);
		ArrayDeque<Integer> d = new ArrayDeque<>(2);
		ArrayDeque<Integer> e = new ArrayDeque<>(2);

		for( Integer n : iar ){
			a.offer( n );
			b.offerFirst( n );
			c.push( n );
			d.add( n );
			e.addFirst( n );
		}

		System.out.println( "a: " + a );
		System.out.println( "b: " + b );
		System.out.println( "c: " + c );
		System.out.println( "d: " + d );
		System.out.println( "e: " + e );

		System.out.println( "e peek: " + e.peek() );	// 5 at head

		System.out.println( "e poll: " + e.poll() );	// remove 5	

		System.out.println( "e pop: " + e.pop() );		// remove 6

		System.out.println( "e pollLast: " + e.pollLast() );		// remove 10

		System.out.println( "e removeLast: " + e.removeLast() );		// remove 9

		System.out.println( "e removeLast: " + e.removeLast() );		// remove 8
		System.out.println( "e removeLast: " + e.removeLast() );		// remove 7

		System.out.println( "view of e: " + e );

		//System.out.println( e.pop() );	// RTE  java.util.NoSuchElementException
		
		//System.out.println( e.remove() );	// RTE same	

		System.out.println( e.poll() );		// null



	}

}
