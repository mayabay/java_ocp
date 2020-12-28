/**
 * lambda
 */
package pkg_3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;

/**
 * 
 *
 */
public class LambdaTest1 {

	
	
	class Person{
		int height;		
		Person(){ this(4); }  
		Person(int height) {
			super();
			this.height = height;
		}

		@Override
		public String toString() {
			return "[PersonComp height = "+this.height+"]";
		}		
	}
	
	class PersonComp implements Comparable<PersonComp>{
		int height;
		PersonComp(){}
		PersonComp(int height) {
			super();
			this.height = height;
		}
		@Override
		public int compareTo( PersonComp other ) {
			return this.height - other.height;
		}
		@Override
		public String toString() {
			return "[PersonComp height = "+this.height+"]";
		}
	}
	
	Function<Integer, Long> intConverter = i -> i+1L;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LambdaTest1 lt1 = new LambdaTest1();
		lt1.test5();
	}

	private void test1() {
		HashMap<String,String> hm = new HashMap<>(); 
		hm.put(null,null);
		hm.put(null,null);
		hm.put(null,"a");
		System.out.println(hm);
		
		Set<String> set = new HashSet<>();
		set.add(null);
		set.add(null);
		set.add(null);
		System.out.println(set);
		
		Hashtable<String,String> ht = new Hashtable<>();
		ht.put(null, null);		// RTE java.lang.NullPointerException
	}
	
	private void test2() {
		
		Set<Double> doubles = new HashSet<Double>();
		doubles.add(3.4D);
		
		// Arrays.asList(1,2,3,4) 
		List<? extends Number> list = new ArrayList<>( doubles );
		list.add(null);
		//list.add(new Object());
		//List<Number> list2 = list;
		Double d = (list.get(0)).doubleValue();
		//System.out.println(d);
		
		
		List<Double> list3 = (List<Double>)list;
		
		list3.add(42.42);
	
		list3.forEach(System.out::println);
		
		
		String[] strArr = { "a", "b" };
		List<String> list4 = Arrays.asList(strArr);
		// list4.add("c"); // java.lang.UnsupportedOperationException
	}
	
	private void test3() {
		
		Comparator<Integer> comp = ( i1,i2 ) -> { return i1-i2; };
		Comparator<Integer> compRev = ( i1,i2 ) -> { return i2-i1; };		
		
		List<Integer> list = new ArrayList<Integer>( Arrays.asList(4,8,2,4,7,1) );
		System.out.println(list);
		Collections.sort(list);
		System.out.println(list);
		System.out.println( "found 8 : " + Collections.binarySearch(list, 8) );
		System.out.println( "found 3 : " + Collections.binarySearch(list, 3) );
		
		System.out.println("-- -----");
		Collections.sort(list,comp);
		System.out.println(list);
		Collections.sort(list,compRev);
		System.out.println(list);
		System.out.println( "found 8 : " + Collections.binarySearch(list, 8) );
		System.out.println( "found 3 : " + Collections.binarySearch(list, 3) );
	}
	
	private void test4() {
		List<Person> persons = new ArrayList<>();
		persons.add( new Person(6) );
		persons.add( new Person(7) );
		//Collections.sort(persons);
		Collections.sort(persons, (p1,p2) -> p1.height - p2.height );
		
		Collections.binarySearch(persons, new Person(4), (p1,p2) -> p1.height - p2.height );
	
	}
	
	private void test5() {
		TreeSet<Person> ts = new TreeSet<>();
		ts.add(new Person(3));	// java.lang.ClassCastException
		ts.add(new Person(4));
		System.out.println(ts);
	}
	
	private void test6() {
		String s = new String[] {"a","b"} [1];
		
	}
	
}
