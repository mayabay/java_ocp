package pkg_4;
import java.util.*;
import java.util.function.*;
public class UsingVarInLambda{

	private interface QuadConsumer<T> {
		void accept( T t1, T t2, T t3, T t4 );
	}

	private QuadConsumer<String> quaddy = ( str1, str2, str3, str4 ) -> { System.out.println( str1 + ", " + str2 + ", " + str3 + ", " + str4 ); };


	private QuadConsumerTop<String> quaddyTop = ( str1, str2, str3, str4 ) -> { System.out.println( str1 + ", " + str2 + ", " + str3 + ", " + str4 ); };

	private static String name = "Steve Rogers";

	static {
		name = "Bruce Banner";
	}	

	private String name2 = "Tony Stark";

	{
		name2 = "John Wayne";
	}

	/** main() */
	public static void main(String[] args){
		UsingVarInLambda uvil = new UsingVarInLambda();
		uvil.go( "Indiana Jones", "Lisa" );
	}

	private void go( String name3, String name33 ){

		String name4 = "John Watson";

		name3 = "Foo1";
		name4 = "Bar1";

		//pNames( quaddy, name, name2, name3, name4  );
		//pNamesTop( quaddyTop, name, name2, name3, name4  );
		pNames(
			(s1, s2, s3, s4) -> {System.out.println( s1 + ", " + s2 + ", " + s3 + "-"+name33+"-" +  ", " + s4 );},
			name, name2, name3, name4
				);

		name3 = "Foo2";
		name4 = "Bar2";


		//name33 = "Bart"; // error: local variables referenced from a lambda expression must be final or effectively final
	}

	// using inner interface QuadConsumer
	private void pNames( QuadConsumer<String> quaddy, String s1, String s2, String s3, String s4 ){
		quaddy.accept( s1,s2,s3,s4 );
	}

	// using top level interface QuadConsumerTop
	private void pNamesTop( QuadConsumerTop<String> quaddy, String s1, String s2, String s3, String s4 ){
		quaddy.accept( s1,s2,s3,s4 );
	}

}
