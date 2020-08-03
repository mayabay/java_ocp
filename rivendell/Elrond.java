package rivendell;
import shire.*;
public class Elrond {
	void testIt(){
		Child c = new Child();
		//System.out.println( c.foodBagSize ); // DNC 6: error: foodBagSize has protected access in Hobbit
				// Child extends Hobbit but only Hobbit has recieved foodBagSize by inheritance

	}
}
