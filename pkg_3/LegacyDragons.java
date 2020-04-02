package pkg_3;
import java.util.*;
class Dragon {}
class Unicorn {}
public class LegacyDragons {

	public static void main(String[] args){
		do1();
	}

	static void do1(){
		List dragons = new ArrayList();
		dragons.add( new Dragon() );		// 13: warning: [unchecked] unchecked call to add(E) as a member of the raw type List
		dragons.add( new Unicorn() );		// 14: unchecked call

		for( Object o : dragons ){
			//System.out.println( o );
		}

		//printDragons( dragons );			// unchecked m() invoc + conversion required List<Dragon> found List


	}

	static void printDragons( List<Dragon> dragons ){
		for( Dragon d : dragons ){
			System.out.println( d );		// CCE for Unicorn
		}
	}




}
