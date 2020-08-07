package pkg_3;
/**
 * Simple address 
 * */
public class Address implements Comparable<Address> {
	
	private static int idSrc = 0;

	private int id;
	private String firstName;
	private String lastName;
	private String city;

	/** ctor */
	public Address(){
		this("xx","yy","zz");
	}

	/** ctor */
	public Address( String firstName, String lastName, String city ){
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		id = ++idSrc;
	}	

	/** getter */
	public int getId(){
		return id;
	}

	/** getter */
	public String getFirstName(){
		return firstName;
	}

	/** getter */
	public String getLastName(){
		return lastName;
	}

	/** getter */
	public String getCity(){
		return city;
	}

	/** @return String representation */
	public String toString(){
		return "[" + firstName + ", " + lastName + ", from "+ city +" ("+id+")]";
	}

	/** @return hash */
	public int hashCode(){
		return 3 * (firstName.hashCode() + lastName.hashCode());
	}

	/** @return true, if same names */
	public boolean equals( Object o ){
 		if ( (o != null) && ( o instanceof Address ) ){
			Address other = (Address)o;
			if ( firstName.equals(other.getFirstName()) ){
				if ( firstName.equals(other.getLastName()) ){
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Compares this address to another address. Both addresses are same, if first and lastName are same
	 * Otherwise lastName is used
	 * @return number greater 0, if this lastName precedes other last name 
	 * */
	public int compareTo( Address addr ){
		boolean haveEqualNames = false;
		if ( this.firstName.equals(addr.getFirstName()) && this.lastName.equals( addr.getLastName() ) ){
			return 0;
		}else{
			return this.lastName.compareTo( addr.getLastName() );
		}
	}

}
