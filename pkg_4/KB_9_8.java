package pkg_4;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import sun.misc.Perf.GetPerfAction;

/**
 * Collecto values from stream
 * 
 * */
public class KB_9_8 {

	public class Person {
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
		
		
		
	}
	
	public static void main(String[] args) {
		KB_9_8 obj = new KB_9_8();	
		obj.collectByAge(  );
	}

	private void collectByAge() {
		List<Person> persons =
		getPersons().stream()
			.filter(p -> p.getAge() == 34)
			//.collect(Collectors.toList());
			.collect(Collectors.toCollection(ArrayList::new));
	System.out.println(persons);	
	}
	
	public List<Person> getPersons(){
		List<Person> persons = new ArrayList<>();
		
		persons.add( new Person("Berth",30) );
		persons.add( new Person("Eric",31) );
		persons.add( new Person("Deb",31) );
		persons.add( new Person("Liz",30) );
		persons.add( new Person("Wendi",34) );
		persons.add( new Person("Kathy",35) );
		persons.add( new Person("Bert",32) );
		persons.add( new Person("Bill",34) );
		persons.add( new Person("Robert",38) );
		
		return persons;
	}
	
}
