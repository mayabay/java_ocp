package pkg_1;
class MyOuter2 {

	private interface Callable {
		void call();
	}

	private static Callable cally;

	private String x = "MyOuter2";
	//private MyInner myInner;	// DNC cannot find Symbol

	public static void main(String[] args){
		MyOuter2 mo2 = new MyOuter2();
		mo2.doStuff();
		mo2.checkCallable( cally );
	}

	void doStuff(){
	
		String z = "local var";
		x += "A"; 
		

		class MyInner implements Callable {

			// static int n = 42;	// illegal static decl
			private final int n2 = 42; 

			public void call(){
				System.out.println( "was called" );
			}

			public void seeOuter(){
				System.out.println( x );
				System.out.println( z );	// OK as long as z is never changed in doStuff() 
				//z += "A";		// DNC 20: error: local variables referenced from an inner class must be final or effectively final

			}
		}
		MyInner my = new MyInner();
		my.seeOuter();

		cally = my;

		//z += "A";	// this would prevent making z effectivly final
		x += "B";
		my.seeOuter();
	}

	/*	
	void checkMyInner( MyInner mi ){		// DNC cannot find symbol
		System.out.println( mi.seeOuter() );
	}
	*/


	private void checkCallable( Callable c ){
		c.call();
	} 	


}
