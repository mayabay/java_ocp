package pkg_1;
class StaticInner {
	static class Nest {
		void go(){  System.out.println( "Hi" );	}
	}
	
	public static void main(String[] args){
		StaticInner.Nest nest = new StaticInner.Nest();
		nest.go();

		StaticInner.Nest nest2 = new StaticInner.Nest();
		nest2.go();

		Nest nest3 = new Nest();
		nest3.go();
	}
}	
