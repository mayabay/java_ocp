package pkg_3;
import java.util.*;
import java.util.function.*;
/**
 * Learn Java8 API for the Map interface
 * */
public class MapJava8Methods {

	private static BiFunction<Address, Address, Address> mapper = (Address t, Address u) -> t.getCity().length() > u.getCity().length() ? t : u;
	
	public static void main(String[] args) {
		do1();
	}

	private static void do1() {
		
		Map<Integer, Address> map = Addresses.getMapOfAddresses();
		
		System.out.println( map );
		
		map.putIfAbsent(5, new Address( "Carlo", "Kirk", "Adelaide" ));
		
		System.out.println( map );
		
		// replace home of Kirk, if Los Angeles is longer than Adelaide
		map.merge( 5, new Address("Carlo", "Kirk", "Los Angeles") , mapper );
		
		// 6 has null value
		map.merge( 6, new Address("Sigfried", "Dragonslayer", "Moria") , mapper );
		
		// 7 does not exist
		map.merge( 7, new Address("Clara", "Fire", "Frankfurt") , mapper );
		
		System.out.println( map );
		
		setCityTo( map, mapper, "Los Angeles" );
		
		System.out.println( map );
	}
	
	/*
	 * change any map entry into a new value
	 * https://www.baeldung.com/java-concurrentmodificationexception
	 * */
	private static void setCityTo( Map<Integer, Address> map, BiFunction<Address, Address, Address> mapper, String City ) {
		Set<Integer> keySet = map.keySet();
		Iterator<Integer> iter = keySet.iterator();
		
		// list of keys
		List<Integer> list = new ArrayList<>();
		
		while( iter.hasNext() ) {
			Integer i = iter.next();
			list.add(i);
			//map.merge(i, new Address( "John", "Doe", City ), mapper);	// RTE Exception in thread "main" java.util.ConcurrentModificationException
		}
		
		for( Integer i : list ) {
			map.merge(i, new Address( "John", "Doe", City ), mapper);
		}
	}
	
}
