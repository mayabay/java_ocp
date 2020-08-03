package plants;
public class Hilo extends Pineapple  {

	/** No-arg constructor */
	public Hilo(){
		super("Hilo");
	}

	/** constructor */
	public Hilo(String name){
		super(name);
	}

	public double getWeight(){
		return 1;
	}

	public double getVolume(){
		return -1;
	}
}
