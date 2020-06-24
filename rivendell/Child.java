package rivendell;
import shire.*;
public class Child extends Hobbit {
	void testIt(){
		//System.out.println( height );  DNC 5: error: height is not public in Hobbit; cannot be accessed from outside package
		System.out.println( foodBagSize );

		Hobbit frodo = new Child();
		//System.out.println( frodo.foodBagSize );
					// DNC 9: error: foodBagSize has protected access in Hobbit
		System.out.println( ((Child)frodo).foodBagSize );
					// ref accessing protected member from outside superclass package must own the member by inheritance 


	}
}
