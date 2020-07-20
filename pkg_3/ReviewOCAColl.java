package pkg_3;
import java.util.*;
class ReviewOCAColl {

	public static void main(String[] args){
		do1();
	}

	static void do1(){
		List<String> names = new ArrayList<String>();
		names.add("Rachel");
		names.add("Monica");
		names.add("Phoebe");

		for( String s : names ){
			System.out.println( s );
		}

		String[] friends = names.toArray( new String[ names.size() ] );

		String[] arr1 = { "gerbil", "mouse" };
		List<String> list1 = Arrays.asList( arr1 );

		// list1.add( "jerry" );	// RTE java.lang.UnsupportedOperationException
		
		p( arr1 );

	}

	
	private static void p(Object[] elems){	
		for( Object o : elems ){
			System.out.println( o );
		}
	}
}
