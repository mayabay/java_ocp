package training;

public class Outer {

	private 				int 			i			= 5;
	private static final 	int				CONSTANT 	= 11; 
	
	private 				void 	say() {
		System.out.println( "in outer say()" );
	}

	private static 			void	sayClass(){
		System.out.println( "in outer sayClass()" );
	}

	public static void main(String[] args) {
		
		Outer outer = new Outer();
		outer.doStuff();
		
		//Inner inner = new Inner();			// DNC no enclosing instance of outer is accessible
											// DNC 13: error: non-static variable this cannot be referenced from a static context	
		Inner inner2 = outer.new Inner();
		inner2.doStuff();

		InnerStatic is1 = new InnerStatic();
		Outer.InnerStatic is2 = new Outer.InnerStatic();

	}

	void doStuff() {
		System.out.println("in Outer");
	}
	
	/*  member inner class
	 *	anny access mod
	 *	extend any class, impl any interface
	 *	abstract of final
	 *	no static fields and m() allowed, constants ok
	 *	can acc all member of outer
	 * */
	class Inner {
		
		private int i = 6;

		//private static int s = 5;		// 46: error: Illegal static declaration in inner class Outer.Inner
										// modifier 'static' is only allowed in constant variable declarations

		private static final int INNER_CONSTANT = 12;


		void doStuff() {
			System.out.println("in Inner, i = " + i);				// inner member
			System.out.println("in Inner, i = " + Outer.this.i);	// outer member
			Outer.this.say();
			Outer.sayClass();
		}		
	}
	

	static class InnerStatic {
		int i = 10;

		void doStuff() {
			System.out.println("in Inner");
		}		
	}
}
