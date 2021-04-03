/**
 * 
 */
package pkg_1.cat;

/**
 * learn protected access
 *
 */
public class Tom {

	public final String name = "Tom";
	protected double weightInGramm =  42.345D;
	int ageInYears = 42;
	private String eyeColor = "green";
	
	protected String getEyeColor() {
		return this.eyeColor;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	}

}
