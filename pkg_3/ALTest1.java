package pkg_3;
import java.util.*;
class ALTest1 {
	

	public static void main(String[] args){
		do1();
	}

	private static void do1(){
		List<String> strings = new ArrayList<>();
	

		DVDInfo info1 = new DVDInfo( "2001", "scifi" );
		DVDInfo info2 = new DVDInfo( "Aliens", "scifi" );
		DVDInfo info3 = new DVDInfo( "Raiders of the lost arc", "action" );
		DVDInfo info4 = new DVDInfo( "Lost in Translation", "comedy" );

		List<DVDInfo> list = new ArrayList<DVDInfo>();
		
		list.add(info2);
		list.add(info1);
		list.add(info3);
		list.add(info4);

		//Collections.sort( list );
		
		// Collections.<DVDInfo>sort(	specifiy the generic type
		// what is type argument for the Comparator? compiler infers it from list
		Collections.sort( list, (t, u) -> {
			return t.getGenre().compareTo( u.getGenre() );
		} );

		System.out.println( list );


		

	}

}
