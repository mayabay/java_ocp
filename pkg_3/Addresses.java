package pkg_3;
import java.util.*;
/** set of sorted addresses */
public class Addresses {

	private static int idSrc;
	
	private Set<Address> set;

	private Comparator<Address> byCity;

	public static void main(String[] args){
		Addresses a = new Addresses();
		a.do1();
	}

	/*
	 * if set is of raw type, no warning
	 * */
	
	// private static void pSet( Set set ){ 
	 private static void pSet( Set<? extends Address> set ){ 
	//private static void pSet( Set<?> set ){ 
		for( Object o : set ){
			System.out.println( o );

		}
	}

	private static void pColl( Collection<?> coll ){
		for( Object o : coll ){
			System.out.println( o );
		}
	}

	private static void pColl( List<Address> list, Comparator<Address> comp) {
		Collections.sort( list, comp );
		pColl( list );
	} 

	private void do1(){	

		// Address has a natural ordering by lastName 

		set = new TreeSet<>();								// 
		//Set set = new TreeSet();	 							// if set is a raw type
		set.add( new Address("Peter", "Parker", "London") );	// unchecked call
		set.add( new Address("Steve", "Rogers", "Berlin") );	// same
		set.add( new Address("Bruce", "Banner", "Paris") );		// same
		set.add( new Address("Tony", "Stark", "Melbourne") );	// same

		//pSet( set );
			// if set is raw type (1) unchecked method invocation (2) unchecked conversion


		// create Comparator 
		byCity = (Address a1, Address a2) -> { 
			return a1.getCity().compareTo( a2.getCity() );	// using natural sort order algorithm 
		};

		List<Address> list = new ArrayList<>();
		list.add( new Address("Peter", "Parker", "London") );	// unchecked call
		list.add( new Address("Steve", "Rogers", "Berlin") );	// same
		list.add( new Address("Bruce", "Banner", "Paris") );		// same
		list.add( new Address("Tony", "Stark", "Melbourne") );	// same

		// sort 
		Collections.sort( list, byCity );
		// print
		//pColl(list);


		// Comparator<? super Address>
		// <?>
		// <? extends Address>
		Comparator<Address> byId = new Comparator<Address>() {
			public int compare( Address a1, Address a2 ){
				return a1.getId() - a2.getId();		
			}
		};

		//pColl( list, byId );
	
		// sort by last name (natural sort order of Address)
		Collections.sort( list );

		// print sorted list
		pColl( list );
		
		// search for address
		int found = Collections.binarySearch( list, new Address("Bruce", "Banner", "Paris") );
		
		// show value
		System.out.println( "found Bruce Banner here = " + found );


	}

	public static Map<Integer, Address> getMapOfAddresses() {
		int initialCapacity = 12;
		float loadFactor = 0.75F; // 0.75F is default
		boolean accessOrder = true;
		
		LinkedHashMap<Integer,Address> lhm = new LinkedHashMap<>( initialCapacity, loadFactor, accessOrder  );
		
		lhm.put(++idSrc, new Address("Bruce","Banner", "München"));
		lhm.put(++idSrc, new Address("Peter","Parker", "Stockholm"));
		lhm.put(++idSrc, new Address("Steve","Rogers", "Berlin"));
		lhm.put(++idSrc, new Address("Tony","Stark", "Frankfurt"));
		lhm.put(++idSrc, null);
		
		return lhm;
	}



}



