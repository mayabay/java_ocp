/**
 * BS 3
 */
package pkg_3;

import java.util.Arrays;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * 
 *
 */
public class BoundsTest4 {

	class NumberBox<T extends Number> {
		private Set<T> numbers;
		{ numbers = new TreeSet<>();  }
		NumberBox(){}
		NumberBox( T...t   ){ this.numbers.addAll(Arrays.asList(t)); }
		void printPositionInfo( Number t ) {
			System.out.println("analyze number box (of type="+numbers.getClass().getTypeName()+") = " + numbers);
			if ( numbers.size() < 4 ) { System.out.println("Minimum 4 numbers needed."); return; }
			NavigableSet<T> ns = (NavigableSet<T>)this.numbers;
			System.out.println( "floor("+t+") = " + ns.floor( (T) t ) );
			System.out.println( "ceiling("+t+") = " + ns.ceiling( (T) t ) );
			System.out.println( "lower("+t+") = " + ns.lower( (T) t ) );
			System.out.println( "higher("+t+") = " + ns.higher( (T) t ) );
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BoundsTest4 bt4 = new BoundsTest4();
		bt4.test1();

	}

	private void test1() {
		NumberBox<Double> box1 = new NumberBox<Double>( 3.4,8.95,1.25,4D,13.75 );
		NumberBox<Long> box2 = new NumberBox<Long>( 34L,78L,1L,890L,1973L,1_234_567L );
		NumberBox<Integer> box3 = new NumberBox<Integer>( 23,127,-128,56,0,1235 );
		this.testBoxes( 7 , new NumberBox[] { box1, box2, box3 });
	}
	
	private void testBoxes( Number number, NumberBox<? extends Number>...boxes ) {
		for( NumberBox<? extends Number> box : boxes ) {
			box.<Number>printPositionInfo( number );
		}
	}
}
