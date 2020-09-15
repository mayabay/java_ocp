package pkg_4;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;
import plants.*;
/** 
 * Examples for Collector implementations from the Collectors class 
 *
 * */
public class CollectorExamples {

	// source for serial no
	private static int idSrc;
	
	/**
	 * Supplier for a Set of boxes of citrus plants
	 * 
	 *  @return 
	 *   */
	public static Supplier< Set<CardboardBox<Citrus, Integer> > > boxSetSupplier = () -> {
		
		Set<CardboardBox<Citrus, Integer> > boxSet = new HashSet<>();
		
		int count = (int) ((Math.random()) * 10); ; 
		
		for( int i = 0; i < count; i++ ) {
			CardboardBox<Citrus, Integer> cb = new CardboardBox<>( ++idSrc );
			cb.add(new Orange());
			cb.add(new Lemon());
			cb.add(new Lime());
			boxSet.add(cb);
		}
		
		return boxSet;
	};
	
	
	
	/**
	 * main()
	 * */
	public static void main(String[] args){
		CollectorExamples ce = new CollectorExamples();
		ce.go();
	}

	private void go(){}
}
