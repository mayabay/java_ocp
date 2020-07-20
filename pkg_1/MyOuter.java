package pkg_1;
class MyOuter {

	private int x = 7;

	class MyInner{
		public void seeOuter(){
			System.out.println( "Outer x is = " + x );
			System.out.println( "see inner this " + this );
			System.out.println( "see outer this " + MyOuter.this );

		} 
	}

	
	public static void main(String[] args){
		MyOuter out = new MyOuter();
		out.makeInner();
	}

	// make inner from outer instance
	public void makeInner(){
		MyInner in = new MyInner();			// DNC if surrounding method is static 
											// 19: error: non-static variable this cannot be referenced from a static context 
		in.seeOuter();
	}

	// make inner from outer static
	public static void sm(){
		
		// a)
		MyOuter mo = new MyOuter();
		MyOuter.MyInner mi_a = mo.new MyInner();
		mi_a.seeOuter();

		// b)
		MyOuter.MyInner mi_b = new MyOuter().new MyInner();
		mi_b.seeOuter();

	}

	void walkThisOnTrain(){
		wagon1(this);
	}

	static void wagon1( MyOuter mo ){
		wagon2(mo);
	}

	static void wagon2( MyOuter mo ){
		wagon3(mo);
	}
	
	static void wagon3( MyOuter mo ){
		mo.makeInner();
	}
}
