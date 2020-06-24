package pkg_1;
import java.util.*;
interface I1 {}
interface I2 {}
class A1 implements I1 {}
class B1 extends A1 {}
class C1 {}
class B11 extends A1 implements I2 {}

class ClassTest1 {
	
	public static void main(String[] args){
		do1();
	}

	static void do1(){
		// 1.) upcast implicit
		A1 a = new B1();
		
		// 2.) downcast	explicit
		B1 b = (B1)a;
		
		// 3.) class ref to interface implicit
		I1 i = a;			// OK implicit, because A1 is-a I1
		
		// 4.) interface ref to class ref implicit
		//A1 another2 = i;	// DNC incompat  I1 is not a A1
		
		// 5.) interface ref to class ref explicit
		A1 another = (A1)i;	// OK and fine because obj i is pointing to is also an A1 
		
		// 6.) interface to unrelated class implicit 
		//C1 c1 = i; 		// DNC incompat
		
		// 7.) interface to unrelated explicit 
		C1 c1 = (C1)i;		// OK but CCE
		
		// 8.) interface in unrelated interface explicit
		//C1 c2 = (List)i;	// DNC incompat

		// 9.) interface in unrelated interface 
		//I2 i2 = i; 		// DNC incompat I2 = I1
		//I2 i2c = a;		// DNC incompat	

		// 10.) A1 in unrelated I2
		I2 i2b = (I2)a;		// OK but CCE

		// 11.) interface in unrelated interface
		I2 i3 = (I2)i;		// OK but CCE

		System.out.println("a points to B1 = " +  (a instanceof B1) );
		System.out.println("i points to A1 = " +  (i instanceof A1) );
		

		// BS Bonus Test2 Q54 ---------------------------------------------------
		A1 a2 = new B11();
		
		Object o1 = a2;
	
		//String s1 = a2;	// DNC incompat

		I1 i4 = (I1)o1;		// OK
		
		I2 i4b = (I2)i4;	// OK

		Number n1 = (Number)o1;		// OK but CCE  

		//String s2 = (String)a2;	// DNC incmopat

		I2 i5 = (I2)n1;		// OK CCE
		

	}

}
