package cat;
abstract class Cat {

	// 1. nix
	
	// 2.
	abstract void clean();
	
	// 3.
	//static void clean(){}		// DNC 7: error: method clean() is already defined in class Cat   
	
	//void clean(){} // DNC if 7 is loaded: 12: error: method clean() is already defined in class Cat


}

class Lion extends Cat {
	
	void clean(){
		//clean();
	}


}
class Cat2 {

	final static String name = "The Cat";

	void clean(){};
								// not final -> its already overridden in Lion2
								// not static -> namespace!
								// DNC 36: error: clean() in Lion2 cannot override clean() in Cat2
}

class Lion2 extends Cat2 {
	
	void clean(){};
}
