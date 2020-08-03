package plants;
/**
 * A plant of genus ananas.
 * */
public abstract class Ananas extends Plant implements Sweet {
	
	private static String descr = "Plants in the genus ananas are native to south america.";

	/** No-arg constructor */
	public Ananas(){}

	/** Constructor */
	public Ananas(String name){
		super(name);
	}

	/**
	 * Returns description of plant genus
	 * @return description
	 * */
	public String getDescr (){ return descr; }


}


