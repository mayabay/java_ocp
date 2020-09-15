package pkg_4;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * OCP KB 8
 * 
 * */
public class Dog {

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
	
	static {
		staticVal++;
	}
	
	{
		instVal++;
	}
	
	private String name;
	private int age;
	private int weight;
	
	public Dog(String name, int age, int weight) {
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
	
	public static void main(String[] args) {
		Dog boi = new Dog("boi", 12, 23);
		Dog clover = new Dog("clover", 8, 12);
		Dog aiko = new Dog("aiko", 7, 7);

		Set<Dog> dogs = new HashSet<>();
		dogs.add(boi); dogs.add(clover); dogs.add(aiko);
		
		//Set<Dog> r = filterDogs( dogs );
		//System.out.println( r );
		
		long n =
		dogs.stream()
			.peek(printNameConsumer.andThen(barkConsumer))
			.collect(Collectors.counting());
		
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
