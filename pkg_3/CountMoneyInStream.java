/**
 * BS 4
 */
package pkg_3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

/**
 * flatMap
 *
 */
public class CountMoneyInStream {

	List<Person> persons;
	
	class Person {
		double money;
		Person() {}
		Person(double money) {
			super();
			this.money = money;
		}
		/**
		 * @return the money
		 */
		double getMoney() {
			return money;
		}
		@Override
		public String toString() {
			return "Person [money=" + money + "]";
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CountMoneyInStream cmis = new CountMoneyInStream();
		//cmis.test1();
		cmis.test2();
	}

	private void test1() {
		persons = new ArrayList<>( Arrays.asList(
				new Person(12.50),new Person(6.50),new Person(10)
				));
		
		double sum = persons.stream()
			.flatMapToDouble(p -> {
				return DoubleStream.of(p.money);
			})
			.peek( System.out::println )
			.sum();								// Terminating
		System.out.println("sum = " + sum);
		
	}
	
	private void test2() {
		persons = new ArrayList<>( Arrays.asList(
				new Person(12.50),new Person(6.50),new Person(10)
				));
		
		double sum = persons.stream()
			.mapToDouble( Person::getMoney )
			.peek( System.out::println )
			//sum();								// Terminating
			//.reduce(0.0D, (d1,d2) -> d1+d2 );		// with lambda
			.reduce(0.0D, Double::sum );			// with method ref
		System.out.println("sum = " + sum);
		
	}	
	
	private void test3() {
//		persons = new ArrayList<>( Arrays.asList(
//				new Person(12.50),new Person(6.50),new Person(10)
//				));	
		
		List<Person> filtered =
		Stream.of( new Person(12.50),new Person(6.50),new Person(10) )
			.parallel()
			.filter( p -> p.getMoney() >= 10D )
			//.collect( ArrayList::new, ArrayList::add, ArrayList::addAll );
			.collect( ArrayList::new, List::add, List::addAll );
				// (  )
		
				// t1 al1
				// t2 al2
				// t3 al3
				// al1 al2 -> altemp + al3 = altemp2  + al4
		
				// ( Collector )
				//		Collectors.
		
	}
	
}
