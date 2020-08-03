package plants;
/** A lemon is a citrus fruit */
public class Lemon extends Citrus implements Sour {

	/** No-arg constructor */
	public Lemon(){
		super("Lemon");
	}

	/** constructor */
	public Lemon(String name){
		super(name);
	}

	public double getWeight(){
		return 0.058;
	}

	public double getVolume(){
		return -1;
	}
}
