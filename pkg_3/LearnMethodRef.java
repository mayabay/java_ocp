package pkg_3;
import java.util.*;
import java.util.function.*;
/**
 * Learn method referenced
 * */
public class LearnMethodRef {

	// write the Consumer behavior 
	private static Consumer<List<Integer>> intListSorter = ( List<Integer> list ) -> Collections.sort(list);
	
	// use a reference to an existing static method from the api to implement the lambda
	private static Consumer<List<Integer>> intListSorter2 = Collections::sort;
	
	 
	//Predicate<String> strTester2 = String::startsWith; 
		// DNC Cannot make a static reference to the non-static method startsWith(String) from the type String
	
	// using lambda and input parameter
	private static void sort ( Consumer<List<Integer>> consumer, List<Integer> list ) { 
		consumer.accept(list);
	} 
	
	public static void main (String[] args) {
		do1();
	} 
	
	// (1) method references for static methods
	private static void do1() {
		
		List<Integer> ints = Arrays.asList(5,8,2,9,1,0);
		sort( intListSorter2, ints );
		System.out.println( ints );
	}
	
	// (2) 
	private static void do2() {
		
		String aString = "Andreas";
		String prefix = "";
		
		Predicate<String> strTester1 = ( String str ) -> str.startsWith(prefix);
		
		//Predicate<String> strTester2 = aString::startsWith;
	
		//Predicate<String> strTester2 = String::startsWith;
				// DNC non-static method startsWith(String) cannot be referenced from a static context

		Predicate<String> strTester3 = String::isEmpty; // OK

	}
	
	
	
	
}
