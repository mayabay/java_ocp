package pkg_1;
class NestedClTest1 {

	private static volatile int counter;
	private String txt = "1";

	public final class InnerClass {
		
		private static final int counter = 0;		// inner instance only constants allowed
														// 8: error: illegal combination of modifiers: final and volatile
		// http://tutorials.jenkov.com/java-concurrency/volatile.html
		// in multithreaded apps on multicore systems a var may exist in main memory with another value than in cpu cache
		// a variable marked volatile will be written back to main memory immidiately 
		// volatile garantees visibility 

		/*
		modifier 'static' is only allowed in constant variable declarations
		static {
			counter = 0;
		}
		*/

		private int id;
		private String txt;

		public InnerClass(){
			this.id = ++NestedClTest1.counter;
			this.txt = "inner #" + this.id;
		}

		public int getId(){
			return id;
		}

		public void doStuff(){
			//txt = "2";
			System.out.println( txt );
			System.out.println( NestedClTest1.this.txt );


		}

	}

	public static void main(String[] args){

		NestedClTest1 nc1 = new NestedClTest1();
		nc1.do1();

		InnerClass ic4 = nc1.new InnerClass();
		ic4.doStuff();
	}

	private void do1(){
		InnerClass i1 = new InnerClass();
		InnerClass i2 = new InnerClass();
		InnerClass i3 = new InnerClass();
		System.out.println( i3.getId() );
		i3.doStuff();

	} 



}
