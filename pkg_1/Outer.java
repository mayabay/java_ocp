package pkg_1;

public class Outer {

	int 	i	= 5;
	
	
	public static void main(String[] args) {
		
		Outer outer = new Outer();
		outer.doStuff();
		
		//Inner inner = new Inner();			// DNC no enclosing instance of outer is accessible 
		Inner inner2 = outer.new Inner();

	}

	void doStuff() {
		System.out.println("in Outer");
	}
	
	class Inner {
		
		void doStuff() {
			System.out.println("in Inner");
		}		
	}
	
}
