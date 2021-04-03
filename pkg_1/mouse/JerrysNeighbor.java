/**
 * 
 */
package pkg_1.mouse;

/**
 * 
 *
 */
public class JerrysNeighbor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JerrysNeighbor jn = new JerrysNeighbor();
		Jerry jerry = new Jerry();
		jn.test1( jerry );
	}

	private void test1( Jerry jerry ) {
		System.out.println( "public : " +  jerry.name);
		//System.out.println( "protected : " +  jerry.weightInGramm);
			// DNC field is not visible to neightbor
	}
	
}
