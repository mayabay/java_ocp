package math;
import java.util.*;

public class DivisorFinder{

	public static void main(String[] args){
		System.out.println(  getDivisors(18)  );
	
	}

	/**
	 * Gives a list of all divisors of a given number
	 * @param a - number for which divisors are searched for
	 * @return List of Integers which are divisors of a
	 * */
	public static List<Integer> getDivisors(int a){
		List<Integer> r = new ArrayList<>();
		for ( int i = 1; i <= a; i++ ){
			if( a % i == 0 ){ r.add(i); }
		}
		return r;
	}


}
