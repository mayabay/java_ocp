/**
 * BS 3
 */
package pkg_3;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.NavigableSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeSet;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

/**
 * 
 *
 */
public class BoundsTest5 {
	
	// determines order of vaccination
	Comparator<Person> comparePersonByAge = (p1,p2) -> p2.birthDate.compareTo(p2.birthDate);
	// returns randomly true or false 
	BooleanSupplier randomBoolSupp = () -> { 
		return Math.random() > 0.5D ? true : false;
	};
	// random pain supplier
	Supplier<String> auaSuppl = () -> randomBoolSupp.getAsBoolean() == true ? "Aua!" : "";
	
	class Person implements Comparable<Person> {
		String name;
		LocalDate birthDate;
		LocalDateTime vaccinatedDateTime;
		Person(){}
		Person( String name, LocalDate birthDate ){
			this.name = name; this.birthDate = birthDate;
		}
		@Override
		public int compareTo( Person other ) {
			return name.compareTo(other.name);
		}
		@Override
		public String toString() {
			return "[Person: "+name+", born "+ birthDate.format( DateTimeFormatter.ISO_DATE ) +", "
					+ "vacc. "+vaccinatedDateTime.format(DateTimeFormatter.ISO_DATE_TIME)+" ]";
		}
	}
	
	class QueueContainer<T extends Comparable<T>> {
		// explicit package level
		Queue<T> q = new PriorityQueue<>(); //new ArrayDeque<>();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BoundsTest5 bt5 = new BoundsTest5();
		//bt5.test1();
		bt5.test2();
	}

	private void test1() {
		QueueContainer<String> qt = new QueueContainer<String>();
		qt.q.add("Frank");
		qt.q.add("Xaver");
		qt.q.add("Lisa");
		qt.q.add("Rachel");	// Frank Lisa Rachel Xaver
		System.out.println("-- ----------");
		System.out.println( qt.q );
		System.out.println("removed = " + qt.q.remove());
		System.out.println("removed = " + qt.q.remove());
		System.out.println("removed = " + qt.q.remove());
		System.out.println("removed = " + qt.q.remove());
		System.out.println("-- ----------");
		System.out.println( qt.q );
		
		
		/*
			-- ----------
			[Frank, Rachel, Lisa, Xaver]
			removed = Frank
			removed = Lisa
			removed = Rachel
			removed = Xaver
			-- ----------
			[]		  
		 */
		
	}
	
	private void test2() {
		Person p1 = new Person("Frank", LocalDate.of(1942, 7, 23));
		Person p2 = new Person("Xaver", LocalDate.of(1971, 2, 13));
		Person p3 = new Person("Lisa", LocalDate.of(1987, 5, 2));
		Person p4 = new Person("Rachel", LocalDate.of(1982, 10, 4));
		List<Person> vaccinated = this.vaccinatePersons(p1,p2,p3,p4);
		System.out.println("-- results ----------");
		for ( Object o : vaccinated ) {		// works with List<?>
			System.out.println(o);
		}
		//this.test3(new TreeSet<Person>(vaccinated), p2);	// What is ordering here?
	}
	
	private List<Person> vaccinatePersons( Person...persons ) {
		System.out.println("-- persons arrived ----------");
		// List of vaccinated persons
		List<Person> vaccinated = new ArrayList<BoundsTest5.Person>();
		
		// queue of persons to vaccinate, oldest first
			//Queue<Person> q = new PriorityQueue<BoundsTest5.Person>( comparePersonByAge );
		
		//long personCount = Arrays.stream(persons)
		Queue<Person> q = Arrays.stream(persons)	
				.peek( p -> System.out.printf("'%1$12s' %2$tF %n", p.name, p.birthDate) )
				//.peek( p -> q.add(p) )
				.collect( () -> { return new PriorityQueue<BoundsTest5.Person>( comparePersonByAge ); } , Queue::add, Queue::addAll  );
		//System.out.println("number of persons = " + personCount);
		System.out.println("-- vaccinate ----------");
		// vaccinate people
		q.stream()
			.peek( p -> {
				System.err.println(" .. now vaccinate " + p.name);
				//Thread current = Thread.currentThread();
				try {
					Thread.sleep(1000);
				}catch( InterruptedException e ) {
					e.printStackTrace();
				}
			} )
			.map( p -> { 
				String s = auaSuppl.get();
				if ( !s.equals("") ) { System.err.println("\t" + s); }
				p.vaccinatedDateTime = LocalDateTime.now();
				return p; } )
			.forEach( p -> { vaccinated.add(p); } );
		
		return vaccinated;
	}
	
	private void test3( NavigableSet<Person> set, Person p ) {
		System.out.println("-- position test for "+p.name+" ----------");
		System.out.println( "floor() = " + set.floor(p) );
		System.out.println( "lower() = " + set.lower(p) );
		System.out.println( "higher() = " + set.higher(p) );
		System.out.println( "ceiling() = " + set.ceiling(p) );
	}
	
}
