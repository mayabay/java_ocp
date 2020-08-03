package plants;
import java.util.*;
class LearnWildcard {

	//private T unknown;

	// eat something
	public static <T> void eat( T t ){
		//System.out.println( "eating : " + t );
	}

	// eat something out of a box
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
		rawBox = cb;										// no warning => less knowledge but greater knowledge behind 
													// raw types points to param type
		cb = ( CardboardBox<Cucumis, Integer> )rawBox;		// unhecked warn => great knowledge but less knowledge behind
													// param type points to raw type

		Ananas ana =  new Pineapple();
		LearnWildcard.<Ananas>eat(ana);

		Box<Plant> sweetBox = new CardboardBox<>();
		sweetBox.add( ana );
		sweetBox.add( new Cucumber() );
		sweetBox.add( new KoreanMelon() );
		eatSweet( sweetBox );

		// lower bound at work
		Box<Ananas> boxAna = new CardboardBox<>();
		boxAna.add( new Pineapple() );
		fillBox( boxAna );

		pBox( boxAna );


		// pack station for plants
		// many boxes ready to be filled with plants 
		List<CardboardBox<Cucumis, Integer> > boxes = new ArrayList<>(); 

		for( int i = 0; i < 10; i++ ){
			//packStationPlants( new Ananas(), boxes );		// DNC Ananas is-a plant, but does not fit in boxes
										// check for type safety
			//LearnWildcard.<Cucumis, Integer>packStationPlants( new Cucumber(), boxes );
			packStationPlants( new Cucumber(), boxes );
		}

		/*
		PS D:\projects\java_ocp> javac plants/LearnWildcard.java
		plants\LearnWildcard.java:56: error: method packStationPlants in class LearnWildcard cannot be applied to given types;
                        packStationPlants( new Ananas(), boxes );
                        ^
		  required: T,List<CardboardBox<T,S>>
		  found: Ananas,List<CardboardBox<Cucumis,Integer>>
		  reason: inference variable T has incompatible bounds
			equality constraints: Cucumis
			lower bounds: Ananas
		  where T,S are type-variables:
			T extends Plant declared in method <T,S>packStationPlants(T,List<CardboardBox<T,S>>)
			S extends Object declared in method <T,S>packStationPlants(T,List<CardboardBox<T,S>>)	

		 * */


	}

	public static void pBox( Box<?> box ){
		for( int i = 0; i < box.size(); i++ ){
			System.out.println( box.get(i) );
		}
	} 

	private static <T extends Plant, S> void packStationPlants( T t,  List<CardboardBox<T, S> > boxes ){
		CardboardBox<T, S> cb = new CardboardBox<>();
		cb.add(t);
		boxes.add( cb );
	} 


	private static void warn(){
		List l = new ArrayList();
		List<Integer> l2 = new ArrayList<>();
		l2 = l;
	}


}
