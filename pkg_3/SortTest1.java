/**
 * 
 */
package pkg_3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class SortTest1 {

	Thing[] thingArr;
	
	class Thing implements Comparable<Thing> {
		
		private String name;
		
		Thing(){
			this.name = "undefined";
		}
		
		Thing(String name) {
			super();
			this.name = name;
		}
		
		String getName() { return name; }
		
		@Override
		public int compareTo( Thing other ) {
			return this.name.compareTo( other.getName() );
		}
		
		@Override
		public String toString() {
			return "{ "+getName()+" }";
		}
		
		@Override
		public int hashCode() {
			return this.getName().hashCode();
		}
		
		@Override
		public boolean equals( Object other ) {
			if ( other == null ) { return false; }
			// not null
			if ( ! (other instanceof Thing) )  return false;
			// its a Thing
			Thing otherThing = (Thing)other;
			if ( ! otherThing.getName().equals(this.getName()) ) return false;
			// Meaningful same
			return true;
		}
	}
	
// anonymous local inner class	
//	private Comparator<Thing> thingComp = new Comparator<Thing> {
//		public int compare( Thing one, Thing two ) {
//			return one.getName().compareTo(two.getName());
//		}
//	};
	
	// two args with type
	private Comparator<Thing> thingComp = (Thing one, Thing two) -> one.getName().compareTo(two.getName());
	
	private Comparator<Thing> thingCompRev = (Thing one, Thing two) -> two.getName().compareTo(one.getName());
	
	// two args w/o type 
	private Comparator<Thing> thingComp2 = (one, two) -> one.getName().compareTo(two.getName());
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SortTest1 st1 = new SortTest1();
		//st1.test1();
		//st1.test2();
		//st1.test3();
		st1.test4();
	}
	
	private void test1() {
		int[] ints = {2,4,7,9}, ints2 = {1,5,8};
		
		double d1 = 1.2, d2 = 1.3, d3 = 2.34;

		double[] darrs = new double[4];
		darrs[0] = d1;
		darrs[1] = d2;
		darrs[2] = d3;
		
		Arrays.sort(darrs);		
		
		System.out.println( Arrays.toString(darrs) );
		
		System.out.println( Arrays.binarySearch(darrs, 1.1) ); // -2
	}

	private void test2() {
		
		Thing[] things = { 
				new Thing("cup"), new Thing("ball"), new Thing("Waterbottle"),
				new Thing("Fork"), new Thing("Spoon"), new Thing("42"), new Thing("$$$")
				
		};
		
		Arrays.sort(things);
		
		System.out.println( Arrays.toString(things) );
		
		System.out.println( Arrays.binarySearch(things, new Thing("Albert")) );
		 // -3
		
		System.out.println( Arrays.binarySearch(things, new Thing("$$$")) );
		// 0
	}
	
	// array as List
	private void test3() {
		//List<Thing> things = new LinkedList<>();
		
		Thing[] things2 = { 
				new Thing("cup"), new Thing("ball"), new Thing("Waterbottle"),
				new Thing("Fork"), new Thing("Spoon"), new Thing("42"), new Thing("$$$")
				
		};		
		
		List<Thing> things = Arrays.asList( new Thing[] {} );
		
		System.out.println("things size: " + things.size());
		
		List<Thing> things3 = Arrays.asList(things2);
		System.out.println(things3);
		
		System.out.println("things3 size: " + things3.size());
		// things3.add(new Thing("Egon"));	// java.lang.UnsupportedOperationException
		
		Thing prev =things3.set(1, new Thing("43"));
		System.out.println("prev : " + prev);
		
		System.out.println(things3);
		
		System.out.println( (int)(new Character('A').charValue()) );
		
		System.out.println("----------");
		
		char[] chars = { '$', '0', 'A', 'a' };
		for ( char c : chars ) {
			System.out.println( c + " : " + (short)c );
		}
	}
	
	// List to array
	private void test4() {
		List<Thing> things = new LinkedList<>();
		things.add(new Thing("cup"));
		things.add(new Thing("ball"));
		things.add(new Thing("Waterbottle"));
		things.add(new Thing("Fork"));
		things.add(new Thing("Spoon"));
		things.add(new Thing("42"));
		things.add(new Thing("$$$"));
	
		// uses arg array for result if size fits
		Thing[] argArr = new Thing[ things.size() ];
		Thing[] tarr = things.toArray( argArr );
		
		System.out.println("same : " + (argArr == tarr) );
		
		// uses type of arg array but creates new arr object for result
		Thing[] argArr2 = new Thing[] {};
		Thing[] tarr2 = things.toArray( argArr2 );
		
		System.out.println("same : " + (argArr2 == tarr2) );
		
		System.out.println("----------");
		
		Arrays.sort(tarr);
		//System.out.println( tarr[0] );
		tarr[0] = new Thing("&&&");
		//System.out.println( tarr[0] );							// $$$
		System.out.println("array : " + Arrays.toString(tarr));
		
		things.add(new Thing("Lisa"));
		//things.remove(2);
		
		//System.out.println("things list at 0 : " + things.get(0)); // cup
		System.out.println("list : " + things );
	}
	
}
