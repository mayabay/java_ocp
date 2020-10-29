/**
 * BS 7.2
 */
package pkg_7;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Andreas Mann (lokal)
 *
 */
public class LearnExecutors {

	private Callable<String> c1 = () -> { return "1"; };
	private Callable<String> c2 = () -> { return "2"; };
	private Callable<String> c3 = () -> { return "3"; };
	private Callable<String> c4 = () -> { return "4"; };
	
	private Callable<String> ca1 = () -> {
		System.out.println("job start");
		int count = 0;
		for ( int i = 0; i < 100; i++ ) {
			count++;
			System.out.println(Thread.currentThread().getName() +  " : " +  count);
			Thread me = Thread.currentThread();
			try {
				me.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//System.out.println(count);		
		System.out.println("job end");		
		return "finished";
	};
	
	private Runnable run1 = () -> {
		System.out.println("job start");
		int count = 0;
		for ( int i = 0; i < 100; i++ ) {
			count++;
			System.out.println(Thread.currentThread().getName() +  " : " +  count);
			Thread me = Thread.currentThread();
			try {
				me.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//System.out.println(count);		
		System.out.println("job end");		
	};
	
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		LearnExecutors le = new LearnExecutors();
		le.test3();

	}

	private void test3() throws InterruptedException {
		ExecutorService es = null; 
		
		try {
			es = Executors.newSingleThreadExecutor();
			Set<Callable<String>> toRun = new HashSet<>();
			toRun.addAll( Arrays.asList(ca1,ca1,ca1,ca1) );
			
			List<Future<String>> fus = es.invokeAll(toRun);			
		}finally {
			if (es != null)
				es.shutdown();
		}
	}
	
	private void test2() throws InterruptedException {
		
		ExecutorService es = Executors.newSingleThreadExecutor();
		try {
			
			Set<Callable<String>> toRun = new HashSet<>();
			toRun.addAll( Arrays.asList(c1,c2,c3,c4) );
			
			List<Future<String>> fus = es.invokeAll(toRun);
			
			fus.forEach(
					future -> {try {
						System.out.println( future.get() );
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (ExecutionException e) {
						e.printStackTrace();
					}}
					);
			
			
		}finally {
			es.shutdown();
		}
		
	}
	
	private void test1() {
	
		ExecutorService es = Executors.newSingleThreadExecutor();
		try {
			//es.execute(run1);
			//es.execute(run1);	
			
			Future<?> fu = es.submit(run1);
			
			if ( fu.isDone() )  
				System.out.println("finished");
			
		}finally {
			es.shutdown();	
		}
		
		
		
	}
	
}
