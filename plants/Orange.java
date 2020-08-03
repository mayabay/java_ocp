package plants;
/**
 * Orange is a citrus fruit
 * */
public class Orange extends Citrus {

	/** No-arg constructor */
	public Orange(){
		super("Orange");
	}

	/** constructor */
	public Orange(String name){
		super(name);
	}

	public double getWeight(){
		return 0.120;
	}

	public double getVolume(){
		return 0.001;
	}

}
