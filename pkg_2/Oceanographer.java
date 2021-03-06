package pkg_2;
public class Oceanographer{
	public static void checkSound( LivesInOcean inOcean ){
		inOcean.makeSound();
	}

	public static void main(String[] args){
		checkSound( new Dolphin() );
		checkSound( new Whale() );
		
		// target type is unrelated interface to dolphin type
		Fly flyingDolphin = (Fly)new Dolphin();		// OK but CCE
													// DNC if Dolphin is final
		
		// target type is unrelated interface to source type
		DeepDiver deepDivingDolphin = (DeepDiver)flyingDolphin;
		
	}
}