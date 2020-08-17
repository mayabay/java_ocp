package pkg_3;
import java.util.*;
class BackedCollection {


	private static TreeMap<String, String> map = new TreeMap<>();

	private static SortedMap<String,String> subMap = null; 

	public static void main(String[] args){
		go();
	}

	private static void go(){
		
		map.put( "a","ant" );
		map.put( "z","zebra" );
		map.put( "f","fly" );
		map.put( "s","snake" );
		map.put( "w","whale" );
		map.put( "t","tiger" );
		map.put( "d","dog" );
		map.put( "h","horse" );

		SortedMap<String,String> subMap = map.subMap( "b","g" );
		System.out.println("aubMap: " + subMap );

		subMap.put( "f","f-thing" );	// goes into subMap
		//subMap.put( "x","xaver" );		// RTE java.lang.IllegalArgumentException: key out of range	
		map.put( "e","elster" );		// also in background map
		//System.out.println( subMap );


		// headMap J5 	SortedMap

		SortedMap<String, String> headMap = map.headMap( "c" );
		// head position: element with highest order value
		System.out.println( headMap );	// a=ant

		SortedMap<String, String> tailMap = map.tailMap( "w" );	
		System.out.println( tailMap );	// w, z
		

		// headMap J6	NavigableMap

		NavigableMap<String, String> tailMapNav = map.tailMap( "w", false );	
		System.out.println( tailMapNav );	// z

		// subMap J6	Navi
		NavigableMap<String, String> subMapNav = map.subMap( "a", false, "d", true );
		System.out.println( subMapNav );	// d


		// pollFirstEntry 
		System.out.println( map );
 		// {a=ant, d=dog, e=elster, f=f-thing, h=horse, s=snake, t=tiger, w=whale, z=zebra}
		NavigableMap<String, String> navi = map.tailMap( "s", true );
		// s=snake, t=tiger, w=whale, z=zebra
		Map.Entry<String, String> me = navi.pollFirstEntry();	// removes snake from navi
		System.out.println( me );	// s=snake

		System.out.println( navi.pollFirstEntry() );			// first is tiger now

		





	}
}
