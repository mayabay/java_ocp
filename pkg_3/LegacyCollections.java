package pkg_3;
import java.util.*;
class LegacyCollections {
	
	public static void main(String[] args){
		//go3();
		LegacyCollections lc = new LegacyCollections();
		Map<String, String> map = new LinkedHashMap<>(); 
		//Map<String, String> map = new HashMap<>();
		map.put( "Max", "Berlin" );
		map.put( "Fritz", "Mannheim" );
		map.put( "Lisa", "Stuttgart" );
		//lc.readMap(map);
		
		Hashtable<String, String> ht = new Hashtable<String, String>();
		ht.put("1", "Jan");
		ht.put("2", "Feb");
		
		
		
		Vector<String> vec = new Vector<>();
		vec.add("Monday"); vec.add("Tuesday");
		lc.test1( vec.elements() );
		lc.test1( ht.elements() );
		// Enumeration 1.0
		
		// Iterator 1.2
		
		// Collection List Iterator
		ArrayList<Integer> ints = new ArrayList<Integer>();
		Iterator<Integer> iter =  ints.iterator();
		 
		
	}

	private static void go(){
		ArrayList al = new ArrayList();
		
		// all warnings add(E e) is generic, unchecked call to add() as member of raw type
		 
		//al.add( "Peter" );
		//al.add( new Dog("clover") );
		//al.add( 42 );


		/*

		ArrayList<Integer> ints = new ArrayList( );	// warn unchecked conversion
		ints.add(3);
		ints.add(5);
		ints.add(8);
		ints.add(2);
		addToList( ints );

		for( int i : ints ){
			//System.out.println( i );	// CCE because legacy code added a String object
		}

		*/

	}

	private static void go2(){
		List list = new ArrayList();
		list.add( new Dog("aiko") );
		list.add( new Dog("clover") );
		addToList2( list );
	}

	private static void addToList( List list ){
		list.add( new String("42") );		// warn unchecked call as member of raw type
	}

	private static void addToList2( List<String> list ){
		list.add( new String("42") );		
	}

	private static void go3(){
		ArrayList list = new ArrayList();
		list.add(3);
		list.add(4);
		//list.add(new StringBuffer());		// OK but pList1 will result in CCE
	
		pList1(list);

	}

	private static void pList1( List<Integer> list ){
		for( Integer i : list ){
			System.out.println( i );

		}
	}

	private void test1( Enumeration<String> names ) {
		// only with Iterable things
//		for ( String name : names ) {
//			
//		}
		
		while( names.hasMoreElements() ) {
			System.out.println(names.nextElement());
		}
		
	}
	
	private void readMap( Map<String, String> map ) {
		
		// Max Berlin
		// Fritz Mannheim
		
		Set<Map.Entry<String, String>> set =  map.entrySet();
		
		for( Map.Entry<String, String> entry : set ) {
			System.out.printf( "'%1$-10s' '%2$20s' %n", entry.getKey(), entry.getValue() );
		}
		
	}
	
}
