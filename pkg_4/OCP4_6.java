package pkg_4;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import util.Employee;
import util.NamesDatabase;

public class OCP4_6 {

	private static int idSrc;

	public static void main(String[] args) {
		OCP4_6 o = new OCP4_6();
		//o.learnLazyEval();
		//o.chainOptional(Optional.of(142));
		// o.remapOptional();
		// o.joinLastNames();
		//o.averageSalary();
		//o.collectToSet();
		//o.collectToList();
		//o.summararizeDouble();
		//o.collectToMap();
		//o.collectToMap2();
		//o.collectToOldest();
		//o.groupingBy1();
		//o.groupingBy2();
		//o.partitioningBy();
		o.partitioningBy2();
		
	}

	private Stream catStream() {
		return Stream.of( "Annie", "Ripley" );
	}
	
	private void learnLazyEval() {
		List<String> cats = new ArrayList<>();
		cats.add("Annie");
		cats.add("Ripley");
		
		Stream<String> catStream = cats.stream();
		
		cats.add("Bonnie");
		
		Set set = catStream
			.peek(System.out::println)
			.collect( TreeSet::new, TreeSet::add, TreeSet::addAll  );
		
		System.out.println(set);
	}
	
	private void chainOptional( Optional<Integer> opt ){
//		if( opt.isPresent() )  {
//			Integer num = opt.get();
//		}
		
		opt	.map( t -> "" + t )
			.filter( t -> t.length() >= 3 )
			.ifPresent( System.out::println );
		
	}
	
	private void remapOptional() {
		Optional<String> opt = Optional.of("Bert");
//		opt	.map( OCP4_6::calculator )
//			.ifPresent(System.out::println);	// Optional[4]
		
		opt	.flatMap( OCP4_6::calculator )
		.ifPresent(System.out::println);		// 4		
	}
	
	private static Optional<Integer> calculator( String s ){
		return Optional.of(s.length());
	}

	private void joinLastNames() {
		
		Stream<Employee> empStream =  Stream.generate( NamesDatabase.getInstance().getEmpliyeeSupplier() );
		
		String str = empStream
			.limit(4)
			.flatMap(e -> { return Stream.of(e.getLastName()); } )
			.collect( Collectors.joining(",") );
		
		System.out.println( "last names = " + str );
		
	}
	
	private void averageSalary() {
		
		Stream<Employee> empStream =  Stream.generate( NamesDatabase.getInstance().getEmpliyeeSupplier() );
		
		Double avgSalary =  empStream
					.limit(32)
					.collect( Collectors.averagingDouble(Employee::getSalary) );
		
		System.out.println("average salary = " + avgSalary);
		System.out.format("average salary formatted: %1$.2f", avgSalary);
	}
	
	private void collectToSet() {
		
		Stream<Employee> empStream =  Stream.generate( NamesDatabase.getInstance().getEmpliyeeSupplier() );
		TreeSet<Employee> set = empStream 
				.limit(32)
				.collect(Collectors.toCollection( TreeSet::new ));
		
		for( Employee e : set ) {
			System.out.println(e);	
		}
		
	}
	
	private void collectToList() {
		Stream<Employee> empStream =  Stream.generate( NamesDatabase.getInstance().getEmpliyeeSupplier() );
		List<Employee> emps = empStream
			.limit(16)
			.collect(Collectors.toList());
		
		for( Employee e : emps ) {
			System.out.println(e);
		}
	}
	
	private void summararizeDouble() {
		Stream<Employee> empStream =  Stream.generate( NamesDatabase.getInstance().getEmpliyeeSupplier() );
		DoubleSummaryStatistics stats = empStream
		.limit(32)
		.collect(Collectors.summarizingDouble(Employee::getSalary));
		
		System.out.println("salary statistics: \n" + stats);
		
	}
	
	private void collectToMap() {
		
		Stream<Employee> empStream =  Stream.generate( NamesDatabase.getInstance().getEmpliyeeSupplier() );
		
		Map<String, LocalDate> map = empStream.limit(6).collect(Collectors. toMap(Employee::getLastName, Employee::getBirthDate));
		
		System.out.println(map);
		
	}
	
	private void collectToMap2() {
		Stream<String> stream = Stream.of("lions", "tigers", "bears");
		
		// keyMapper, valueMapper, mergeFunction (when two keys are same)
		Map<Integer, String> map = stream.collect(Collectors.toMap( String::length, Function.identity(), (s1,s2) -> { return s1 + ", " + s2; } ) );
		
		System.out.println(map);	// {5=lions, bears, 6=tigers}
	}
	
	private void collectToOldest() {
		
		Stream<Employee> empStream =  Stream.generate( NamesDatabase.getInstance().getEmpliyeeSupplier() );
		
		Comparator<Employee> comp = (e1,e2) -> {
			return e1.getBirthDate().compareTo(e2.getBirthDate());
		};
		
		Optional<Employee> opt = 	empStream
									.limit(6)
									.peek(System.out::println)
									.collect(Collectors.minBy(comp));
		
		System.out.println("---");
		opt.ifPresent(System.out::println);	// maxBy -> youngest if used with LocalDate, oldest by minBy
		
	}
	
	
	private void groupingBy1() {
		
		Stream<Employee> empStream =  Stream.generate( NamesDatabase.getInstance().getEmpliyeeSupplier() );
		
		Map<String, List<Employee>> map = empStream.limit(8).collect( Collectors.groupingBy( Employee::getDepartment ) );
		
		System.out.println(map);
	}

	private void groupingBy2() {
		
		Stream<Employee> empStream =  Stream.generate( NamesDatabase.getInstance().getEmpliyeeSupplier() );
		
		Map<String, Set<Employee>> map = empStream.limit(8).collect( Collectors.groupingBy( Employee::getDepartment, Collectors.toSet() ) );
		
		System.out.println(map);
	}	
	
	private void partitioningBy() {
		
		Stream<Employee> empStream =  Stream.generate( NamesDatabase.getInstance().getEmpliyeeSupplier() );
		
		Map<Boolean, List<Employee>> map = empStream.limit(32).collect(Collectors.partitioningBy(e-> e.getSalary() > 40000 ));
		
		System.out.println(map);
	}
	
	private void partitioningBy2() {
		
		Stream<Employee> empStream =  Stream.generate( NamesDatabase.getInstance().getEmpliyeeSupplier() );
		
		Map<Boolean, Set<Employee>> map = empStream.limit(32).collect(Collectors.partitioningBy(e-> e.getSalary() > 40000, Collectors.toSet() ) );
		
		System.out.println(map);
	}
	
}
