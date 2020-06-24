package food;
public abstract class Fruit{
	private String name;
	public String getName(){
		return name;
	}
	public Fruit(){
	}
	public Fruit (String name){
		this.name = name;
	}
	public abstract boolean isSweet();
	public String toString(){
		return name;
	}
	public int hashCode(){
		return name.hashCode();
	}
	public boolean equals(Object o){
		if ( o == null || !(o instanceof Fruit) ){
			return false;
		}
		Fruit f = (Fruit)o;
		return name.equals( f.name );
	}
}
