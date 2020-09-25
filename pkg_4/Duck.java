package pkg_4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Duck implements Comparable<Duck> {

	private String name;
	private String color;
	private int age;

	public static Comparator<Duck> byName = Comparator.comparing( Duck::getName );
	public static Comparator<Duck> byColor = Comparator.comparing( Duck::getColor );
	public static Comparator<Duck> byAge = Comparator.comparing( Duck::getAge );
	
	public Duck(String name, String color, int age) {
			super();
			this.name = name;
			this.color = color;
			this.age = age;
		}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public int compareTo(Duck other) {
		return this.getName().compareTo(other.getName());
	}

	@Override
	public String toString() {
		return "Duck [name=" + name + ", color=" + color + ", age=" + age + "]";
	}	
	
	// -------------------------------------------------------------------------
	


	public static void main(String[] args) {
		getDucks().stream()
			.sorted()
			.forEach( System.out::println );
		
		System.out.println("---");
		
		getDucks().stream()
		.sorted( byAge.reversed() )
		.forEach( System.out::println );		

		
		List<Duck> ducks = getDucks();
		ducks.add(new Duck ( "Jerry","mottled",10 ));
		ducks.add(new Duck ( "Goerge","white",12 ));
		ducks.add(new Duck ( "Kramer","brown",11 ));
		ducks.add(new Duck ( "Elaine","brown",13 ));
		
		
	}

	private static List<Duck> getDucks(){
		List<Duck> ducks = new ArrayList<>();		
		ducks.add(new Duck("Jerry", "yelllow", 3));
		ducks.add(new Duck("George", "brown", 4));
		ducks.add(new Duck("Kramer", "mottled", 6));
		ducks.add(new Duck("Elaine", "white", 2));
		
		return ducks;
	}
	
}
