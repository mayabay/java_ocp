/**
 * KB 9, 2 Minute
 */
package pkg_4;

import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 
 *
 */
public class StreamsOfStreamsTest1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StreamsOfStreamsTest1 sost1 = new StreamsOfStreamsTest1();
		sost1.test1();

	}

	private void test1() {
		Stream<Stream<String>> streami = Stream.of (
				Stream.of("one", "two", "three"),
				Stream.of("four", "five", "six"),
				Stream.of("seven", "eight", "nine")
				);
		String s =
		streami.flatMap(Function.identity())
		.collect(Collectors.joining("-"));
		
		System.out.println(s);
	}
	
}
