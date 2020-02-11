package pkg_2;
public class Oceanographer{
	public static void checkSound( LivesInOcean inOcean ){
		inOcean.makeSound();
	}

	public static void main(String[] args){
		checkSound( new Dolphin() );
		checkSound( new Whale() );
	}
}
