/**
 * BS 8.3.4
 */
package pkg_8;

import java.util.LinkedList;
import java.util.Queue;

/**
 * printf - learn format String
 */
public class PrintStreamTest1 {

	class Person {
		String name;
		int age;
		
		Person ( String name, int age ){
			this.name = name; this.age = age;
		}
		
		@Override
		public String toString(  ) {
			return "[ Person, name = "+ name +", age = "+age+" ]";
		}
	}
	
	Queue<PrintStreamTest1.Person> queue; 
	
	/**
	 * main()
	 */
	public static void main(String[] args) {
		PrintStreamTest1 pst1 = new PrintStreamTest1();
		
		pst1.queue = new LinkedList<>();
		Person p1 = pst1.new Person("John", 12);
		Person p2 = pst1.new Person("Linda", 18);
		pst1.queue.add(p1); pst1.queue.add(p2); 
		pst1.test1();

	}

	private void test1() {
		
		// printf converts java objects to character output
		
		// printf (format, varargs)
		// printf (locale, format, varargs)
		
		// rules begin with %
		System.out.printf("Hello %s!%n", "World");
		
		// 1.) format rules
		
		// %[flags][width][.precision]conversion-character
		System.out.printf("Hello %b!%n", false);	// %b
		
		// [] optional 
		
		// flags change output
		
		// width is minimum number of characters for output
		
		// . is precision for floating point types
		

		// 2.) conversion character
		// s format chars, 
		// d format decimal numbers and f for floating point
		// t formats date and time
				
		
		// 3.) %n separates lines
		System.out.printf("Price is $ %4.2f%n and this is in another %s", 124.5278D, "line.");
		System.out.println();
		
		// 4.) bool type
		System.out.printf("%b%n", (Object)null);
		System.out.printf("%b%n", false);
		System.out.printf("%b%n", 42);
		System.out.printf("%b%n", "false");
		
		// 5.) format Strings
		System.out.printf("%s%n","baeldung");
		System.out.printf("%S%n","baeldung");
		
		System.out.printf("'%15.2S'%n","spring");
		
		// 6.) format characters
		System.out.printf("%C%n", 'a');
	}
}
