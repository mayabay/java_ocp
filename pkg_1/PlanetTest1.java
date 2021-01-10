/**
 * Enums OCP BS 1
 */
package pkg_1;

/**
 * @author andreas
 *
 */
public class PlanetTest1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PlanetTest1 pt1 = new PlanetTest1();
		pt1.test1();

	}
	
	private void test1() {
		
		for( Planets planets : Planets.values() ) {
			System.out.printf( "planet: %1$s (%2$s) %n", planets, planets.getInfo() );
		}
		
	}

}
