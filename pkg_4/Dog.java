package pkg_4;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * OCP KB 8
 * 
 * */
public class Dog implements Comparable<Dog> {

	private static int staticVal;
	private int instVal;
	
	// Predicate testing dogs age
	private static DogQuerier isOlderThan9yrs = d -> d.getAge() >= 9;
	
	private static DogQuerier effi = d -> {
		
		staticVal++;	// captured from enclosing scope
		//instVal++;	// impossible
		d.instVal++;
		
		return true;
	};
	
	private static Predicate<Dog> nameStartsWithCharC = d -> d.getName().startsWith("c");
	private static Predicate<Dog> nameLengthSmaller = d -> d.getName().length() < 5 ;
	
	
	static {
		staticVal++;
	}
	
	{
		instVal++;
	}
	
	private String name;
	private int age;
	private int weight;
	
	public Dog(String name, int weight, int age) {
		super();
		this.name = name;
		this.age = age;
		this.weight = weight;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public int getWeight() {
		return weight;
	}

	public String toString() {
		return "[ name="+this.name+" ]";
	}

	public String bark() {
		return "woof, woof";
	}

	public int compareTo( Dog other ) {
		return name.compareTo(other.getName());
	}
	
	// -------------------------------------------------------------------------
	
	public static void main(String[] args) {
		//searchDogs();
	}
	

	private static void testFilter() {
		Dog boi = new Dog("boi", 30, 6);
		Dog clover = new Dog("clover", 35, 12);
		Dog aiko = new Dog("aiko", 50, 10);
		Dog zooey = new Dog("zooey", 45, 8);
		Dog charis = new Dog("charus", 120, 7);
		Dog cici = new Dog("cici", 12, 2);
		
		Set<Dog> dogs = new HashSet<>();
		dogs.add(boi); dogs.add(clover); dogs.add(aiko);
		dogs.add(zooey); dogs.add(charis); dogs.add(cici);
		
		//Set<Dog> r = filterDogs( dogs );
		//System.out.println( r );
		
		printDogIf( dogs, d -> true );
		System.out.println("---");
		printDogIf( dogs, d -> d.age < 9 );
		System.out.println("---");
		printDogIf( dogs, d -> d.age <= 9 );
		System.out.println("---");
		dogs.removeIf(nameStartsWithCharC.and(nameLengthSmaller)  );
		printDogIf( dogs, d -> true );		
	}
	

	private static List<Dog> getDogs(){
		Dog boi = new Dog("boi", 30, 6);
		Dog clover = new Dog("clover", 35, 12);
		Dog aiko = new Dog("aiko", 50, 10);
		Dog zooey = new Dog("zooey", 45, 8);
		Dog charis = new Dog("charis", 120, 7);		
		List<Dog> dogs = new ArrayList<>();
		
		dogs.add(boi); dogs.add(clover); dogs.add(aiko);
		dogs.add(zooey); dogs.add(charis); 
		
		return dogs;
	}
	
	private static void searchDogs() {
		
		boolean b1 =
		getDogs().stream()
			.anyMatch(d -> d.name.startsWith("c"));
		
		System.out.println("b1 : " + b1);	
		
		Optional<Dog> opt1 =
				getDogs().stream()
					.filter(d -> d.weight > 50)
					.findFirst();
		opt1.ifPresent(System.out::println);
		
	}
	
	private static void printDogIf( Set<Dog> dogs, Predicate<Dog> pred ) {
		dogs.forEach(
				(Dog dog) -> { if( pred.test(dog) ) { System.out.println(dog); } }
				);
	}
	
	private static Set<Dog> filterDogs( Set<Dog> dogs ) {
		Set<Dog> r = new HashSet( );
		r.addAll(dogs);
		for ( Dog d : dogs ) {
			if ( ! isOlderThan9yrs.test(d) ) {
				r.remove(d);
			}
		}
		return r;
	}

	private static Consumer<Dog> printNameConsumer = (d) -> { System.out.println(d.getName()); }; 
	
	private static Consumer<Dog> barkConsumer = (d) -> { System.out.println( d.bark() ); };


	


}
