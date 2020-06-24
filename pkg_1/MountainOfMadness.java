package pkg_1;
interface Admirable { void marvelAt(String animal); }
interface CanFly extends Admirable { void fly(); }
interface CanDive extends Admirable  { void dive(); }
interface CanSpeak {}
abstract class Animal implements Admirable {  }
abstract class Fish extends Animal implements CanDive {
	public void dive(){ System.out.println( "diving ..." ); }
}
abstract class Bird extends Animal implements CanFly {
	public void fly(){ System.out.println( "flying ..." ); }
}
class Dolphin extends Fish {
	public void dive(){	System.out.println( "dolphin diving ..." ); }
	public void marvelAt(String name) { System.out.println( "looking at " + name ); }
}
class Hawk extends Bird {
	public void fly(){	System.out.println( "hawk flying ..." ); }
	public void marvelAt(String name) { System.out.println( "looking at " + name ); }
}
final class FijiGoshawk extends Hawk {}
public class MountainOfMadness {

	public static void main(String[] args){
		go();
	}

	static void go(){
		
		// -- objects
		Dolphin dolphin = new Dolphin();
		Hawk hawk = new Hawk();
		FijiGoshawk fidjihawk = new FijiGoshawk();

		// -- class to class (class) target = source (class) 
		Fish fish = dolphin;					// (1)
		Animal animal = fish;
		Bird bird = hawk;
		Animal animal2 = bird;
		Bird bird2 = (Bird)animal;				// OK CCE

		//Bird bird2 = (Bird)dolphin;			// DNC cannot convert
		

		// -- class to interface (related, implements ...)
		Admirable admirable = dolphin;
		CanDive diver = dolphin;

		// -- class to interface (unrelated)
		//CanFly flyer = dolphin;				// DNC cannot convert ..
		//CanFly flyer2 = (CanFly)dolphin;		// OK but CCE	
		//CanDive diver2 = (CanDive)hawk;		// same 
		//CanDive diver3 = (CanDive)fidjihawk;	// OK if FidjiGoshawk is NOT final



		// -- interface to interface 
		//CanDive diver = admirable;			// DNC cannot convert
		Admirable admirable2 = diver;
		CanDive diver2 = (CanDive)admirable;
		CanFly flyer2 = (CanFly)admirable;
		//CanDive diver3 = flyer2;				// DNC cannot ...
		//CanDive diver4 = (CanFly)flyer2;		// DNC cannot
		CanDive diver5 = (CanDive)flyer2;
		CanFly flyer3 = (CanFly)diver5;
		CanSpeak speaker = (CanSpeak)admirable;


		// interface to class
		//Hawk hawk3 = admirable;				// DNC cannot ...
		Hawk kawk4 = (Hawk)admirable;
		Object o1 = admirable;


	}
}
