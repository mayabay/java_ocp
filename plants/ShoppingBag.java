package plants;
import java.util.*;
/**
 * A class to learn java generics
 * 
 * @author Andreas Mann
 * @version 0.1 2020-07-21
 * */
public class ShoppingBag {

	public static void main(String[] args){
		//fillBag();
		learnLowerB();
	}

	private static void learnLowerB(){
		ArrayList<Plant> plants = new ArrayList<>();
		plants.add( new Pineapple() );
		plants.add( new KoreanMelon() );
		
		Collection<? super Citrus> CitrOrSuper2 = plants;

		Collection<Plant> collP = plants;

		Collection<? super Citrus> CitrOrSuper = collP; // OK 

		//plants = addPlants( collP ); // DNC incompat with plants or collP
		
		List list = (List)addPlants( CitrOrSuper );	// DNC 27: error: incompatible types: 
												//Collection<CAP#1> cannot be converted to ArrayList<Plant>

		printAny( list );



	}

	private static void fillBag(){
		
		// built in array
		Plant[] plants = new Plant[4];
		plants[0] = new Ananas();
		plants[1] = new Orange();
		//printArr( plants );
		Cucumis[] manyCucumis = { new Cucumber(), new KoreanMelon() };
		printArr( manyCucumis );	// type conversion: ref widening

		// raw collection
		Collection rawColl = new ArrayList();
		//rawColl.add( 1 );						// both OK, compi gives warnings
		//rawColl.add( new StringBuffer() );


		// generic collections
		Collection<Plant> coll = new ArrayList<>();
		coll.add( new Pineapple() );
		coll.add( new KoreanMelon() );
		Sweet sweetAnanas = new Ananas();
		//coll.add( sweetAnanas );
		//printBag(coll);

		Collection<Cucumis> cucumis = new ArrayList<Cucumis>();
		cucumis.add(new Cucumber() ) ;
		cucumis.add(new KoreanMelon() );
		//printBag( cucumis );		// DNC 
						// reason: argument mismatch; Collection<Cucumis> cannot be converted to Collection<Plant>

		// OK   
		// printBag2( cucumis );


		// upperbound generic
		Collection<? extends Plant> coll2 = new ArrayList<>();	// this AL will stay empty forever!!!
		
		// What does a generic ref var with an upper bound represent?
		//
		//coll2.add( new Pineapple() ); // DNC error: incompatible types: Pineapple cannot be converted to CAP#1
		//coll2.add( (Plant)new KoreanMelon() );
		//coll2.add( new Plant() );
		
		//printBag(coll2); // DNC 31: error: method printBag in class ShoppingBag cannot be applied to given types;


		// working with unknown type
		// coll is passed to method, which doesnt know the payload type
		Object[] r = toArray( coll );
		for( Object o : r ){
			//System.out.println( o );
		}

		// required: class or interface without bounds found ? 
		//Collection<?> mysteryBag = new ArrayList<?>();	// 67: error: unexpected type

		Collection<?> mysteryBag2 = new ArrayList();

		Collection mysteryBag3 = new ArrayList();
		//mysteryBag3.add( new String("hello") ); // warn uses raw type

	}

	private static Object[] toArray( Collection<?> coll ){
		Object[] objArr = new Object[ coll.size() ];
		Iterator<?> it = coll.iterator();
		int i = 0;
		while( it.hasNext() ){
			objArr[i] = it.next();
			i++;
		}
		return objArr;
	}

	private static void printArr( Plant[] plants ){
		for( Plant p : plants ){
			System.out.println( p );
		}
	}

	private static void printBag( Collection<Plant> coll ){
		for( Plant p : coll ){
			System.out.println( p );
		}
	}

	private static void printBag2( Collection<? extends Plant> coll ){
		for( Plant p : coll ){
			System.out.println( p );
		}
	}

	private static void checkSweet( Collection<? extends Sweet> coll ){
		for( Sweet sweet : coll ){
			System.out.println( sweet );
		}
	}

	private static Object emptyBag( Collection<?> coll  ){
		Iterator<?> it = coll.iterator();
		while( it.hasNext() ){
			coll.remove( it.next() );
		}
		return coll;
	} 

	private static void printAny( Collection<?> coll ){
		System.out.println( "print any collection: " );
		for( Object obj : coll ){
			System.out.println( obj );
		}
	}

	/*
	 * i can return Collection<? super Citrus> 
	 * 				Collection
	 * 				Collection<?>
	 * 				Collection<? super Lime>
	 * 				Collection<? extends Object>
	 * */
	private static Collection<? extends Object> /*Collection<?>*/ addPlants( Collection<? super Citrus> coll ){
		coll.add( new Orange() );
		coll.add( new Lemon() );
		coll.add( new Citrus() );
		//coll.add( new Plant() );		// DNC incompat
		return coll;
	}

}
