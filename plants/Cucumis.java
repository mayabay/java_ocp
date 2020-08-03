package plants;
/**
 * A plant of genus cucumis.
 * */
public abstract class Cucumis extends Plant implements Sweet {
	
	private static String descr = "Cucumis is a genus of twining, tendril-bearing plants in the family Cucurbitaceae.";

	/** No-arg constructor */
	public Cucumis(){}

	/** Constructor */
	public Cucumis(String name){
		super(name);
	}

	/**
	 * Return description of plant genus
	 * @return description
	 * */
	public String getDescr (){ return descr; }

}

