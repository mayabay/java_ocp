package pkg_3;
import java.util.*;
class LegacyCollections {
	
	public static void main(String[] args){
		go3();
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

}
