package pkg_4;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Supplier;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Learn Primitive Streams
 * 
 * */
public class PrimitiveStreams {

	private static int idSrc;
	
	private class Employee{
		
		private int id;
		private String name;
		private long salary;
		
		private Employee() {
			id = ++idSrc;
			double dice = Math.random();
			name = dice > 0.5 ? "JohnDoe" : "JenniferSmith";
			name += id; 
			salary = Math.round(dice * 100_000);
		}

		private int getId() {
			return id;
		}

		private void setId(int id) {
			this.id = id;
		}

		private String getName() {
			return name;
		}

		private void setName(String name) {
			this.name = name;
		}

		private long getSalary() {
			return salary;
		}

		private void setSalary(long salary) {
			this.salary = salary;
		}

		@Override
		public String toString() {
			return "Employee [id=" + id + ", name=" + name + ", salary=" + salary + "]";
		}
		
	}
	
	/**
	 * main()
	 * */
	public static void main(String[] args) {
		PrimitiveStreams ps = new PrimitiveStreams();
		//ps.go();
		ps.listSalary();
		//ps.listRandomInts();

	}

	private Supplier<Employee> employeeSupplier = Employee::new;
	
	private void go() {
		
		DoubleStream dStream = DoubleStream.iterate(0.00, d -> (d + 0.1) * 10 );
		
		Set<Integer> set = dStream
				
				.peek(System.out::println)
				.limit(6)
				.mapToInt(d -> (int)d )
				.collect(HashSet::new, HashSet::add, HashSet::addAll);
				
		System.out.println( new TreeSet(set) );	// [0, 1, 11, 111, 1111, 11111]
	}
	
	private void listSalary() {
		Stream<Employee> estream = Stream.generate(employeeSupplier);
		
		/*
		estream
			.limit(12)
			.forEach(System.out::println);
		*/
		
		estream
			.peek(System.out::println)
			.limit(12)
			.mapToLong(e -> e.getSalary())
			.forEach(System.out::println);
	}
	
	private void listRandomInts() {
		Random random = new Random();
		IntStream ints = random.ints();
		
		ints.limit(16).map( Math::abs ).forEach(System.out::println);
		
	}
}
