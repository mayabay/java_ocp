package pkg_3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://docs.oracle.com/javase/tutorial/extra/generics/simple.html
 * Learn Generics
 */
public class GenericTest1 {

	class Pallet<T extends Number, S extends Crate<? extends Fruit>> {
		T id;				// each pallet has an id
		List<S> elements;	// a pallet can store crates of fruit
		public String toString() { return "["+this.getClass().getTypeName()+","
				+ " id="+id+", with "+elements.size()+" loaded items]"; }
	}
	
	enum Sweet { S, M, L, XL }
	
	interface Eatable {
		Sweet getSweetLevel();
	}
	
	class Plant {
		String type;
	}
	
	class Fruit extends Plant implements Eatable {
		// enum Sweet2 { GG }
		Sweet sweet = Sweet.M;	// DNC The member enum Sweet must be defined inside a static member type
		double weight;
		public Sweet getSweetLevel() {return this.sweet;}
	}
	
	class Apple extends Fruit {
		public String toString() { return "["+this.getClass().getTypeName()+","
				+ " weight="+weight+", sweet= "+sweet+"]"; }
	}
	class Pear extends Fruit {
		public String toString() { return "["+this.getClass().getTypeName()+","
				+ " weight="+weight+", sweet= "+sweet+"]"; }
	}
	class Banana extends Fruit {
		public String toString() { return "["+this.getClass().getTypeName()+","
				+ " weight="+weight+", sweet= "+sweet+"]"; }
	}
	
	/*
	 * Crate declares a formal type parameter T with a bound
	 * 
	 * */
	//class Crate< ? > {}	// DNC
	//class Crate< ? extends Fruit > {}
	class Crate< T extends Fruit > {
		T t;
		public String toString() {
			return "[Crate, t type = "+ t.getClass().getTypeName() +"]";
		}		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		GenericTest1 gt1 = new GenericTest1();
		
		// invocation of the generic type declaration using the actual type argument
		// GenericTest1.Apple
		List<Apple> list = new ArrayList<GenericTest1.Apple>();
		list.add( gt1.new Apple() );
		
		// more invocations with different actual type arguments
		Crate<Fruit> crate = gt1.new Crate<Fruit>();
		Crate<Apple> crate2 = gt1.new Crate<Apple>();
		Crate<Banana> crate3 = gt1.new Crate<Banana>();
		
		crate3.t = gt1.new Banana();
		crate.t = gt1.new Pear();
		
		
		//gt1.fillCrate(crate3, list);
		// parameterized type of crate fits bound of method argument type
		gt1.fillCrate(crate, list);
		
		// actual type argument Banana does not match argument bound of method
		//Crate<Plant> crate3 = gt1.new Crate<Plant>();
		
		// method consumes crates of eatable stuff
		gt1.eatingStuff(crate3);
		
		System.out.println("-- filling pallet -----------");
		Pallet<Integer,Crate<?>> pallet = gt1.loadPallet(Arrays.asList( crate2, crate3 ));
		System.out.println(pallet);
	}

	private <T extends Apple> void fillCrate( Crate<? super Apple> crate, List<T> list ) { 
		
		// Crate<? extends Apple> crate
		// DNC for crate.t = new Apple();
		
		// OK with Crate<? super Apple> 
		
		crate.t = new Apple();
		//crate.t = new Object(); DNC
		//crate.t = new Fruit(); DNC
		
		crate.t = list.get(0);
		System.out.println( crate );
		System.out.println( list.get(0) );
		
		//crate.t = list.get(0);	// super

	}
	
	
	private <T> void fillCrate2( Crate<Fruit> crate, List<T> list ) {
		// crate.t = list.get(0);
	}
	
	/*
	 * use bound to access common property of objects in crate
	 * */
	private void eatingStuff( Crate<? extends Eatable> crate ) {
		System.out.println("eating : " + crate.t + " with sweet level = " + crate.t.getSweetLevel());
	}
	
	/*
	 * load a pallet with crates and return it
	 * */
	private Pallet<Integer,Crate<?>> loadPallet( List<Crate<? extends Fruit>> crates ) {
		// build pallet with parametrized type
		Pallet<Integer, Crate<? extends Fruit>> pallet = new Pallet<Integer, GenericTest1.Crate<? extends Fruit>>();
		pallet.id = 42;	// set pallet id
		
		// fill box with apple
		Crate<Apple> appleBox = new Crate<GenericTest1.Apple>();
		appleBox.t = new Apple();
		// and again
		Crate<Banana> bananaBox = new Crate<GenericTest1.Banana>();
		bananaBox.t = new Banana();
		
		// fill list and load to pallet
		List<Crate<?>> listsche = new ArrayList<GenericTest1.Crate<?>>();
		listsche.add(appleBox);		// add the 2 crates
		listsche.add(bananaBox);
		listsche.addAll(crates);	// and add all additional given crates
		
		pallet.elements = listsche;
		
		return pallet;
	}
	
}
