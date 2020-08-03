package plants;
/** 
 * A plant having weight and volume
 * */
public abstract class Plant {

	private static int num = 0;

	/* species */
	private String name = "Plant";

	/* id gives a plant object a unique identity */
	private final int id;

	{
		id = + ++num;
		//id = 42;
	}

	private static final String descr = "Mutlicellular organisms of the kingdom plantae.";
	
	/** No-arg constructor */
	public Plant(){}

	/** Constructor with name */
	public Plant(String name){
		this.name = name;
	}

	/**
	 * Returns name of spiecies 
	 * @return species
	 * */
	public String getName(){
		return name;
	}

	/**
	 * Returns unique identifier for this id
	 * @return 
	 * */
	public int getId(){
		return id;
	}

	/**
	 * Returns the weight of the plant in kg
	 * @return weight in kg
	 * */
	public abstract double getWeight();

	/**
	 * Returns the volume of the plant 
	 * @return volume in qubic meter; -1 if volume is not available
	 * */
	public abstract double getVolume();

	/**
	 *@return hashCode
	 * */
	public int hashCode(){
		return id;
	}

	/**
	 * Test for Equality 
	 * @return true if two plants are equal
	 * */
	public boolean equals (Object object){
		if ( (object != null) && (object instanceof Plant) ) {
			Plant other = (Plant)object;
			return this.getId() == other.getId();
		}
		return false;
	}

	/**
	 * Returns name of species
	 * @return species
	 * */
	public String toString(){
		return name + " ("+id+") ";
	}

}
