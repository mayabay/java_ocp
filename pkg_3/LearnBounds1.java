package pkg_3;
import java.util.*;
import java.io.*;

class Animal1 {}
class Bird1 extends Animal1 {}
class Hawk1 extends Bird1 {}

public class LearnBounds1 {


	public static void main(String[] args){
		do2();
	}

	static void do1(){

		List<? super IOException > exceptions = new ArrayList<>();

		//exceptions.add( new Exception() ); 	// DNC no suitable
											// CAP#1 extends Object super: IOException from capture of ? super IOException

		exceptions.add( new IOException() );			// OK

		exceptions.add( new FileNotFoundException() );	// OK

		exceptions = new ArrayList<IOException>();		// OK

		exceptions = new ArrayList<Exception>();		// ok

		exceptions = new ArrayList<Object>();
		exceptions.add( null );
		// exceptions.add( new Object() );		// DNC no suitable
		exceptions.add( new FileNotFoundException() );	// OK

		ArrayList<Object> objs = new ArrayList<>();
		objs.add( null );
		objs.add( new Object() );
		objs.add( new Integer(1) );
		exceptions = objs;

		for( Object o : exceptions ){
			System.out.println( o );

		}



	}

	static void do2(){
		// upper bound -> immutable		
		List<? extends Exception > exceptions = new ArrayList<>();

		//exceptions.add( new Exception() );		// DNC no suitable
		//exceptions.add( new IOException() );		// DNC no suitable

		ArrayList<Object> objs = new ArrayList<>();

		//exceptions = objs;						// DNC incompat
				
		ArrayList<Exception> exc = new ArrayList<>();

		exceptions = exc;

	}


	static void do3(){
		String[] strArr = new String[2];
		
		String[] strArr2 = new String[] { "A","B" };
		
		String[] strArr3, strArr4, strArr5;
		
		strArr3 = new String[4];
		
		strArr4 = new String[] { "C","D" };
		
		//strArr5 = { "E","F" };	// DNC illegal start of expr
		
		String[] strArr6 = { "X","Y","Z" };	// anonym arr initial only in declar


	}


	static void do4(){
		
		String[] strArr = new String[4];

		Object[] objArr = strArr;						// 

		// ---

		List<String> listStr = new ArrayList<>();
		
		//List<Object> listObj = listStr;				// DNC incompat

		// ---
		Animal1 a = new Animal1();
		Bird1 b = new Bird1();
		Hawk1 h = new Hawk1();
		List<?> l1 = new ArrayList<>();
		//addAnimal( l1, a );							// DNC incompat ? extends Object

		List<? extends Bird1> birds = new ArrayList<>();

		List<Animal1> animals = new ArrayList<>();
		addAnimal4( animals, new Hawk1() );

		List<Bird1> birds2 = new ArrayList<>();
		addAnimal4( birds2, new Hawk1() );

		List<Hawk1> hawks = new ArrayList<>();
		//addAnimal4( hawks, new Hawk1() );				// DNC
		/*
pkg_3\LearnBounds1.java:116: error: method addAnimal4 in class LearnBounds1 cannot be applied to given types;
                addAnimal4( hawks, new Hawk1() );
                ^
  required: List<? super Bird1>,Bird1
  found: List<Hawk1>,Hawk1
  reason: argument mismatch; List<Hawk1> cannot be converted to List<? super Bird1>
1 error
		 * */

	}

	static void addAnimal( List<Animal1> animals, Animal1 a ){
		animals.add( a );
	}

	static void addAnimal2( List<? extends Bird1> animals, Animal1 a ){
		//animals.add( a );						// DNC no suitable
		animals.remove( a );
	}

	static void addAnimal3( List<? super Bird1> animals, Animal1 a ){
		//animals.add( a );						// DNC no suitable  CAP#1 extends Object super: Bird1 from capture of ? super Bird1
	}

	static void addAnimal4( List<? super Bird1> animals, Bird1 b ){
		animals.add( b );						// OK can add Bird
		animals.add( new Hawk1() );
	}

}
