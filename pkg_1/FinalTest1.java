package pkg_1;
import java.util.*;
class FinalTest1{
	
	public static void main(String[] args){
		do1();
	}
	
	static void do1(){
		final int n = 42;
		char c = n;
		byte x = 3;
		//char cc = x; // DNC incompat
		
		// 32 bit int float
		// 64 bit long double
		double d = (long)34;	// ok		
		//long l = (double)34;	// DNC incompat	

		 //(float)34;			// DNC err not a stmt




	}
}
