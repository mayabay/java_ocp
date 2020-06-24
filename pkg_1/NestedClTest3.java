package pkg_1;
class NestedClTest3 {
	
	private interface CanFly {
		void fly();
	} 

	private abstract class Animal{
		public abstract String getSpecies();
	}

	private class Dog extends Animal{
		public String getSpecies(){
			return "Dog";
		}
	}

	public static void main(String[] args){
		NestedClTest3 nc3 = new NestedClTest3();
		nc3.doStuff();
	}

	private void doStuff(){
	
		double maxSpeed = 0x2B;
		double height = 1024.;

		CanFly canFly = new CanFly(){
			public void fly(){
				System.out.println( "flying ... " + height + " m above ground." );
			}
		};

		canFly.fly();

		Animal charlie = new Dog();
		System.out.println( charlie.getSpecies() );

		class Mouse extends Animal{
			//private final double maxSpeed = maxSpeed;	// DNC 40: error: self-reference in initializer
			private final double _maxSpeed = maxSpeed;	
			public String getSpecies(){
				return "Mouse";
			}
			public void run(){ System.out.println( "running at " + _maxSpeed );}

		} 

		Mouse bert = new Mouse();
		System.out.println( bert.getSpecies() );
		bert.run();


	}



}
