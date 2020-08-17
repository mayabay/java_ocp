package pkg_3;
import java.util.*;
class ArrayAnd {

	public static void main(String[] args){

		go();
	}

	private static void go(){
		
		String[] sa = { "A","B","C","D" };
		
		List<String> list = Arrays.asList(sa);

		sa[1] = "B1";

		String[] sa2 = list.toArray( new String[ list.size() ] );

		sa2[1] = "B2";

		for( String s : list ){
			System.out.println( s );
		}
	


	}
}
