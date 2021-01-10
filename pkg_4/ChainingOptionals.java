/**
 * HH-DR
 * https://github.com/HH-DR/SimpleTests/blob/master/SimpleTests/src/ocp4/StreamTestsAllIntermediates.java
 * */
package pkg_4;

import java.util.Optional;

public class ChainingOptionals {
	
	// Optional nutzt methoden von Streams
	private static void threeDigit( Optional<Integer> optional) {
		
		optional.map( x -> "" + x)
				.filter( str ->  str.length() == 3)
				.ifPresent( d -> System.out.println( d ) );
	}
	
	
	private static Optional<Integer> calculator( String str ) {
		
		return Optional.of(	str.length() );
		
		
	}
	
	public static void main(String[] args) {
		
		Optional<Integer> opti_12 =  Optional.of( 12 );
		Optional<Integer> opti_321 =  Optional.of( 321 );
		
		
		threeDigit( opti_12 );
		threeDigit( opti_321 );
		
		

		Optional<String> opti_String = Optional.of("n´Ábend");
		
		// Optional<Integer> opti_Integer = opti_String.map( ChainingOptionals::calculator ); 
		// geht nicht, weil returns Optional von Optional von String
		//Type mismatch: cannot convert from Optional<Optional<Integer>> to Optional<Integer>
		// deswegen flatMap()
		
		Optional<Integer> opti_Integer = opti_String.flatMap( ChainingOptionals::calculator );
		System.out.println( opti_Integer );
		
		
		System.out.println("----------");
		// Verschachtelung Optionals Auflösung mit flatMap() auf die unterste Ebene
		Optional<String> opti_String_layer1 = Optional.of("Layer one");									// 1. Ebene
		Optional<Optional<String>> opti_String_layer2 = Optional.of( opti_String_layer1 );				// 2. Ebene
		Optional<Optional<Optional<String>>> opti_String_layer3 = Optional.of( opti_String_layer2 ); 	// 3.Ebene
		
		Optional<String> unwrapOptionals = opti_String_layer3.flatMap( optiLayer2 ->  optiLayer2.get());
		
		System.out.println( unwrapOptionals );
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}