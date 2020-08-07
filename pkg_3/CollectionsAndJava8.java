package pkg_3;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * New methods for Java 8
 * 
 * */
public class CollectionsAndJava8 {

	public static void main(String[] args) {
		 do1();

	}

	private static void do1() {
		//List<Integer> ints = Arrays.asList(2,3,4,5,6);	// ints is immutable
		
		Queue<Integer> ints = new LinkedList();
		
		ints.add(2);
		ints.offer(4);
		ints.add(6);
		ints.add(7);
		ints.add(8);
		ints.add(9);
		

		System.out.println( ints.peek() );	// 2
		ints.remove();
		System.out.println( ints.peek() ); // 4
		
		boolean success = ints.removeIf( t -> t.intValue() >=8 );		
		
		System.out.println( ints );
		
		List<Integer> intList = (List<Integer>)ints;
		
		intList.replaceAll( (Integer t) -> t.intValue() * 2 );
		
		//System.out.println( intList );
		
		intList.forEach(System.out::println);
		
		
	}
	
	private static void do2() {
		Map<Integer, Address> map = Addresses.getMapOfAddresses();
		
	} 
	
}
