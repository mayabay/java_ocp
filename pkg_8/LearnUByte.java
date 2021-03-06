/**
 * Esser 11.10
 */
package pkg_8;

/**
 * byte vs ubyte
 *
 */
public class LearnUByte {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 byte b = 78;
		 
		 int step1 = (b >>> 4) & 0xF;
		 System.out.println("step0 = " + (b >>>4));
		 System.out.println("step1 = " +  step1 );
		 System.out.println("step2 = " +  step1 * 16 );
		 
		 int ub = ((b >>> 4) & 0xF) * 16 + (b & 0xF); 
		 
		 System.out.println("");

		 System.out.println("b = " + b + ", ub = " + ub);
	}

}
