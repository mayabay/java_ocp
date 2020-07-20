package pkg_1;
class AnonInner{

	
	public static void main(String[] args){
		System.out.println( new AnonInner().new Food().popcorn1  );

		System.out.println( 
				new AnonInner().new Food().popcorn1.pop()  
		);

		System.out.println( 
				//new AnonInner().new Food().popcorn1.m()	// DNC cannot find symbol  
		);

		System.out.println( 
				new AnonInner().new Food().c.cook()  
		);

		AnonInner.Food f = new AnonInner().new Food(); 

		AnonInner ai = new AnonInner();
		String statement = ai.prepare( 30, new Cookable(){
			public String cook(){
				return "apple pie";
			}
		} );

		System.out.println( "statement = " + statement );

	}

	private interface Cookable{
		public String cook();
	}

	private class Popcorn {
		private boolean isSpicy;
		Popcorn(){}
		Popcorn( boolean b ){ isSpicy = b; }
		public String pop(){
			System.out.println( "popcorn pops " + isSpicy );
			return "popper";
		}
	}

	private String prepare( int minutes , Cookable c ){
		return "preparing in " + minutes + " something to cook: " + c.cook();
	}

	private class Food {
		// flavor1: extender 
		Popcorn popcorn1 = new Popcorn() {
			// Popcorn (){ super(true); } // DNC error: invalid method declaration; return type required

			public String pop(){
				System.out.println( "extended pop" );
				return "overridden popper";
			}
			
			public String toString(){
				pop();
				super.pop();
				m();
				return "blub";
			}
			private String s;
			private void m(){ System.out.println( "m() .." ); }
		};

		// flavor2: implementer
		Cookable c = new Cookable(){
			public String cook(){ return "pasta"; }
		};

	}
}
