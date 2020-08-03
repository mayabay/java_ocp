package plants;
public class Lime extends Citrus {

	/** No-arg constructor */
	public Lime(){
		super("Lime");
	}

	/** constructor */
	public Lime(String name){
		super(name);
	}

	public double getWeight(){
		return 0.067;
	}

	public double getVolume(){
		return -1;
	}

}
