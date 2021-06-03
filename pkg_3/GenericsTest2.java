/**
 * 
 */
package pkg_3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * kb 6.4
 *
 */
public class GenericsTest2 {

	class CageRaw {
		Object o;
		CageRaw(){}
		CageRaw(Object o){
			this.o = o;
		}
	}
	
	class Cage<T extends Animal>{	// T extends Animal warn Animal is a raw type
		T t;
		Animal[] objects;
		Cage(){
			this(null);		// DNC undefined: new Object() new Animal()
		}
		Cage( T t ){
			this.t = t;
			objects = new Animal[42];
			objects[0] = t;
		}
		
		void inserter( Animal<T> animal ) {
			objects[0] = animal;
		}
	}
	
	class Animal<T> { 
		T t;
		Animal(){}
		Animal( T t ){
			this.t = t;
		}
		String check(){
			return "check : Animal";
		}
	}

	class Dog extends Animal {		// Animal is a raw type should be parameterized
		String getThing() {
			return " dog :  " + t;
		}
		String check(){
			return "check : Dog";
		}		
	}
	
	class Cat extends Animal {
		String check(){
			return "check : Cat";
		}
		
		String catTalk() {
			return "miauuu";
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GenericsTest2 gt2 = new GenericsTest2();
		//gt2.test1();
		
		ArrayList<String> strs = new ArrayList<>();
		strs.add("a"); strs.add("b"); strs.add("c");
		
		//gt2.test2( strs );
		
//		for( String s : strs ) {	// java.lang.ClassCastException: class java.lang.Integer cannot be cast to class java.lang.String
//			System.out.println( s.concat("x") );
//		}
		
		//gt2.test5();
		gt2.test6();
	}
	
	private void test1() {
		List l1 = new ArrayList();		// raw List
		l1.add( new Dog() );			// add anything
		l1.add( 42 );				
		
		List<String> friends = Arrays.asList("Monical", "Rachel", "Phoebe");
		List<String> friends2 = new ArrayList<>( friends );
		
		callRawListParam(l1);			// raw -> raw
		
		callGenListParam(l1);			// raw -> gen param	*type safety warning: unchecked conversion*
		
		//callRawListParam(friends);		// gen -> raw param
											// friends is a backed collection, backed by an array
		callRawListParam(friends2);		// gen -> raw param
		
		callGenericCage(new Cage());	// call generic code with raw type, warn type safety unchecked conversion
		callGenericCage(new Cage<Dog>()); // call generic code with in bound type, no warning
		
		//this.testAnimal(new Dog());
		//this.testCage(new Cage<>( new Cat() ));
		
		Cage<? extends Animal> c1 = new Cage<>( new Dog() );
		
		List<Dog> l2 = new ArrayList<>();
		addToListRawParam(l2);		// List<Dog> -> List conversion to object
		
		System.out.println( l2 );
		
		this.addToListGenParam(l1);	// List -> List<Dog> unchecked conversion
		//System.out.println(l1);
		
		// Collections.sort(l1); // CCE  class pkg_3.GenericsTest2$Dog cannot be cast to class java.lang.Integer
		
		List<Integer> ints = new ArrayList<Integer>();
		ints.add(2); ints.add(4);
		addToListRawParam(ints);
		for( Integer i : ints ) {	// adding cat was ok, but using Integer din't work here
			System.out.println( i.intValue() );
		}
	}	
	
	private void test2( List list ) {
		list.add(42);
	}
	
	private void test3() {
		// 1)
		Animal[] ans = new Cat[3];
		// 2)
		// List<Animal> ans2 = new ArrayList<Cat>(); polym not llowed for generic type
	}
	
	private void test4() {
		List<Animal> anis = new ArrayList<>();
		anis.add(new Cat());
		anis.add(new Dog());
		//vetCheck(anis); type conversion interface to impl type NOT w/o cast
		vetCheck((ArrayList)anis);
		
	}
	
	private void test5() {
		List<Dog> dogs = new ArrayList<>();
		// addAnimal(dogs, new Cat()); // DNC no polym type conv., if allowed 
									// you could add the cat to list of dogs 
	
		Cat[] cats = new Cat[4];
		addAnimal( cats, new Dog() );	// THIS WORKS!!!
		
		// but ...
		for ( Cat cat : cats ) {
			System.out.println( cat.catTalk() );
		}
	}
	
	private void test6() {
		Cage<Cat> catCage = new Cage<Cat>();
		catCage.inserter(new Dog());
		
	}
	
	private void addAnimal( List<Animal> animals, Animal a ) {
		animals.add(a);
	}
	
	private void addAnimal( Animal[] animals, Animal a ) {
		animals[0] = a;
	}	
	
	private void vetCheck( ArrayList<Animal> animals ) {
		for ( Animal a : animals ) {
			System.out.println( a.check() );
		}
	}
	
	private void callRawListParam( List list ) {
		// list.add(new Cat());		// list is a array backed collection
									// arrays d'ont support dynamic addition
		//list.add( new Object() );	//  java.lang.UnsupportedOperationException
	}

	private void callGenListParam( List<String> list ) {
		//list.add(new Cat());
		list.add("Chandler");
	}	
	
	private void testAnimal( Animal animal ) {
		return;
	}

	private void testCage( Cage<?> cage ) {
		System.out.println(cage.t);
		return;
	}

	private void callGenericCage( Cage<? extends Animal> cage ){
		
	}

	private void callRawCage( Cage cage ){
		System.out.println(cage);
	}
	
	private void addToListRawParam( List list ) {
		list.add( new Cat() );
	}
	
	private void addToListGenParam( List<String> strings ) {
		//strings.add(new Cat()); // DNC only String objects allowed
		strings.add("huhu");
		//strings.add( new Object() ); // DNS s
	}
	
	private List willReturnRawList( List list ){
		list.add( new Cat() );
		return list ;
	} 

	private List<Animal> willReturnGenList( List<Animal> list ){
		list.add( new Cat() );
		return list ;
	} 	
	
}
