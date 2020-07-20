package pkg_1;
class InnerEnumTest1 {

	static int n = 442;

	protected enum InnerEnum { VAL1,VAL2,VAL3; };	// both ; optional here
	
	private static enum Inner2 { 

		VAL4(11) {

			// constant specific class body

			//public int calcN(){return n*1;} // DNC 8: error: non-static variable n cannot be referenced from a static context
			public int calcN(){ return m*1;} 
			int m = 42;
			//static int n = 43;	// DNC 12: error: Illegal static declaration in inner class
									// error: Illegal static declaration in inner class <anonymous pkg_1.InnerEnumTest1$Inner2$1>
								// modifier 'static' is only allowed in constant variable declarations
			//public int calcN2(){ return m * 1; }		// DNC cant override, overridden is final

		},	
		VAL5(12) { 
			public int calcN(){ return m*2;} 
		}, 
		VAL6(13) { 
			public int calcN(){ return 3;} 
		} 
		;		// ; not optional here!!
		
		private int n;
		private static int m;		// 

		public abstract int calcN();
		public final int calcN2(){ return n; };
		//public static int calcN3(){ return n; };

		private Inner2(int n){
			this.n = n;
		}

	}

	public static void main(String[] args){
		InnerEnumTest1 ie1 = new InnerEnumTest1();
		//System.out.println( ie1.InnerEnum.VAL2.ordinal() );	// 9: error: unexpected type
		System.out.println( InnerEnum.VAL2.ordinal() );
		System.out.println( InnerEnum.VAL2 );
		System.out.println( Inner2.VAL6.ordinal() );

	}


}
