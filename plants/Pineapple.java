package plants;
/** Pineapple, an ananas fruit */
public class Pineapple extends Ananas {

	/** No-arg constructor */
	public Pineapple(){
		super("Pineapple");
	}

	/** constructor */
	public Pineapple(String name){
		super(name);
	}

	public double getWeight(){
		return 1;
	}

	public double getVolume(){
		return -1;
	}


}
