/**
 * BS 3, KB 6
 */
package pkg_3;

import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * SortedSet
 *
 */
public class SetTest2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SetTest2 st2 = new  SetTest2();
		//st2.test1();
		st2.test2();
	}

	private void test1() {
		// (1)
		Set<Integer> set = new TreeSet<>();
		set.add(1); set.add(4); set.add(11); set.add(5); set.add(7); 
		System.out.println(set);

		 SortedSet<Integer> soSet = (SortedSet<Integer>)set;
		 System.out.println("first = " + soSet.first());
		 System.out.println("last = " + soSet.last());
		
		 Set<Integer> headSet = soSet.headSet(5);
		 System.out.println("headSet (5) = " + headSet);

		 Set<Integer> tailSet = soSet.tailSet(5);
		 System.out.println("tailSet (5) = " + tailSet);		 
		 
		System.out.println("----");
		
		// 2
		Set<String> set2 = new TreeSet<>();
		set2.add("1"); set2.add("4"); set2.add("11"); set2.add("5"); set2.add("7"); 
		System.out.println(set2);		
		
	}
	
	private void test2() {
		Set<Integer> set = new TreeSet<>();
		set.add(1); set.add(4); set.add(11); set.add(5); set.add(7); 
		System.out.println(set);
		
		NavigableSet<Integer> ns = (NavigableSet<Integer>)set;
		System.out.println("higher(3) = " + ns.higher(3));
		System.out.println("lower(8) = " + ns.lower(8));

		System.out.println("ceiling(4) = " + ns.ceiling(4));
		System.out.println("floor(12) = " + ns.floor(12));		
		
	}
	
}
