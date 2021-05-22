/**
 * 
 */
package pkg_3;

import java.util.function.Consumer;

/**
 * https://stackoverflow.com/questions/37308294/why-does-a-java-method-reference-with-return-type-match-the-consumer-interface
 *
 */
public class ConsumerTest1 {

	Consumer<Integer> con1 = x -> x++;
	Consumer<String> con2 = s -> String.valueOf(s);
	//Consumer<Integer> con3 = i -> i;	// void cannot return a value
	Consumer<Integer> con4 = i2 -> {return;};
	Consumer<Character> con5 = c -> {};
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ConsumerTest1 ct2 = new ConsumerTest1();
		
		ct2.test2('c', ct2.con5);
		ct2.test2('c', ct2::eatChar)
		;
	}

	private Character eatChar( Character c ) {
		return c=+1;
	}

	private void test2 ( Character c, Consumer<Character> consumer ) {
		consumer.accept(c);
	}
	
}
