package pkg_3;
/** Represents a dog */
public class Dog implements Comparable<Dog> {
	
	private static int idSrc;

	private String name;
	private int id;

	/** ctor */
	public Dog(String name){
		this.name = name;
		this.id = ++idSrc;
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
	public int compareTo( Dog other ){
		return name.compareTo( other.getName() );
	}

	/** true: name, id */
	public boolean equals (Object o){
		if ( o == null || !( o instanceof Dog ) ){
			return false;
		}

		Dog other = (Dog)o;

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
