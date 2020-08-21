package pkg_3;
/** Represents a cat */
public class Cat extends Animal implements Comparable<Cat> {
	
	private static int idSrc;

	private String name;
	private int id;

	/** ctor */
	public Cat(){
		this.id = ++idSrc;
		this.name = "unnamed cat " + id;
	}

	/** ctor */
	public Cat(String name){
		this.name = name;
		this.id = ++idSrc;
	}
	
	public String getSpecies(){
		return "Felis Catus";
	}

	/** getter */
	public String getName(){
		return name;
	}

	/** getter */
	public int getId(){
		return id;
	}

	/** Compares by name */
	public int compareTo( Cat other ){
		return name.compareTo( other.getName() );
	}

	/** true: name, id */
	public boolean equals (Object o){
		if ( o == null || !( o instanceof Cat ) ){
			return false;
		}

		Cat other = (Cat)o;

		if (this.name.equals( other.getName() )  ){
			if ( this.id == other.getId() ){
				return true;	
			}
		}

		return false;
	}

	/** name, id */
	public int hashCode(){
		int hc = 17;
		hc += ( 3 * id );
		hc += name.hashCode();
		return hc;
	}

	/** toString */
	public String toString(){
		return name + " ("+id+")";
	}

}
