/**
 *  review bs ch 7
 */
package pkg_7;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveAction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 */
public class ConcurrentTest3 {

	// ForkJoinTasks as inner classes cannot be used with the ForkJoinFramework
	public class CountNumbers extends RecursiveAction {
		int start, end;
		public CountNumbers( int start, int end ) {
			this.start = start;
			this.end = end;
		}
		
		@Override
		protected void compute() {
			if ( start < 0  )
				return;
			else {
				int middle = start + ((end-start) / 2);
				invokeAll( new CountNumbers(start, middle), new CountNumbers(middle, end) );
			}
		}
	}
	
	/**
	 * @param args
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ConcurrentTest3 cct3 = new ConcurrentTest3();
		//cct3.test1();
		//cct3.test2();
		//cct3.test3();
		cct3.test4();
	}

	private void test1() {
		
		Stream<String> s =  Arrays.asList("duck","chicken","flamingo","pelikan")
				.parallelStream()
				.parallel();
		int totalCharsInStream = s
				.peek(System.out::println)
				.reduce( 0, (i, s2) -> i + s2.length(), (i1,i2) -> i1+i2 );
		
		System.out.println( "totalCharsInStream : " + totalCharsInStream );
	}
	
	private void test2() throws InterruptedException, ExecutionException {
		Object o1 = new Object();
		Object o2 = new Object();
		ExecutorService service = Executors.newFixedThreadPool(2);
		Future<?> f1 = service.submit(
				() -> {
					synchronized(o1) {
						synchronized(o2) {
							System.out.println( "Tortoise" );
						}
					}
				}
			);
		Future<?> f2 = service.submit(
				() -> {
					synchronized(o2) {
						synchronized(o1) {
							System.out.println( "Hare" );
						}
					}
				}
			);		
		
		f1.get();
		f2.get();
		
	}
	
	private void test3() {
		ForkJoinTask<?> fjt = this.new CountNumbers(0, 4);
		ForkJoinPool fjp = new ForkJoinPool();
		Object r = fjp.invoke(fjt);
		
	}
	
	private void test4() {
		Stream<String> s1 = Stream.of("l","l","o","p").parallel();
		Stream<String> s2 = Stream.of("p","g","p").parallel();
		
		ConcurrentMap<Boolean,List<String>> map =
		Stream.of(s1,s2)
			.flatMap( s -> s )
			.collect(
						Collectors.groupingByConcurrent(s-> !s.startsWith("p"))
					);
		System.out.println("true : " + map.get(true).size() );
		System.out.println("false : " + map.get(false).size() );
	}

	Callable<Integer> c1 = () -> {return 10;};
	Callable<CharSequence> c2 = () -> "Hello";
	Callable<Integer> c3 = () -> 42;
	Callable<Void> c4 = () -> null;
	Callable<String> c5 = () -> "Foo" + "Bar";
	// Callable<Integer> c6 = ( int n ) -> n + 42; // DNC
	Callable<Integer> c7 = (  ) -> { System.out.println("42"); return 42; };
	
	private <V> V callMe( Callable<V> c ) { 
		try {
			return c.call();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		assert true : "you should not arrive here!";
		return null;
	}
	
	private void test5(  ) {
		
		ConcurrentTest3 me = this;
		String s1 = (String)me.<CharSequence>callMe(c2);
				// (String) <CharSequence>callMe(c2); // DNC
		System.out.println(s1);
		
	}
}