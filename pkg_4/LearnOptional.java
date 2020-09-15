package pkg_4;
import java.util.*;
import java.util.stream.Stream;

import plants.*;
/**
 * OCP 4.2
 * */
public class LearnOptional{

	private static int idSrc; 

	/** main() */
	public static void main(String[] args){
		LearnOptional lo = new LearnOptional();
		//lo.go();
		//lo.chainOptional(lo.getBox(3));
		lo.chainOptional2();
	}

	private void go(){
		Optional< List<Box<Musa>> > opt = getBox(3);
		List<Box<Musa>> res = null; 
		if ( opt.isPresent() ){
			res = opt.get();
			System.out.println( res );

		}

	}

	private Optional< List<Box<Musa>> > getBox(int num){
		List<Box<Musa>> boxes = new ArrayList<>();
		
		Box<Musa> b1 = new CardboardBox<Musa, Integer>( ++idSrc );
		Box<Musa> b2 = new CardboardBox<Musa, Integer>( ++idSrc );
		Box<Musa> b3 = new CardboardBox<Musa, Integer>( ++idSrc );

		b1.add( new Acuminata() );
		b2.add( new Acuminata() );
		b3.add( new Acuminata() );

		boxes.add(b1);
		boxes.add(b2);
		boxes.add(b3);

		//return Optional.of( boxes ); OK
		//return boxes == null ? Optional.empty() : Optional.of( boxes ) ;	// with ternary
		return Optional.ofNullable( boxes );	
	}

	private void chainOptional( Optional<?> opt ) {
		opt	.flatMap( o -> { return  Optional.of( o.toString() ) ; } )
			.ifPresent( System.out::println );
	}
	
	private void chainOptional2() {
		String[] arr = { "Fred", "Wilma", "Frank", "Lisa", "Bart" };
		
		Stream<String> strStream = Arrays.stream(arr);
		
		Optional<Integer> opt = strStream
				.map( String::length )
				.reduce( (i1, i2) -> { return i1 += i2; } );
		
		System.out.println( opt.orElse(42) );	// 22
		
		
	}

}
