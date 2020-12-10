/**
 * OCP BS 1.7.3
 */
package pkg_2;

import java.lang.reflect.Field;

/**
 * @author andreas
 * test types of anonymous inner classes
 */
public class AnonInnerTest1 {

	class Animal {
		String species;
		boolean canFly;
		boolean canHop;
		Animal(String species, boolean canFly, boolean canHop ){
			this.species = species;
			this.canFly = canFly;
			this.canHop = canHop;
		}
		public String toString() {
			return "["+this.species+", hopper = "+this.canHop+", flyer "+this.canFly+"]";
		}
	}
	
	@FunctionalInterface
	interface CheckTrait {
		boolean test(Animal animal);
	}
	
	class AnimalCanHopChecker implements CheckTrait {
		@Override
		public boolean test(Animal animal) {
			return animal.canHop;
		}
		
	}
	
	AnimalCanHopChecker ani = new AnimalCanHopChecker ()  { 
		
		public void doIt( Animal animal ) {
			this.test(animal);
		}
		
		public boolean isStrange() { return true; }
		
	};
	
	class SpecialAnimalCanHopChecker  extends  AnimalCanHopChecker {
		
	}	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AnonInnerTest1 ait1 = new AnonInnerTest1();
		ait1.test1();

	}
	
	private void test1() {
		Animal eagle = new Animal( "bird", true, false );
		Animal bear = new Animal( "bear", false, true );
		Animal kangaroo = new Animal( "kangaroo", false, true );
		
		// lambda
		boolean eagleCanHop = this.analyzeAnimal(eagle, a -> a.canHop);
		// anon inner 
		boolean bearCanHop = this.analyzeAnimal(bear, new CheckTrait () {
			
			@Override
			public boolean test (Animal animal) {
				System.out.println( "ne = " + this.ne() );
				return animal.canHop;
			}
			
			int number;
			
			public boolean ne() {
				
				Class cla = this.getClass();
				Field[] fields =  cla.getDeclaredFields();
				for ( Field field : fields ) {
					System.out.println( "field name = " +  field.getName());
					System.out.println( "field modi = " +  field.getModifiers() );
					
				}
				
				number++;
				number--;
				return number == 0;
			}
			
		});
		
		

		
		
		// anon inner 
		boolean kangarooCanHop = this.analyzeAnimal(kangaroo, new AnimalCanHopChecker ()  { 
			
			public void doIt( Animal animal ) {
				this.test(animal);
			}
			
			public boolean isStrange() { return true; }
			
		} );
		
		// 
		boolean kangarooCanHop2 = this.analyzeAnimal(kangaroo, new AnimalCanHopChecker());  
		
		
		System.out.println("kangarooCanHop = " + kangarooCanHop );
	}

	
	private boolean analyzeAnimal ( Animal animal, CheckTrait checkTrait ) {
		return checkTrait.test(animal);
	}
	
}
