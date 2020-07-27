package plants;
import java.util.*;
public class PlantFactory {
	
	public static void main(String[] args){
		packBox();
	}

	private static void packBox(){

		//Box<Plant>[] boxes = new Box<Plant>[10];	// DNC 10: error: generic array creation
		
		//Box<Plant>[] boxes = new Box<>[10]; // DNC 12: error: cannot create array with '<>'	
		
		Box<Lemon>[] boxes = new CardboardBox[10];	// warn 13: warning: [unchecked] unchecked conversion
		
		// arrays for generic classes can only use <?>
		Box<?>[] more = new CardboardBox<?>[10];

		for( int i = 0; i < 10; i++ ){
			boxes[i] = new CardboardBox<>();
			boxes[i].add( new Lemon() ); 
			 
		}	

		//loadTruck( boxes );

		CardboardBox<Lime> limeBox =  new CardboardBox<>();
		//limeBox.add( new Orange() );	// DNC type safety at work
										// 29: error: incompatible types: Orange cannot be converted to Lime
		limeBox.add( new Lime() );
			// DNC w/o cast :33: error: no suitable method found for loadTruck(CardboardBox<Lime>
		//loadTruck( ( Box<? extends Sour> )limeBox );	// Lemon does implement Sour NOT Lime :-D
		
		CardboardBox<Lemon> lemonBox = new CardboardBox<>();
		lemonBox.add( new Lemon() );

		Sour sourFruit = new Lemon();
		loadTruck( lemonBox );

	}

	private static void loadTruck( Box<? extends Sour>[] boxes  ){
		for( Box<? extends Sour> s : boxes ){
			System.out.println( "loading " + s.get( 0 ) );
		}
	}

	private static void loadTruck( Box< ? extends Sour > box ){
		int size = box.size();
		for( int i = 0; i < size; i++ ){
			System.out.println( "loading " + box.get(i) );
		}
	}

}
