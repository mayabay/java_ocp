package math;
import java.util.*;
/**
 * The class contains operations on numbers.
 * */
public class Numbers{

	public static void main(String[] args){
		//System.out.println( "divisors of 18  " + divisorsOf(18) );
		//System.out.println( "divisors of 33  " + divisorsOf(33) );
		//System.out.println( "common divisors are = " + commonDivisors( 25, 40 ) );

		//System.out.println( "multiples of 6  " + multiplesOf(6, 10) );
		//System.out.println( "7 isPrime = " + isPrime(7) );
		//System.out.println( "73 isPrime = " + isPrime(73) );
		
		//System.out.println( "prime number <= 49 : " + primes(49) );

		//System.out.println( "next prime for 15 is " + (nextPrime(15)) );

		//System.out.println( "prime factors for 20 = " + (primeFactors(20)) );

		//System.out.println( "gcd (24,42) = " + (greatestCommonDivisor(24,42)) );
		System.out.println( "gcd (126,300) = " + (greatestCommonDivisor(126,300)) );
	}

	/**
	 * Returns a set of multiples of a given number
	 * @param a - find multiples for a
	 * @param limit - maximum multiple
	 * @return Set of Integers
	 * */
	public static Set<Integer> multiplesOf (int a, int limit ){
		Set<Integer> set = new TreeSet<>();
		for ( int i = 1; i <= limit; i++ ){
			set.add( a * i );
		}
		return set;
	}

	/**
	 * Gives a list of all divisors of a given number
	 * @param a - number for which divisors are searched for
	 * @return Set (sorted) of Integers which are divisors of a
	 * */
	public static Set<Integer> divisorsOf(int a){
		Set<Integer> set = new TreeSet<>();
		for ( int i = 1; i <= a; i++ ){
			if( a % i == 0 ){ set.add(i); }
		}
		return set;
	}
	
	/**
	 * A prime number can only by devides by 1 or itself without rest.
	 * @param upper - upper limit
	 * @return Set of prime numbers up until upper 
	 * */
	public static Set<Integer> primes(int upper){
		Set<Integer> set = new TreeSet<>();
		for( int i = 2; i <= upper; i++ ){
		if ( isPrime(i) ) set.add(i);	
		}
		return set;
	}

	/**
	 * Determines if a given number is a prime number
	 * @param a - number
	 * @return true - if given number is only dividable by 1 or itself
	 * */
	public static boolean isPrime(int a){
		if (a == 2) return true;
		for( int i = 2; i < a; i++ ){
			if ( a % i == 0 ){ return false; }
		}
		return true;
	}

	/**
	 * Returns a set of Integers representing commnon divisors of two numbers
	 * @param a - first number
	 * @param b - second number
	 * @return Set (sorted) of common divisors (intersection of divisors of a and b)
	 * */
	public static Set<Integer> commonDivisors(int a, int b){
		int bigger, smaller;
		if (a == b) { bigger = a; smaller = b; }
		else if ( a > b ){ bigger = a; smaller = b; }
		else{ bigger = b; smaller = a; }
		Set<Integer> setA = divisorsOf(bigger);
		Set<Integer> setB = divisorsOf(smaller);
		setA.retainAll(setB);
		return setA;
	}

	/**
	 * returns the greates common divisor of two numbers
	 * @param a - 
	 * @param b - 
	 * @return greatest divisor
	 * */
	public static int greatestCommonDivisor(int a, int b){
		SortedSet<Integer> set = (SortedSet<Integer>)commonDivisors( a, b ); 
		return set.last();
	} 

	/**
	 *	Finds the next prime number for a given number
	 *	@param n - an integer
	 *	@return r - returns value of next prime number
	 * */
	public static int nextPrime( int n ){
		for( int i = ++n; i < Integer.MAX_VALUE; i++ ){
			if ( isPrime(i) ) return i;
		}	
		return -1;
	}

	/**
	 * Finds the prime factors for a given number
	 * @param n - number, for which we look for prime factors
	 * @return List<Integer> of primefactors
	 * */
	public static List<Integer> primeFactors( int n ){
		if ( n < 2 ) return null;
		List<Integer> factors = new ArrayList<>();
		int prime = 2; 
		int rest = n;
		while( true ){
			if (rest % prime == 0){
				factors.add(prime);
				rest = rest / prime;
				if (isPrime(rest)) {
					factors.add(rest);
					break;
				};
			}else{
				prime = nextPrime(prime);
			}
		}
		// test result
		int sum = 0;
		for( int i = 0; i < factors.size(); i++ ) {
			if (i == factors.size() -1 ) break;
			if( i == 0 ) sum = factors.get(0);
			sum = ( sum * factors.get( i + 1 ) );
			//System.out.println( "sum = " + sum );
		} 
		if (sum != n) { throw new RuntimeException("factors not : " + n + "; sum = "+ sum +",  factors = " + factors); }
		
		return factors;
	}


	/**
	 * checks, if a given value is multiple of all numbers
	 * @param numbers - array of numbers
	 * @param value - integer
	 * @return true - if each given number is multiple of value
	 *  */
	public static boolean isCommonMultiple( int[] numbers, int value ){
		boolean isCommon = false;
		for( int i = 0; i < numbers.length; i++ ){
			if ( value % numbers[i] == 0 ){
				isCommon = true;
			}
			else {
				isCommon = false;
				return false;
			}
		}
		return isCommon;
	}
}
