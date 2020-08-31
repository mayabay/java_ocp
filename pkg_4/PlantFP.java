package pkg_4;
import java.util.*;
import java.util.function.*;
import plants.*;
public class PlantFP {
	
	private static int idSrc;

	/** main() */
	public static void main(String[] args){
		PlantFP pfp = new PlantFP();
		//pfp.goSupplier();
		//pfp.doDump();
	    //pfp.repack(); 
		//
	}

	private void pBox( Box<?> box ){
		for ( int i = 0; i < box.size(); i++ ){
			System.out.println( box.get(i) );
		}
	}

	private void pBoxes( List<? extends Box> boxes ){
		for( Box box : boxes ){
			System.out.println( box );
		}
	}

	// ----------------------------------------------------------------------------

	private void doDump(){
		//Box[] boxes = new CardboardBox<Hilo, Integer>[3];	/DNC error: generic array creation
		List<Box<Hilo>> boxes = new ArrayList<>();
		
		Box<Hilo> b1 = new CardboardBox<Hilo,Integer>( ++idSrc ); 
		b1.add( new Hilo() );
		boxes.add( b1 ); 

		Box<Hilo> b2 = new CardboardBox<Hilo,Integer>( ++idSrc ); 
		b2.add( new Hilo() );
		boxes.add( b2 );

		pBoxes( boxes );
		dumpBoxes( Box::clear, boxes );
		pBoxes( boxes );
	}

	/**
	 * clear Box references
	 * @param unaryOp behavoir to clear the boxes
	 * @param boxes array of boxes to dump 
	 * */
	public void dumpBoxes( UnaryOperator<Box> unaryOp, List<Box<Hilo>> boxes ){
		for( Box<Hilo> b : boxes ){
			unaryOp.apply(b);
		}
	} 

	private void goSupplier(){
		Box<? extends Ananas> r = getAnanas( () ->  new Hilo(), 4 );
		pBox( r );
	}	

	/**
	 * Returns a Box type of Ananas
	 * @param s Supplier of Ananas
	 * @param amount plant count to supply
	 * */
	public Box<? extends Ananas> getAnanas( Supplier<? extends Ananas> s, int amount ){
		CardboardBox<Ananas, Integer> cbox = new CardboardBox<Ananas, Integer>( ++idSrc );
		for ( int i = 0; i < amount; i++ ){
			cbox.add( s.get() ); 
		}
		return cbox;
	}


	private void repack(){
		Box<Plant> box1 = new CardboardBox<>();
		Box<Plant> box2 = new CardboardBox<>();
		
		box1.add( new Acuminata() );
		box2.add( new Acuminata() );
		box2.add( new Acuminata() );

		BinaryOperator<Box <Plant>> biOp = ( b1, b2 ) -> { 
			
			b1.clear();

			for( int i = 0; i < b2.size(); i++ ){
				b1.add( b2.get(i) );
			}	
			return b1;
		};

		box1 = repackToFirst( biOp, box1, box2 );
		
		pBox( box1 );
	}

	/**
	 * All elements from box1 will be repacked to box2
	 * @param biOp BinaryOperator representing the repacking behavior
	 * @param box1 target box
	 * @param box2 source box
	 * @return box1
	 * */
	public Box<Plant> repackToFirst(BinaryOperator<Box<Plant>> biOp, Box<Plant> box1, Box<Plant> box2 ){
		return biOp.apply( box1, box2 );
	}

}
