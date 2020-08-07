package pkg_3;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LearnMaps {

   public static void main(String[] args) {
	   LearnMaps lm = new LearnMaps();
	   lm.do1();
   }	
	
	private void do1() {
		Map<String, String> map = new HashMap<>();
		map.put( "Koala", "bamboo" );
		map.put( "Lion", "meat" );
		map.put( "Zebra", "grass" );
		
		
		pMap( map );
	}
	
	private void pMap( Map<?,?> map ) {
		Set<?> keySet = map.keySet();			// one of the 3 collection views
		Collection<?> values = map.values();	// values collection
		Set<Map.Entry<?, ?>> set = map.entrySet();
		Set<?> set2 = map.entrySet();
		for ( Object o : values ){
			System.out.println( o );
		}
	}
	
}
