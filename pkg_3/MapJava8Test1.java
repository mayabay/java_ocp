/**
 * 
 */
package pkg_3;

import java.time.LocalDate;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author andreas
 *
 */
public class MapJava8Test1 {

	long instanceId;
	
	Map<Long,Person> persons;
	
	static long id;	
	
	
	class Person implements Comparable<Person> {
		String firstName;
		String lastName;
		LocalDate birthDate;
		double height;
		
		Person(String firstName, String lastName, LocalDate birthDate, double height) {
			super();
			this.firstName = firstName;
			this.lastName = lastName;
			this.birthDate = birthDate;
			this.height = height;
		}
		@Override
		public int compareTo(Person o) {
			return this.birthDate.compareTo(o.birthDate);
		}
		@Override
		public String toString() {
			return "Person [firstName=" + firstName + ", lastName=" + lastName + ", birthDate=" + birthDate
					+ ", height=" + height + "]";
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getEnclosingInstance().hashCode();
			result = prime * result + ((birthDate == null) ? 0 : birthDate.hashCode());
			result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;		// same object
			if (obj == null)
				return false;		// null
			if (getClass() != obj.getClass())
				return false;		// wrong type
			Person other = (Person) obj;
			if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
				return false;		// because of inner type
			if (birthDate == null) {
				if (other.birthDate != null)
					return false;
			} else if (!birthDate.equals(other.birthDate))
				return false;
			if (lastName == null) {
				if (other.lastName != null)
					return false;
			} else if (!lastName.equals(other.lastName))
				return false;
			return true;
		}
		private MapJava8Test1 getEnclosingInstance() {
			return MapJava8Test1.this;
		}
		
		
	}
	
	MapJava8Test1(){
		instanceId++;
		persons =  new TreeMap<>();
		persons.put( Long.valueOf(++id) , new Person("Frank", "Miller", LocalDate.of(1971,4,12),5.2 ) );
		persons.put( Long.valueOf(++id) , new Person("Tom", "Berengar", LocalDate.of(1953,7,19),7.2 ) );
		persons.put( Long.valueOf(++id) , new Person("Linda", "Unger", LocalDate.of(1981,9,1),6.1 ) );
		persons.put( Long.valueOf(++id) , new Person("Frieda", "Dori", LocalDate.of(1995,12,6),5.8 ) );
		persons.put( Long.valueOf(++id) , new Person("Max", null, LocalDate.of(1941,2,8),5.4 ) );
		
		persons.put( Long.valueOf(99L) , null );
		
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (instanceId ^ (instanceId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MapJava8Test1 other = (MapJava8Test1) obj;
		if (instanceId != other.instanceId)
			return false;
		return true;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MapJava8Test1 mjt1 = new MapJava8Test1();
		mjt1.test1();
		//mjt1.test2();
		//mjt1.test3();

	}

	private void printMap( Map<?,?> map ) {
		Set<?> set = map.entrySet();	// Set<Map.Entry<?,?>> set type mismath
		for( Object o : set ){
			Map.Entry<?, ?> me = (Map.Entry<?, ?>)o;
			System.out.printf("%1$4s - %2$20s %n", me.getKey(), me.getValue());
		}
	}
	
	private void test1() {
		System.out.println("-- current persons ----------");
		this.printMap(persons);
		
		this.persons.merge(5L,		// 5L updates Max and 6L adds given Person
				new Person("Max", "Mustermann", LocalDate.of(1941,2,8),5.4 ),
				(p1,p2) -> { 
					if ( p1 == null ) { return null; }
					if ( p1.lastName == null ) { return p2; }
					return null;
				} );
		System.out.println("-- current persons after Merge -----");
		
		this.printMap(persons);
	}
	
	private void test2() {
		System.out.println("-- current persons ----------");
		this.printMap(persons);
		this.persons.computeIfPresent(2L, (l,p) -> {
			if ( p.height > 7 ) {		// 8 returns null, which will remove Person
				return new Person("Tom", "Berengar", LocalDate.of(1953,7,19),7.0 );
			}
			return null;
		} );
		System.out.println("key 2 adjusted");
		this.printMap(persons);
	}
	
	private void test3() {
		System.out.println("-- current persons ----------");
		this.printMap(persons);		
		this.persons.computeIfAbsent(99L, (p) -> { 		// 8L adds John also with 99L because entry is null	
			return new Person("John", "Wayne", LocalDate.of(1921,2,8),7.1 ); 
			// return null; 
			} );
		
		System.out.println("key 8 absent");
		this.printMap(persons);
	}
	
}
