package pkg_1;
class NestedClTest2 {

	public static void main(String[] args){
		NestedClTest2 nc = new NestedClTest2();
		nc.doStuff();
	}


	private void doStuff(){
		int n = 10;
		n++;	// n not effectivly final now
		final int n2 = 11;

	  	class InnerLocal {
			private double value;
			public InnerLocal(){
				value = Math.random();
			}
			public double getValue(){
				return value;
			}
			void show(){
				//System.out.println( "n = " + n );	
				// DNC error: local variables referenced from an inner class must be final or effectively final
				System.out.println( "n2 = " + n2 );

			}

		}

		class BabyInnerLocal extends InnerLocal {
			
		}

		BabyInnerLocal bil = new BabyInnerLocal();
		System.out.println( bil.getValue() );
		bil.show();


	}


}
