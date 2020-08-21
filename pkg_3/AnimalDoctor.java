package pkg_3;
import java.util.*;
public class AnimalDoctor {


	public static void main(String[] args){
		
		AnimalDoctor doc = new AnimalDoctor();

		// built in arrays 
		Dog dog = new Dog("aiko");
		Cat cat = new Cat("mini");
		Animal[] animals = { dog, cat };
		
		Dog[] dogs2 = { dog, new Dog("peter") };

		doc.checkup( dogs2 );
		// call is ok Dog[] is a Animal[]


		// Collection 
		List<Animal> animalColl = new ArrayList<>();
		animalColl.add( new Dog() );
		animalColl.add( new Cat() );

		List<Dog> dogs = Arrays.asList( new Dog("aiko"), new Dog("rex") );

		//doc.checkupColl( dogs );	// DNC 25: error: incompatible types: List<Dog> cannot be converted to List<Animal>
		//WHY WHY WHY???

	}

	private void checkup( Animal[] animals ){
		for( Animal animal : animals ){
			System.out.println( "checking a " + animal.getSpecies() );
		}

		// THIS IS THE PROBLEM WITH ARRAYS
		animals[0] = new Cat();		// RTE java.lang.ArrayStoreException if animals points to Dog[] on heap 

	}

	private void checkupColl( List<Animal> animals ){
		for( Animal animal : animals ){
			System.out.println( "checking a " + animal.getSpecies() );
		}

	}

	private void addAnimal( List<? super Dog> animals ){
		Animal cat = new Cat();

		animals.add( new Dog() );
		//animals.add( new Object() );	// DNC 54: error: no suitable method found for add(Object)
		//animals.add( cat );			// DNC 55: error: no suitable method found for add(Animal)
		
		// dont put random thin in boxes!!
		// List<? super Dog> animals means, that any List of Dog or supertype of it can be converted to this ref
		// The lower bound type is what you can add to the collection, no other type!
		//
	}

	private void testWildcards(){
		
		List<?> list = new ArrayList<Dog>();
		
		List<? extends Animal> aList = new ArrayList<Dog>();

		//List<?> foo = new ArrayList<? extends Animal>(); // DNC required: class or interface without bounds
		
		//List<? extends Dog> cList = new ArrayList<Integer>(); // DNC
				//71: error: incompatible types: ArrayList<Integer> cannot be converted to List<? extends Dog>
	
		List<? super Dog> bList = new ArrayList<Animal>();

		//List<? super Animal> dList = new ArrayList<Dog>();	// DNC
			// 76: error: incompatible types: ArrayList<Dog> cannot be converted to List<? super Animal>

	}

}

