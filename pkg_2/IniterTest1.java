package pkg_2;
class Init1 {
	Init1(){
		System.out.println( "Init1()" );
	}

	{ System.out.println( "Ins1" ); }
	static { System.out.println( "Static1" ); }
}
class Init2 extends Init1 {
	Init2(){
		System.out.println( "Init2()" );
	}

	{ System.out.println( "Ins2" ); }
	static { System.out.println( "Static2" );}
}
class IniterTest1{

	public static void main(String[] args){
		new Init1();
		new Init2();

		// static 1
		// Ins1, Init1() 
		// static 2
		// Ins1, Init1(), Ins2, Init2()
	}
}
