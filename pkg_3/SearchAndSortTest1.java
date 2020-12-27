/**
 * BS 3.5
 */
package pkg_3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

/**
 * Collections.binarySearch
 * Collections.sort
 *
 */
public class SearchAndSortTest1 {

	class Car implements Comparable<Car> {
		String model;
		int hp;
		Car(){ this("n/a",-1); }
		Car(String model, int hp) {
			this.model=model; this.hp=hp;
		}
		@Override
		public int hashCode() {
			return (31 * hp) + (model.hashCode());
		}
		@Override
		public boolean equals( Object o ) {
			if (o == null) return false;
			if ( ! (o instanceof Car) ) return false;
			Car other = (Car)o;
			if ( ! model.equals(other.model) ) {
				return false;
			}
			if ( hp != other.hp ) {
				return false;
			}
			return true;
		}
		@Override
		public String toString() {
			return "[Car model="+model+", hp="+hp+"]";
		} 
		@Override
		public int compareTo( Car other ) {
			return other.hp-hp;
		}
	}
	
	private NavigableSet<Car> cars;
	private List<Car> carsList;
	
	{
		cars = new TreeSet<Car>();
		carsList = new ArrayList<Car>();	
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SearchAndSortTest1 sast1 = new SearchAndSortTest1();
		sast1.test1();
	}

	private void buildCars() {
		Car c1 = new Car("Ferrari XY",525);
		Car c2 = new Car("Ford Mondeo",140);
		Car c3 = new Car("Porsche Carrera",375);
		Car c4 = new Car("Opel Corsa",75);
		this.fillCollection(cars, new Car[] { c1,c2,c3,c4 });
		this.fillCollection(carsList, new Car[] { c1,c2,c3,c4 });
	}
	
	private void fillCollection ( Collection<Car> carColl, Car...cars ) {
		Arrays.stream(cars)
			.forEach( carColl::add );
	}
	
	private int searchCars(List<Car> list,  Car key ) {
		return Collections.binarySearch(list, key);
	}
	
	private void test1() {
		this.buildCars();
		System.out.printf("TreeSet: %1$s %n ", cars);
		
		Collections.sort(carsList);
		System.out.println("-- list sorted ----------");
		System.out.printf("List: %1$s %n ", carsList);
		System.out.println("-- list searched ----------");
		System.out.println( "search Ferr 525 = " + this.searchCars(carsList, new Car( "Ferrari XY", 525 )) );	// 0
		System.out.println( "search Ferr 725 = " + this.searchCars(carsList, new Car( "Ferrari XY", 725 )) );	// -1
		System.out.println( "search Opel 75 = " + this.searchCars(carsList, new Car( "Opel Corsa", 75 )) );		// 3
	}
	
}
