/**
 * 
 */
package pkg_1.mouse;

/**
 * 
 *
 */
public class JerrysSon extends Jerry {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JerrysSon js = new JerrysSon();
		System.out.println("protecetd " + js.weightInGramm);
		
		Jerry jerry = (JerrysSon)js;
		//System.out.println("protecetd " + jerry.weightInGramm);
			// DNC not visible thru superclass
	}

}
