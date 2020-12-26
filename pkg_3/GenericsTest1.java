/**
 * BS 3
 */
package pkg_3;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/**
 *
 */
public class GenericsTest1 {


	
	/**
	 * T is species of SocialAnimal
	 * */
	interface SocialAnimal<T> {
		String says();
		//static String info( T t ) { return "Social animals like "+t+" can interact with others!"; }
		default String info( T t ) { return "Social animals like "+t+" can interact with others!"; }
	}
	
	class Animal {}
	
	class Elephant extends Animal implements SocialAnimal<Elephant> {
		String name;
		Elephant(){ this( "n/a" ); }
		Elephant( String name ){ this.name = name; }
		@Override
		public String says() { return "rooaarr"; }
		@Override
		public String toString() {
			return "[Elephant : "+name+"]";
		}
	}
	
	class AfricanElephant extends Elephant {
		AfricanElephant(){ this( "n/a" ); }
		AfricanElephant( String name ){ this.name = name; }		
		@Override
		public String says() { return "waves his big ears"; }
		@Override
		public String toString() {
			return "[AfricanElephant, name = "+name+"]";
		}		
	}
	
	/**
	 * T is Storage Type of ShippingContainer
	 * */
	interface ShippingContainer<T> {
		
		int SEE_CONTAINER_20FT = 2;
		int SEE_CONTAINER_40FT = 4;
		
		String getType();
		default String getLoad( T t ) { return "ShippingContainer loaded with " + t; }
	}	
	
	class Crate<T> implements ShippingContainer<Crate<T>> {
		T t;
		void packCrate( T t ) {
			this.t = t;
		}
		@Override
		public String getType() { return "[ShippingContainer : "+ShippingContainer.SEE_CONTAINER_40FT+"]"; }
		@Override
		public String toString() {
			return "[Crate: "+t+"]";
		}
	}
	
	class ContainerShip<T extends ShippingContainer<T>> {
		List<T> storage;
		ContainerShip(){ super(); this.storage = new LinkedList<>(); }
		void addToStorage( T t ){
			this.storage.add(t);
		}
		List<T> getStorageList(){ return Collections.unmodifiableList(storage); }
		T unload() {
			if ( storage.size() > 0 ) { return storage.remove(storage.size()-1); }
			else { return null; }
		}
		void depart() {
			storage.forEach(t -> System.out.println(t.getType() + ", " + t.toString() ) );
		}
	}
	
	// very simple animal check
	private static Predicate<? super GenericsTest1.Animal> animalExamination = Objects::nonNull;
	// test for Elephant
	private static Predicate<? super GenericsTest1.Elephant> elephantExamination = e -> e.name.length() > 4;
	// test for AfricanElephant
	private static Predicate<? super GenericsTest1.AfricanElephant> africanElephantExamination = e -> e.name.length() > 7;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GenericsTest1 gt1 = new GenericsTest1();

		// (1) pack a crate
		Crate<? extends GenericsTest1.Animal> crate1 =   gt1.test1();
		// (2) check animal health
		boolean isHealthyInCrate = gt1.checkAnimalHealthInCrate(crate1, animalExamination);
		//boolean isHealthyInCrate = gt1.checkAnimalHealthInCrate(crate1, elephantExamination);
		// (3) load crate on ship
		ContainerShip<Crate<GenericsTest1.Animal>> ship1 = gt1.new ContainerShip<Crate<GenericsTest1.Animal>>();
		if (isHealthyInCrate) {
			ship1.addToStorage((Crate<Animal>) crate1);
		}  
		// (4) depart harbor
		ship1.depart();
	
	}

	private Crate<? extends GenericsTest1.Animal> test1(){
		Crate<GenericsTest1.Elephant> crate1 = new Crate<>();
		Elephant ele1 = new Elephant("Dumbo");
		AfricanElephant ele2 = new AfricanElephant("KingOfAfrica");
		crate1.packCrate(ele2);
		return crate1;
	}
	
	private boolean checkAnimalHealthInCrate( Crate<? extends GenericsTest1.Animal> crate,
			Predicate<? super GenericsTest1.Animal> animalExamination ) {
		return animalExamination.test(crate.t);
	}
	
}
