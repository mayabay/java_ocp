package pkg_1;
class EnumTest2 {

	private final int myPrime  = 11;

	protected static enum EnumTester22 {			// final DNC modifier not allowed  
		EINS(1),
		ZWEI(2),
		DREI(3); 
	
		EnumTester22( int value  ){
			n = value;
			//n = myPrime;		// DNC non-static variable myPrime cannot be referenced from a static context 
		}

		private int n;

	}
	
	public static void main( String[] args ){
	
		System.out.println( EnumTester22.EINS.n  );	

	}


	

}
