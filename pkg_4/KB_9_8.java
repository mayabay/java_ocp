package pkg_4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Collecto values from stream
 * 
 * */
public class KB_9_8 {

	public class Person implements Comparable<Person> {
		private String name;
		private Integer age;
		
		/**
		 * no-arg ctor
		 * */
		public Person() {}
		
		/**
		 * @param name
		 * @param age
		 */
		public Person(String name, Integer age) {
			super();
			this.name = name;
			this.age = age;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @return the age
		 */
		public Integer getAge() {
			return age;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getEnclosingInstance().hashCode();
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (!(obj instanceof Person))
				return false;
			Person other = (Person) obj;
			if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			return true;
		}

		private KB_9_8 getEnclosingInstance() {
			return KB_9_8.this;
		}

		@Override
		public String toString() {
			return "Person [name=" + name + ", age=" + age + "]";
		}
		
		@Override
		public int compareTo(Person other) {
			return name.compareTo(other.getName());
		}
		
	}
	
	public static void main(String[] args) {
		KB_9_8 obj = new KB_9_8();	
		//obj.collectByAge(  );
		//obj.groupByAge();
		//obj.groupByAgeToName();
		//obj.groubByAgeToPersonCount();
		//obj.groupByNameAndSumAge();
		obj.groupByNameAndAvgAge();
	}

	private void groupByAge() {
		Map<Integer, List<Person>> map = 
				getPersons().stream()
				.collect( Collectors.groupingBy( Person::getAge ) );
				
		map.forEach( (i, p) -> {
			System.out.println("age: " + i.intValue());
			System.out.println(p);
			System.out.println("---");
		} );		
	}
	
	private void groupByAgeToName() {
		Map<Integer, List<String>> map = 
				getPersons().stream()
				.collect( Collectors.groupingBy( Person::getAge, Collectors.mapping(Person::getName, Collectors.toList()) ) );
				
		map.forEach( (i, s) -> {
			System.out.println("age: " + i.intValue());
			System.out.println(s);
			System.out.println("---");
		} );		
	}	
	
	private void groubByAgeToPersonCount() {
		Map<Integer , Long> map =
		getPersons().stream()
			.collect( Collectors.groupingBy(Person::getAge, Collectors.counting()) );
		
		map.forEach( (i, l) -> {
			System.out.print("age: " + i.intValue() + " | ");
			System.out.println(l + " people");
			//System.out.println("---");
		} );		
	}
	
	private void collectByAge() {
		List<Person> persons =
		getPersons().stream()
			.filter(p -> p.getAge() == 34)
			//.collect(Collectors.toList());
			.collect(Collectors.toCollection(ArrayList::new));
		System.out.println(persons);	
	}
	
	private void groupByNameAndSumAge() {
		Map<String, Integer> map =
		getPersons().stream()
		.filter(p -> p.getName().startsWith("B"))
			.collect( Collectors.groupingBy(Person::getName, Collectors.summingInt(Person::getAge) ) );
		
		map.forEach( (s, i) -> {
			System.out.println("name: " + s + ", sum age: " + i.intValue() + " | ");
			//System.out.println("---");
		} );			
	}
	
	private void groupByNameAndAvgAge() {
		Map<String, Double> map =
		getPersons().stream()
		.filter(p -> p.getName().startsWith("B"))
			.collect( Collectors.groupingBy(Person::getName, Collectors.averagingInt(Person::getAge)) );
		
		map.forEach( (s, d) -> {
			System.out.println("name: " + s + ", sum age: " + d.doubleValue() + " | ");
			//System.out.println("---");
		} );			
	}	
	
	public List<Person> getPersons(){
		List<Person> persons = new ArrayList<>();
		
		persons.add( new Person("Beth",30) );
		persons.add( new Person("Eric",31) );
		persons.add( new Person("Deb",31) );
		persons.add( new Person("Liz",30) );
		persons.add( new Person("Wendi",34) );
		persons.add( new Person("Kathy",35) );
		persons.add( new Person("Bert",32) );
		persons.add( new Person("Bill",34) );
		persons.add( new Person("Robert",38) );
		
		//
		persons.add( new Person("Bill",40) );
		persons.add( new Person("Beth",45) );
		persons.add( new Person("Bert",38) );
		
		Collections.sort(persons);
		
		return persons;
	}
	
}
