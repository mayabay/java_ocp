package pkg_1;
class HeavyAnimal implements CanEatMuch  {}
class Hippo extends HeavyAnimal {}
class Elephant extends HeavyAnimal {}
interface CanEatMuch {}
interface CanFly {}
class InstanceOfTest {

	public static void main(String[] args){	
	    
        Hippo hippo1 = new Hippo();
        
        Elephant ele1 = new Elephant();

        HeavyAnimal ha1 = new Hippo();

        System.out.println( "hippo instanceof Hippo ->  " + ( hippo1 instanceof Hippo  )  );

        System.out.println( "hippo instanceof HeavyAnimal ->  " + ( hippo1 instanceof HeavyAnimal  )  );
    
        System.out.println( "hippo instanceof CanEatMuch ->  " + ( hippo1 instanceof CanEatMuch  )  );
    
        System.out.println( "ha1 instanceof Hippo ->  " + ( hippo1 instanceof Hippo )  );

        HeavyAnimal ha2 = new Elephant();
        
        ha2 = null;

        System.out.println( "ha2 (null)  instanceof Elephant ->  " + ( ha2 instanceof Elephant )  );    // false

        System.out.println( "null  instanceof Elephant ->  " + ( null instanceof Elephant )  ); // false

         // B is a class + B is anrelated to a --> DNC   

        // System.out.println( "unrelated operands  " + ( ele1 instanceof Hippo  )  );
            // DNC 32: error: incompatible types: Elephant cannot be converted to Hippo
        Elephant ele2 = new Elephant();

        // B is an interface + B is unrelated to a --> OK, maybe at runtime type of a will implement B

        System.out.println( "ele2 instanceof CanFly ->  " + ( ele2 instanceof CanFly )  );


    }	
}
