package plants;
import java.util.*;
/**
 * Questions and exercises
 * https://docs.oracle.com/javase/tutorial/java/generics/QandE/generics-questions.html
 * */
class GenericsJavaTut {

	public static void main(String[] args){
		//test1( Arrays.asList( 1,2,3,4,5 ) );

		test1( Arrays.asList( 1.1,2.1,3.1,4.1,5.1 ) );
	}

	public static void test1( Collection<? extends Number> coll ){

		for( Number n : coll ){
			if(n instanceof Long){
			}
		}

	}

}
