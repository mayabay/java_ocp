package plants;
import java.util.*;
class LearnWildcard {

	//private T unknown;

	public static <T> void eat( T t ){
		//System.out.println( "eating : " + t );
	}

	public static <T extends Plant> void eatSweet ( Box<T> t ){
		for( int i = 0; i < t.size(); i++ ){
			//System.out.println(  "eating " + t.get(i) );
		}
	}
  
	// public static <T> void fillBox ( Box<T super Pineapple> box ){
	// public static void fillBox ( Box<? super Pineapple> box ){
	public static void fillBox ( Box<? super Pineapple> box ){
		box.add( new Pineapple() );
		box.add( new Hilo() );
	}

	public static void main(String[] args){

		// using a generic type w/o generics w/o type arguments
		Box rawBox = new CardboardBox();
		CardboardBox<Cucumis, Integer> cb = new CardboardBox<Cucumis, Integer>();
		rawBox = cb;										// no warning 
													// raw types points to param type
		cb = ( CardboardBox<Cucumis, Integer> )rawBox;		// unhecked warn
													// param type points to raw type

		Ananas ana =  new Ananas();
		LearnWildcard.<Ananas>eat(ana);

		Box<Plant> sweetBox = new CardboardBox<>();
		sweetBox.add( ana );
		sweetBox.add( new Cucumber() );
		sweetBox.add( new KoreanMelon() );
		eatSweet( sweetBox );

		// lower bound at work
		Box<Ananas> boxAna = new CardboardBox<>();
		boxAna.add( new Ananas() );
		fillBox( boxAna );

		pBox( boxAna );

	}

	public static void pBox( Box<?> box ){
		for( int i = 0; i < box.size(); i++ ){
			System.out.println( box.get(i) );
		}
	} 



}
