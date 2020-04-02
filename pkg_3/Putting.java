/* 
 * OCP 3.2.6
 * Putting it all together 
 * */
package pkg_3;
import java.util.*;
class A { 
	void do1(){ System.out.println( "A: do1()" ); }
}
class B extends A {
	void do1(){ System.out.println( "B: do1()" ); }
}
class C extends B {
	void do1(){ System.out.println( "C: do1()" ); }
}
class X {}
public class Putting {
	
	public static void main(String[] args){
		do2();
	}

	static void do2(){
		
		List<String> friends = new ArrayList<>();

		friends.add("Monica");
		friends.add("Phoebe");
		friends.add("Chandler");
		friends.add("Ross");
		friends.add("Rachel");
		friends.add("Joey");

		Collections.sort( friends );

		System.out.println( friends );

		int result = Collections.binarySearch( friends, "Monica" );
		System.out.println( "result = " + result );

	}



	static void do1(){
	
		List<?> list1 			= new ArrayList<A>();		// unbounded: any generic can be referenced
		List<? extends A> list2	= new ArrayList<A>();		// upper bound: any that is A or extends A (A,B,C)
		List<? super A> list3	= new ArrayList<A>();		// lower bound: any that is A or super of A (A,Object)
		//list3.add(new Object());	// 21: error: no suitable method found for add(Object)
		list3.add( new A() );
		list3 = new ArrayList<Object>();
		//list3.add( new Object() );	// DNC 24: error: no suitable method found for add(Object)

		/*
		*
		PS D:\projects\java_ocp> javac pkg_3/Putting.java -Xdiags:verbose
		pkg_3\Putting.java:24: error: no suitable method found for add(Object)
					list3.add( new Object() );
						 ^
		method Collection.add(CAP#1) is not applicable
		  (argument mismatch; Object cannot be converted to CAP#1)
		method List.add(CAP#1) is not applicable
		  (argument mismatch; Object cannot be converted to CAP#1)
		method List.add(int,CAP#1) is not applicable
		  (actual and formal argument lists differ in length)
		where CAP#1 is a fresh type-variable:
		CAP#1 extends Object super: A from capture of ? super A
		1 error
		*
		* */
		List<? super B> list3b	= new ArrayList<B>();	// declared ref list3b can be ref to any B or 
		//list3b.add( new A() );			// DNC 44: error: no suitable method found for add(A)
		list3b.add( new B() );				// B() is a B	
		list3b.add( new C() );				// C() is a B
		
		list3b = new ArrayList<A>();		// OK A is parent of B
		//list3b.add( new A() );				// DNC 49: error: no suitable method found for add(A)
							// CAP#1 extends Object super: B from capture of ? super B
		//list3b = new ArrayList<X>();		// DNC 49: error: incompatible types: ArrayList<X> cannot be converted to List<? super B>
	


		list3b = new ArrayList<A>();

		List<? extends B> list4 = new ArrayList<>();
		//list4 = new ArrayList(); 		// OK warning: uses unchecked or unsafe operations.
		//list4 = new ArrayList<A>();	// DNC 52: error: incompatible types: ArrayList<A> cannot be converted to List<? extends B>
		list4 = new ArrayList<B>();		// B is B
		list4 = new ArrayList<C>();		// C extends B



	}

}
