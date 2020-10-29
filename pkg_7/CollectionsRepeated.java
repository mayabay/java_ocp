/**
 * Repeat Collections
 */
package pkg_7;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * 
 *
 */
public class CollectionsRepeated {

	private class Person implements Comparable<Person> {
		private String name;
		private String surName;
		
		private Person() {}
		
		private Person( String name, String surname ) {
			this.name = name;
			this.surName = surname;
		}
		
		/**
		 * @return the name
		 */
		private String getName() {
			return name;
		}

		/**
		 * @return the surName
		 */
		private String getSurName() {
			return surName;
		}

		@Override
		public String toString() {
			return "[ "+name+" "+surName+"  ]";
		}
		
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if ( o instanceof Person ) {
				Person other = (Person)o;
				if (! this.name.equals(other.name) ) {
					return false;
				}else {
					return true;
				}
			}
			return false;
		}
		
		@Override
		public int hashCode() {
			return name.hashCode();
		}
		@Override
		public int compareTo(Person o) {
			return this.name.compareTo(o.name);
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CollectionsRepeated cr = new CollectionsRepeated();
		//cr.test1();	// LinkedList
		//cr.test2();
		cr.test3();
	}

	private void test3() {
		ArrayDeque<Person> ad = new ArrayDeque<Person>();
		ad.add(new Person("Peter","Parker"));
		ad.add(new Person("Steve","Rogers"));
		ad.add(new Person("Tony","Shark"));
		ad.add(new Person("Bruce","Banner"));
		System.out.println( ad.getLast() );
		
		ad.push(new Person("Rachel","Green"));
		System.out.println( ad.getLast() );
		System.out.println( ad.getFirst() );
		System.out.println( ad.peek() );
		System.out.println( ad.pop() );
		System.out.println( ad.pop() );
		//ad.add(null);	// java.lang.NullPointerException
		
	}
	
	private void test2() {
		
		HashMap<Integer,Person> personMap = new HashMap<>();
		personMap.put(null, new Person("Peter", "Parker"));
		personMap.put(null, new Person("Steve", "Rogers"));
		System.out.println(personMap);
		
		//Hashtable<Integer,Person>  personTable = new Hashtable<>(personMap);
						// java.lang.NullPointerException nnk, nnV in Hashtable
		
		TreeSet<Person> personSet = new TreeSet<Person>();
		//personSet.add(null);	// java.lang.NullPointerException
	}
	
	private void test1() {
		
		// LL double linked implementation of List and Queue interface
		LinkedList<String> ll = new LinkedList<>();
		ll.add("Josef");
		ll.add("Peter");
		ll.add(null);
		System.out.println("size : " + ll.size());
		
		ll.addFirst("Lisa");
		//System.out.println(ll.element());	// throws NoSuchElement if empty
		//System.out.println(ll.peek());		// returns null if empty
		
		System.out.println( "removed : " + ll.remove() );
		
		// for Stack: 	push() 		pop() 		peek()		// special value
		// 				add() 		element() 	remove() 	// Exception
		// 				offer() 	peek() 		poll()		// special value
	
		ll.offer("Frank");
		System.out.println("at head : " + ll.getLast());
		System.out.println("at end : " + ll.getFirst());
		
		System.out.println(ll);
		
		for( int i = 0; i < ll.size(); i++ ) {
			//System.out.println( ll.remove() ); // java.util.ConcurrentModificationException
			System.out.println( ll.get(i) );
		}
		
		
	}
	
	
}
