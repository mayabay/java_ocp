package pkg_4;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import util.Employee;
import util.NamesDatabase;

/**
 * Working with functional interfaces
 * OCP KB 8.2.3
 * 
 * */
public class WorkingWithFuncInt {

	private static Supplier<Integer> answerSupplier = () -> 42;
	
	private static Supplier<String> userSupplier = () -> System.getenv().get("USER");
	
	public static void main(String[] args) {
		//System.out.println("How did the universe begin? answer: " + answerSupplier.get());
		WorkingWithFuncInt wwfi = new WorkingWithFuncInt();
		wwfi.printEmployees();

	}

	private void printEnv() {
		System.out.println( "current user : " + userSupplier.get() );
		
		Map<String, String> map = System.getenv();
		
		Collection<String> values =  map.values();
		
		Set<String> keys = map.keySet();
		
		String s = keys.stream()
				.sorted()
				.peek(key -> { System.out.println( key + " : " + System.getenv().get(key) ); })
				.collect( Collectors.joining(";") );
		
		//values.stream().forEach(System.out::println);		
	}
	
	private void printEmployees() {
		Stream<Employee> empStream = Stream.generate( NamesDatabase.getInstance().getEmpliyeeSupplier() );
		
		List<Employee> list = empStream
			.limit(12)
			.peek( e -> {
				System.out.println(e);
				try {
					Thread.sleep(250);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			} )
			.collect( Collectors.toList() );
		
		
		//list.forEach(System.out::println);
		
	}
	
}
