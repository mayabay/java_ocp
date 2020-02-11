package pkg_2;
public class Animal {
	private String species;
	private boolean canHop;
	private boolean canSwim;

	public Animal( String species, boolean hopper, boolean swimmer ){
		this.species = species;
		this.canHop = hopper;
		this.canSwim = swimmer;
	}

	public boolean canHop(){
		return this.canHop;
	}

	public boolean canSwim(){
		return this.canSwim;
	}

	public String toString(){
		return species;
	}


}
