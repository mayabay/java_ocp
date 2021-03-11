/**
 * https://en.wikipedia.org/wiki/Lynx
 * 
 * */
package cat.species;
import cat.BigCat;

public class CanadaLynx extends Lynx {
	public static void main( String[] args ) {
		//BigCat bc = new Lynx();
		BigCat bc = new CanadaLynx();
		//System.out.println( bc.hasFur );	// DNC has prot
		//System.out.println( ((Lynx)bc).hasFur );	// DNC has prot acc
		Lynx ly = ((Lynx)bc);
		System.out.println( ((CanadaLynx)ly).hasFur );
	
	}
}
