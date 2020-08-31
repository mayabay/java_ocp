package plants;
/**
 * A plant of genus Musa.
 * */
public abstract class Musa extends Plant {
	
	private static String descr = "Musa is one of two or three genera in the family Musaceae; it includes bananas and plantains.";

	/** No-arg constructor */
	public Musa(){}

	/** Constructor */
	public Musa(String name){
		super(name);
	}

	/**
	 * Return description of plant genus
	 * @return description
	 * */
	public String getDescr (){ return descr; }

}

