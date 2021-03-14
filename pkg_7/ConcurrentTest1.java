/**
 * review bs Ch7 questions
 */
package pkg_7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Collectors;

/**
 *
 */
public class ConcurrentTest1 {

	private AtomicLong value1 = new AtomicLong(0);
	private long[] value2 = new long[1];
	// new long[] {0};
	// {0};
	{ value2[0] = 0; }

	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws InterruptedException {
		ConcurrentTest1 cc1 = new ConcurrentTest1();
		//cc1.test1();
		//cc1.test2();
		//cc1.test3();
		//cc1.test4();
		//cc1.test5();
		cc1.test6();
	}

	private void test1() throws InterruptedException {
		
		ExecutorService service = null;
		try {
			service = Executors.newSingleThreadExecutor();
			service.submit( () -> System.out.println("Open Zoo") );
		}finally {
			service.shutdown();
		}
		
		ScheduledExecutorService ses = null;
		try {
			ses = Executors.newScheduledThreadPool(2);
			// one time 
			ses.schedule( () -> { System.out.println("hello"); } , 1000 , TimeUnit.MILLISECONDS);
			// schedlued
			ses.scheduleAtFixedRate(() -> { System.out.println("scheduled hi"); }, 1000, 2000, TimeUnit.MILLISECONDS);
			
			Thread.currentThread().sleep(10_000);
			
			System.out.println("main awake again!");
			
		}finally {
			ses.shutdown();
		}
	}
	
	private void test2() {
			
		IntStream.iterate( 1, i -> 1 )
		.limit(100)
		.parallel()
		.forEach(i -> value1.incrementAndGet());
		
		IntStream.iterate( 1, i -> 1 )
		.limit(100)
		.parallel()
		.forEach(i -> ++value2[0] );
		
		System.out.println(value1 + " : " + value2[0]);
		// 100 : 100
		// 100 : 98
		// 100 : 97
	}
	
	private void test3() {
		
		System.out.println("iterate with pre increment");
		
		Long val =
		LongStream.iterate(1 , n -> ++n)
		.limit(4)
		.peek(System.out::println) // 1 2 3 4
		.sum(); 
		
		System.out.println("---");

		System.out.println("iterate with post increment");
		Long val2 =
		LongStream.iterate(1 , n -> n++)
		.limit(4)
		.peek(System.out::println) // 1 2 3 4
		.sum(); 		
		
		System.out.println("---");		
	}
	
	private void test4() {
		List<Integer> l1 = Arrays.asList(1,2,3);
		List<Integer> l2 = new CopyOnWriteArrayList<>(l1);
		Set<Integer> s3 = new ConcurrentSkipListSet<>(); 
		s3.addAll(l1);
		
		//for(Integer item : l1) l1.add(6); 
			// RTE UnsupportedOperationException
		
		// adding while iterating
		for( Integer item : l2 ) l2.add(4);
		for( Integer item : s3 ) s3.add(5);
		
		System.out.println(l1.size() +  " : " + l2.size() + " : " + s3.size());
		System.out.println("List l1 " + l1);
		System.out.println("List l2 " + l2);
		System.out.println("Set s3 " + s3);
		
		this.test5();
	}
	
	private void test5() { 
		List<Integer> l1 = new ArrayList<>();
		l1.add(1);l1.add(2);l1.add(3);
		
		List<Integer> l2 = Collections.synchronizedList(l1);
		for ( Integer item : l2 ) {
			//l2.add(7);
			// RTE java.util.ConcurrentModificationException
		}
	}	
	
	private void test6() {
		Integer i1 = Arrays.asList(1,2,3,4,5).stream().findAny().get();
		synchronized(i1) {
			Integer i2 = Arrays.asList(6,7,8,9,10)
					.parallelStream()
					.sorted()
					.findAny()
					.get();
			
			System.out.println(i1 + " : " + i2);
		}
	}
	
}
