package assesment;

import java.time.LocalDate;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class Q20 {

	public static void main(String[] args) {
		Stream<LocalDate> s = Stream.of(LocalDate.now());
		
		UnaryOperator<LocalDate> u = l -> l;
		UnaryOperator<LocalDate> u2 = UnaryOperator.identity();

		s.filter( l -> l != null ).map( u2 ).peek(System.out::println);
		
	}

}
