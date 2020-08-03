package math;
/**
 * Fraction 
 *
 * */
public class Fraction {

	// numerator
	private int num;
	// denominator
	private int denom;

	/**
	 * ceates Fraction with numerator and denominator
	 * @param num - numerator
	 * @param denom - denominator
	 * **/
	Fraction (int num, int denom){
		if (denom == 0) throw new IllegalArgumentException("denom cannot be 0");
		this.num = num;
		this.denom = denom;
	}

	/**
	 * returns numerator
	 * */
	public int getNum(){
		return num;
	}

	/**
	 * returns denominator
	 * */
	public int getDenom(){
		return denom;
	}

	/**
	 * raises fraction
	 * @param number - by which fraction will be raised
	 * */
	public Fraction raise( int number ){
		this.num *= number;
		this.denom *= number;
		return this;
	}

	/**
	 * reduces fraction
	 * @param number - by which fraction will be reduced
	 * */
	public Fraction reduce(int number){
		this.num /= number;
		this.denom /= number;
		return this;
	}

	// shortens the fraction if possible
	private static Fraction shorten(Fraction fraction){
		if (fraction.getNum() > fraction.getDenom() ){
			return null;
		}else{
			return fraction;
		}
	}

	/**
	 * @return - fraction as String
	 * */
	public String toString(){
		return this.num + "/" + this.denom;
	}
}
