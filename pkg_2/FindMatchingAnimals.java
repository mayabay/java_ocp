package pkg_2;
public class FindMatchingAnimals{
	
	public static void main(String[] args){
		
		printAnimal( new Animal("fish", false, true), a -> a.canHop() );

		printAnimal( new Animal("hawk", true, false), (a) -> {return a.canHop();} );


	}

	private static void printAnimal (Animal a, CheckTrait trait){
		if ( trait.test(a) ){
			System.out.println( a );
		}
	}

	public void shit(){
		shit: return;
	}
}
