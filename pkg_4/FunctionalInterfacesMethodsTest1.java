package pkg_4;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import plants.Acuminata;
import plants.Basjoo;
import plants.Box;
import plants.CardboardBox;
import plants.Musa;

/**
 * Test default and static methods of functional interfaces
 * 
 * */
public class FunctionalInterfacesMethodsTest1 {

	public static void main(String[] args) {
		FunctionalInterfacesMethodsTest1 fimt1 = new FunctionalInterfacesMethodsTest1();

		//fimt1.goPredicate();
		// fimt1.goConsumer();
		fimt1.goFunction();
	}

	/*
	 * Test banana boxes
	 * */
	private void goPredicate() {
		
		SurinamBananas sb = new SurinamBananas();
		
		CardboardBox<Musa,Integer> cb = sb.boxSupplier.get();
		
		// 3 bananas
		cb.add(new Basjoo()); // cb.add(new Basjoo()); cb.add(new Basjoo());
		
		Predicate< CardboardBox<Musa,Integer> > predIsEmpty = (c) -> c.size() == 0;
		
		Predicate< CardboardBox<Musa,Integer> > predLessThan3 = (c) -> c.size() < 3;
		Predicate< CardboardBox<Musa,Integer> > predMoreThan2 = (c) -> c.size() > 2;
		Predicate< CardboardBox<Musa,Integer> > predMoreThan6 = (c) -> c.size() > 6;
		
		Predicate< CardboardBox<Musa,Integer> > notEmptyAndMoreThan2 = predIsEmpty.negate().and(predMoreThan2);
		
		Predicate< CardboardBox<Musa,Integer> > lessThanThreeOrMoreThan6 = (predIsEmpty.negate().and(predLessThan3)) .or(predIsEmpty.negate().and(predMoreThan6) );
		
		//System.out.println( "notEmptyAndMoreThan2: "  + notEmptyAndMoreThan2.test(cb) );
		System.out.println( "lessThanThreeOrMoreThan6: "  + lessThanThreeOrMoreThan6.test(cb) );
		
	}
	
	/*
	 * consume banana boxes
	 * */
	private void goConsumer() {
		
		SurinamBananas sb = new SurinamBananas();
		
		CardboardBox<Musa,Integer> cb = sb.boxSupplier.get();		
		
		// 3 bananas
		cb.add(new Basjoo()); cb.add(new Basjoo()); cb.add(new Basjoo()); cb.add(new Acuminata());
		
		//System.out.println(cb); // CardboardBox with 1 items of type: plants.Basjoo
		
		//Consumer <CardboardBox<Musa,Integer>> consuBoxInspector = CardboardBox::toString;
		
		Consumer <CardboardBox<Musa,Integer>> consuBoxTerminator = CardboardBox::clear;
	
		Consumer <CardboardBox<Musa,Integer>> consuBoxPrinter = System.out::println;
		
		Consumer <CardboardBox<Musa,Integer>> consuBoxFinalizer = consuBoxPrinter.andThen(consuBoxTerminator).andThen(consuBoxPrinter);
		
		
		//consuBoxInspector.accept(cb);
		consuBoxFinalizer.accept(cb);
		
	}
	
	/*
	 * Work on returned boxes
	 * */
	private void goFunction() {
		
		SurinamBananas sb = new SurinamBananas();
		
		CardboardBox<Musa,Integer> cb = sb.boxSupplier.get();		
		
		// 3 bananas
		cb.add(new Basjoo()); cb.add(new Basjoo()); cb.add(new Basjoo()); cb.add(new Acuminata());		
		
		System.out.println( cb );
		
		Box<Musa> musaBox = (Box<Musa>)cb;
		
		Function<? super CardboardBox<Musa,Integer>, ? extends Box<Musa> > func1Terminator = cb1 -> { cb1.clear(); return cb;};
		
		Box<? extends Musa> box = func1Terminator.apply(cb);
		
		System.out.println("func1Terminator: " + box);
		
	}
	
}
