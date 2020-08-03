package plants;
/**
 * A plant of genus citrus
 * */
public abstract class Citrus extends Plant {
	
	private static String descr = "Plants in the genus Citrus produce citrus fruits.";

	/** No-arg constructor */
	public Citrus(){}

	/** Constructor */
	public Citrus(String name){
		super(name);
	}

	/**
	 * Returns description of plant genus
	 * @return description
	 * */
	public String getDescr (){ return descr; }
}
