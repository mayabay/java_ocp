package pkg_1;
class CastingTest1{

	public static void main(String[] args){

	}

	static void do1(){

		byte by = 8;
		short sh = -16;
		char ch = 'a';
		int i = 32;
		long lo = 64L;
		float fl = 3.14F;
		double d = 3.14D;

		//Number[] nums = { by, sh, ch, i, fl, lo, d };		// ch is not a Number, Character extends Object

		// --- primitive casting n = narrowing, w = widening

		// (1) smaller -> bigger is widening otherwise narrowing
		sh = by;			// w
		by = (byte)i;		// n	

		// (2) to char is always narrowing because char is unigned
		ch = (char)sh;		// n

		// (3) floating point to integer is narrowing
		i = (int)fl;		// n
		fl = i;				// w

		lo = (long)d;		// n
		d = lo;				// w	

		// --- numeric promotion in binary arithmetic operations
		short sh2 = (short) (by + ch);	// by -> int, ch -> int, sum is int

		double d2 = lo * d;
		
		int i3 = (int) (fl * by);		// last step is float to int -> n 

		//float fl2 = d % by;			// both to double, modulus to float is -> n

		double d3 = ch % d2;

		int i4 = by % sh;

	}

}
