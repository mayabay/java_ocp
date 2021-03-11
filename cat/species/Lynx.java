package cat.species;

import cat.BigCat;

public class Lynx extends BigCat {

	static {
		
		i = 10; 
		//i++;						// DNC Cannot reference a field before it is defined
		//System.out.println(i);
	}
	
	static int i;
	
	public static void main(String[] args) {
		BigCat bc = new BigCat();
		System.out.println( bc.name );
		//System.out.println( bc.hasFur ); // DNC The field BigCat.hasFur is not visible

		BigCat bc2 = new Lynx();
		System.out.println( "hasFur =  " +   ((Lynx)bc2).hasFur  ); 
		
	}

}
