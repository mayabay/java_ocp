/**
 * BS 3
 */
package pkg_3;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;



/**
 * 
 *
 */
public class BoundsTest3 {

	private class LifeForm {}
	
	private class Animal extends LifeForm {}
	
	private class Plant extends LifeForm{}
	
	private class Bird extends Animal {}
	
	private class Mammal extends Animal {}
	
	private class Primate extends Mammal {}

	private class HomoSapiens extends Primate {
		int spokenLanguageCount;
		HomoSapiens(){}
		HomoSapiens( int spokenLanguageCount ){ this.spokenLanguageCount = spokenLanguageCount; }
	}
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BoundsTest3 bt3 = new BoundsTest3();
		//bt3.test1();
		bt3.test2();

	}

	private void test1() {
		
		String name = new String[] { "Chandler","Rachel","Monica" } [1];
		
		List<String> list0 = new ArrayList<>();
		list0.add(name);
		
		List<?> list1 = new LinkedList<>( list0 );
		System.out.println( list1.get(0) );
		list1.remove(0);
		System.out.println("size = " + list1.size());
		
		// upper bound Mammal
		//List<? extends Mammal> list2 = new LinkedList<? extends Mammal>(  );
		List<? extends Mammal> list2 = new LinkedList<>(  );
		System.out.println("type of List created with  <? extends Mammal> | <>");
		
		// what is type of list2?
		Class classOfList2 = list2.getClass();
		TypeVariable[] typeVars = classOfList2.getTypeParameters();	// only returns declared type vars
		for ( TypeVariable tv : typeVars ) {
			Type[] types = tv.getBounds();
			System.out.println(types);
			for( Type type : types ) {
				System.out.println("\t" + type);
			}
			
			System.out.println( "type name = " + tv.getTypeName() );
			//System.out.println("type is " + tv + ", with class " +tv.getClass());
		}
		
		
		list2.add(null);			// here is no problem with type safety
		Mammal m1 = new Mammal();
		//list2.add(m1);				// list2 cannot tell you, what type of 
									//objects are allowed in this list
		
		// lower bound Mammal
		List<? super Mammal> list3 = new LinkedList<>();
		
		Object obj1 = new Object();
		LifeForm lf1 = new LifeForm();
		Animal an1 = new Animal();
		Bird br1 = new Bird();
		Mammal ma1 = new Mammal();
		Primate pr1 = new Primate();
		
		//list3.add( obj1 );	// Object is not a Mammal
		//list3.add( an1 );
		//list3.add( br1 );
		list3.add( ma1 );
		list3.add( pr1 );
		
		List<Bird> birds = new LinkedList<BoundsTest3.Bird>();
		//this.addMammal(birds, br1); 	// type of list must me a Mammal 
		//this.addAnimal(birds, br1);	// type of list must be Animal
		this.addMammal(list3, pr1);		// pr1 is upcast to Mammal
		
		List<Mammal> justMammals = new LinkedList<BoundsTest3.Mammal>();
		this.addMammal(justMammals, pr1);
		
		List<Object> justObjects = new LinkedList<>();
		this.addMammal(justObjects, null);
		this.addMammal(justObjects, pr1);
		//this.addMammal(justObjects, br1);	// br1 is not a Mammal
		this.addAnimal(justObjects, br1);
		
		List<LifeForm> justLifeForms = new LinkedList<BoundsTest3.LifeForm>();
		this.addMammal(justLifeForms, pr1);	// pr1 is a Mammal
		
		List<?> justUnknown = new LinkedList<Mammal>();
		justUnknown.add(null);
		//justUnknown.add(new Object()); // no match
		
		List<?> dontKnowList = this.getListOf( new LifeForm[] { new Mammal(), new Animal(), new HomoSapiens() } );
		
	}	
	
	private void test2() {
		HomoSapiens hs = this.<BoundsTest3.HomoSapiens>speaksMoreLang(new HomoSapiens(2), new HomoSapiens(3));
		if (hs == null) { System.out.println("speaking same number of languages"); }
		else {
			System.out.println("hs speaks " + hs.spokenLanguageCount + " languages.");
		}
	}
	
	private boolean addAnimal( List<? super Animal> list, Animal animal ) {
		return list.add(animal);
	}	
	
	private boolean addMammal( List<? super Mammal> list, Mammal mammal ) {
		list.add( new HomoSapiens() );
		list.add( new Primate() );
		list.add( new Mammal() );
		//list.add( new Animal() );		// only Mammal or sub type can be added 
		//list.add( new LifeForm() );	//
		return list.add(mammal);
	}
	
	/*
	 * <? super LifeForm> as return type
	 * */
	private List<? super BoundsTest3.LifeForm> getListOf( BoundsTest3.LifeForm ...forms  ) {
		//LinkedList<? super BoundsTest3.LifeForm> maList = new LinkedList<BoundsTest3.Mammal>( Arrays.asList(forms) );
		//LinkedList<? extends BoundsTest3.LifeForm> maList = new LinkedList<BoundsTest3.Mammal>(  );
					// ctor fails: LifeForm is not a Mammal - new LinkedList<BoundsTest3.Mammal>( Arrays.asList(forms) );
					// OK w/o ctor argument, but then immutable 
		LinkedList<? super BoundsTest3.LifeForm> maList = new LinkedList<Object>( Arrays.asList(forms) );
					// assignment OK
		maList.add(new Primate());
		maList.add(new HomoSapiens());
		return maList;		// DNC LinkedList<Mammal> maList
	}
	
	private <T extends HomoSapiens>  T speaksMoreLang( T t1, T t2 ) {
		if ( t1.spokenLanguageCount == t2.spokenLanguageCount ) { return null; }
		return t2.spokenLanguageCount > t1.spokenLanguageCount ? t2 : t1;
	}
	
}
