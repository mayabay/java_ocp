package pkg_1.cat.species;
import pkg_1.cat.BigCat;
public class Lynx extends BigCat {

	
	public static void main(String[] args){
		
		BigCat cat = new BigCat();
		
		// public
		System.out.println( cat.name  );
		
		// protected
			
		//System.out.println( cat.hasFur  );	// DNC 14: error: hasFur has protected access in BigCat

		// package private
		// System.out.println( cat.hasPaws  );	// DNC 18: error: hasPaws is not public in BigCat; cannot be accessed from outside package


		// access protected 
		Lynx lynx = new Lynx();
		System.out.println( lynx.hasFur  );		// OK

		BigCat bigCat = new Lynx();	
		//System.out.println( bigCat.hasFur  );	// DNC 26: error: hasFur has protected access in BigCat

		// ref type determines protected access!!!
		System.out.println( ((Lynx) bigCat).hasFur  );




	}
}
