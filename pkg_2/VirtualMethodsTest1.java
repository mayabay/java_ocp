/**
 * BS 2
 */
package pkg_2;

/**
 * virtual method invocation
 * inner classes 
 */
public class VirtualMethodsTest1 {

	private static String nully;
	private static final int FUN_LEVEL;
	private final int FUN_LEVEL_INSTANCE;

	static {
		System.out.println("nully is " + nully);
		//FUN_LEVEL = 42;
		FUN_LEVEL = calculateFun();	// call static method below me
	}
	
	static {
		nully = "is here";
		System.out.println("nully is " + nully);
	}
	
	{
		FUN_LEVEL_INSTANCE = calculateInstanceFun();
	}
	
	private static int calculateFun() {
		return 42;
	}

	private int calculateInstanceFun() {
		return 42;
	}		
	
	
	// -------------------------------------------------------------------------
	
	public VirtualMethodsTest1(){
		this(42);
	}
	
	public VirtualMethodsTest1( int funLevel ){
		super();
	}
	
	interface HungryAnimal {				// implicit modifiers
	    int VALUE = 42;						// public static final
		String eat();						// public abstract
		static String eatStatic() {			// public 
			return "eating in static m()";
		}
		default String eatingDefault() {	// public
			return "eating in default m()";
		}
	}
	
	class Animal implements HungryAnimal {
		public String eat() {
			System.out.println( "VALUE = " + VALUE );
			System.out.println( HungryAnimal.eatStatic() );
			System.out.println( this.eatingDefault() );
			return "Animals eats stuff.";
		}
	}
	
	class Horse extends Animal {

		class Pony {
			
			MicroPony microPony;
			
			class MicroPony {
				String name;
				MicroPony (String name) {
					this.name = "Mini" + name;
				}
			}
			
			String name;
			
			Pony(String name) { 
				this.name = name;
				this.microPony = new MicroPony(name);
			}
		}	
		
		Pony pony;
		
		Horse(){
			this.pony = new Pony("Penny");
		}
		
		@Override	// checks all overriding rules apply
		public String eat(  ) {
			return "Horse eats hay.";
		}
		
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		VirtualMethodsTest1 vmt1 = new VirtualMethodsTest1();
		//vmt1.test1();
		//vmt1.test2();
		vmt1.test3();

	}
	
	private void test1() {
		System.out.println( this.adder(1,2) );
		System.out.println( this.adder(1.0,2.0) );
		;
	}

	private int adder (int a, int b) {
		System.out.println("adder() int");
		return a+b;
	}
	
	private double adder (double a, double b) {
		System.out.println("adder() double");
		return a+b;
	}	
	
	private void test2() {
		Animal a = new Horse();
		System.out.println(a.eat());
	}
	
	private static void test3() {
		VirtualMethodsTest1.Animal animal = (new VirtualMethodsTest1()).new Horse();
		VirtualMethodsTest1.Horse.Pony pony = ((Horse)animal).pony;
		System.out.println("pony name = " + pony.name);
		
		VirtualMethodsTest1.Horse.Pony.MicroPony microPony = pony.microPony;
		System.out.println("microPony name = " + microPony.name + ", is a HungryAnimal = " + (microPony instanceof HungryAnimal) );
	}
	
}
