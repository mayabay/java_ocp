package math;
import java.util.*;
import static math.Fractions.*;
class FractionsTest1{

	public static void main(String[] args){
		do2();
	}
	
	static void do1(){

		//System.out.println( getBiggest( new int[] {3,73,5,17,7,11} ) );

		Fraction[] r = toCommonDenominator( new Fraction[] { 
			new Fraction(2,3),
			new Fraction(1,6),
			new Fraction(7,10),
			new Fraction(1,4),
			new Fraction(11,12),
			new Fraction(3,5)
		} );

		System.out.println( Arrays.toString(r) );

		r = toCommonDenominator( new Fraction[] { 
			new Fraction(3,4),
			new Fraction(1,8),
			new Fraction(2,5),
			new Fraction(13,20),
			new Fraction(1,2),
			new Fraction(9,10)
		} );


		r = toCommonDenominator( new Fraction[] { 
			new Fraction(2,5),
			new Fraction(17,20),
			new Fraction(9,25),
			new Fraction(3,4),
			new Fraction(39,50)
		} );


		Fraction[] fracs = new Fraction[]{
			new Fraction(2,3),
			new Fraction(1,6),
			new Fraction(7,10),
			new Fraction(1,4),
			new Fraction(11,12),
			new Fraction(3,5)
		};

		// scd
		System.out.println( "scd = " + getSmallestCommonDenominator(fracs) );

		// common multiple
		System.out.println( "common multiple = " + getCommonMultiple( getDenominators(fracs) ) );
		
		System.out.println( "sum of 1/18 and 5/6 = " + add( new Fraction(1,18),new Fraction(5,6) ) );
		System.out.println( "sum of 13/108 and 11/12 = " + add( new Fraction(13,108),new Fraction(11,12) ) );

		Fraction[] fracs2 = new Fraction[]{ new Fraction(16,45), new Fraction(25,72) };
		System.out.println( getSmallestCommonDenominator( fracs2 ) );
	}

	static void do2(){
		System.out.println( "sum = " + ( add( new Fraction(19,48), new Fraction(3,4) ) ) );

	}

}
