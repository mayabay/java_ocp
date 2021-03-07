package assesment;

import java.io.Console;

public class Q13 {

	public static void main(String[] args) {
		Console c = System.console();
		String line = null;
		while( (line = c.readLine()) != null ) {
			System.out.println(line);
		}

	}

}
