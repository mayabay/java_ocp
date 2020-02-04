package pkg_1;
public class Hippo_1_5 {
	
	private String name;
	private double weight;
	
	public Hippo_1_5(String name, double weight){
		this.name = name;
		this.weight = weight;
	} 

	@Override
	public String toString(){
		return name;
	}

	public static void main(String[] args){
		Hippo_1_5 h1 = new Hippo_1_5("Harry", 3100);
		System.out.println(h1);
	}


}
