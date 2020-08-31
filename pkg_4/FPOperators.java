package pkg_4;
import java.util.*;
import java.util.function.*;
public class FPOperators{

	public static void main(String[] args){
		FPOperators fpo = new FPOperators();
		fpo.go();
	}

	UnaryOperator<String> unaOp = String::toLowerCase;
	
	BinaryOperator<String> binaOp = String::concat;

	private void go(){

		System.out.println( unaOp.apply("HELLO") );

		System.out.println( binaOp.apply( "Hello", " world" ) );

	}


}
