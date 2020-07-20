package math;
import java.util.*;

/**
 * Utility class for fractions
 *
 * */
public class Fractions{
	
	public static void main(String[] args){

	}

	/**
	 * Will bring an array of Fractions to a common denominator
	 * @param fractions - array of Fraction objects
	 * @return same fractions raised to a common denominator
	 * */
	public static Fraction[] toCommonDenominator( Fraction[] fractions ){
		// get array of all denominators
		int[] denoms = getDenominators(fractions); 

		int biggestDenom = getBiggest(denoms);
		int commonMultiple = getCommonMultiple( denoms );

		List<Fraction> ret = new ArrayList<>();
		for ( Fraction frac : fractions ){
			int factor = commonMultiple / frac.getDenom();
			ret.add( frac.raise( factor ) );
		}

		return ret.toArray(new Fraction[ ret.size() ] );
	}

	/**
	 * calculates the smallest common denominator 
	 * @param array of fractions
	 * @return smallest common denominator
	 * @throws IllegalArgumentException if fraction is null or empty
	 * */
	public static int getSmallestCommonDenominator(Fraction[] fractions){
		if ( fractions == null || fractions.length < 1 ) throw new IllegalArgumentException("fractions cannot be null or empty");

		// get array of all denominators
		int[] denoms = getDenominators(fractions);
		// get smalles common multiple scd
		int commonMultiple = getCommonMultiple( denoms );

		return commonMultiple;

	}

	/**
	 * get smallest common multiple for array of denominators
	 * @param array of denominators
	 * @throws IllegalArgumentException if argumant is null or empty
	 * */
	public static int getCommonMultiple(int[] denominators){
		if ( denominators == null || denominators.length < 1 ) throw new IllegalArgumentException("array cannot be null or empty");
		
		int biggestDenom = getBiggest(denominators);
		int commonMultiple = 0;

		// multiple of biggestDenom
		for ( int i = 1; i < Integer.MAX_VALUE; i++ ){
			int multiple = biggestDenom * i;
			// check all denoms 
			if ( Numbers.isCommonMultiple( denominators, multiple ) ){
				commonMultiple = multiple; 
				break;
			} 
		}
		return commonMultiple;
	}

	/**
	 * gets denominators from fractions
	 * @return array of denominators of given fractions
	 * */
	public static int[] getDenominators( Fraction[] fractions ){
		// get array of all denominators
		int[] denoms = new int[ fractions.length ];
		for( int i = 0; i < fractions.length; i++  ){
			//Fraction frac = fractions[i];	
			denoms[i] = fractions[i].getDenom();
		}
		return denoms;
	}


	/**
	 * adds two Fractions
	 * @param Fraction a
	 * @param Fraction b
	 * @return Fraction sum with common denominator 
	 * */
	public static Fraction add( Fraction a, Fraction b ){
		Fraction[] r = toCommonDenominator( new Fraction[]{a,b} );
		return new Fraction( ( r[0].getNum() + r[1].getNum() ),r[0].getDenom() );	
	}

	/**
	 * substracts two Fractions
	 * @param Fraction a
	 * @param Fraction b
	 * @return Fraction substratcion with common denominator 
	 * */
	public static Fraction substract (Fraction a, Fraction b ){
		Fraction[] r = toCommonDenominator( new Fraction[]{a,b} );
		return new Fraction( ( r[0].getNum() - r[1].getNum() ),r[0].getDenom() );	
	}
	

	// ---------------------------------------------------------------------------

	private static int getBiggest(int[] numbers){
		int bigger = Integer.MIN_VALUE;
		for ( int i = 0; i < numbers.length; i++ ){
			if (numbers[i] > bigger) { 
				bigger = numbers[i];
			}
		}
		return bigger; 
	}
}
