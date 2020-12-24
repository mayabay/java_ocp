package pkg_3;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class TreeSetComparatorTest {
	

	public static void main(String[] atgs) {
		
		Comparator<Person> moneyRules = (Person a, Person b) -> b.fortune - a.fortune;

		
		Set<Person> persons = new TreeSet<>();
		persons.add( new Person("Donald Trump", LocalDate.of(1946, 6, 14), 100_000_000) );
		persons.add( new Person("Ivanka Trump", LocalDate.of(1987, 6, 14), 20_000_000) );
		persons.add( new Person("Ivanka Trump", LocalDate.of(1987, 6, 14), 20_000_000) );	// no exception, only 1 Ivanka entry
		persons.add( new Person("Immanuel Kant", LocalDate.of(1724, 4, 22), 100) );
		
		
		Person testHashCodePerson1 = new Person ("HashPerson", LocalDate.now(), 20);
		Person testHashCodePerson2 = new Person ("HashPerson", LocalDate.now(), 20);
		
		System.out.println( testHashCodePerson1.hashCode() );
		System.out.println( testHashCodePerson2.hashCode() );
		
		System.out.println( persons );
		
		// Set behält Sortierreihenfolge bei -> Comparator nicht möglich
		
		Set<Person> unfairWorld = new TreeSet<>( moneyRules);
		unfairWorld.addAll(persons);
		
		System.out.println( unfairWorld );
		
		
	}
	
	
	

}
class Person implements Comparable<Person> {
	
	String name;
	LocalDate birthDate;
	int fortune;

	
	
	public Person(String name, LocalDate birthDate, int fortune) {
		super();
		this.name = name;
		this.birthDate = birthDate;
		this.fortune = fortune;
	}
	@Override
	public String toString() {
		return name;
	}

	@Override
	public int hashCode() {
		
		return fortune * 31 + name.hashCode() + birthDate.hashCode();
	}
	
	@Override
	public boolean equals(Object other) {
		
		if(other == null) return false;
		if(other instanceof Person) {
			Person other2 = (Person)other;
			if ( this.fortune == other2.fortune && this.name.equals(other2.name ) && this.birthDate == other2.birthDate ) return true;
			
			return false;
			
		} else return false;
		
	}
	

	// Impfreihenfolge natural order

	@Override
	public int compareTo(Person person) {

		
	 if(this.birthDate.equals(person.birthDate)) {
		 return 0;
	 }else {
	 
			return	this.birthDate.isBefore(person.birthDate) ? -1 : 1;
	 		}
	}
	
}