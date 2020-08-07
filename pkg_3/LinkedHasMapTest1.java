package pkg_3;
import java.util.*;

/**
 * Learn LinkedHAshMap
 * */
public class LinkedHasMapTest1 {

	private static int idSrc = 0;
	
	public static void main(String[] args) {
		do1();
	}

	private static void do1() {
		
		int initialCapacity = 12;
		float loadFactor = 0.75F; // 0.75F is default
		boolean accessOrder = true;
		
		LinkedHashMap<Integer,Address> lhm = new LinkedHashMap<>( initialCapacity, loadFactor, accessOrder  );
		
		lhm.put(++idSrc, new Address("Peter","Parker", "Stockholm"));
		lhm.put(++idSrc, new Address("Steve","Rogers", "Berlin"));
		lhm.put(++idSrc, new Address("Tony","Stark", "Frankfurt"));
		lhm.put(++idSrc, new Address("Bruce","Banner", "München"));
		
		//Collection coll = lhm.values();
		
		// Set<Integer> keySet = lhm.keySet();
		
		//pSet( lhm.keySet() );
		pColl( lhm.values() );
		
		// accessOrder = true
		// 1 2 3 4
		
		
		// print Bruce
		System.out.println( "---" );
		System.out.println( lhm.get(2) );
		System.out.println( "---" );
		
		//pSet( lhm.keySet() );
		pColl( lhm.values() );
		
	}
	
	private static void pSet( Set<?> set ){
		Iterator<?> iter = set.iterator();
		while( iter.hasNext() ) {
			System.out.println( iter.next() );
		}		
		
	}
	
	private static void pColl( Collection<?> coll ) {
		Iterator<?> iter = coll.iterator();
		while( iter.hasNext() ) {
			System.out.println( iter.next() );
		}
	}
	
}
