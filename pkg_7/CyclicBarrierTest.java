/**
 * KB 11.3.4
 */
package pkg_7;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Learn CyclicBarrier
 */
public class CyclicBarrierTest {

	// final result list
	List<String> result = new ArrayList<>();
	// data set 1
	static String[] dogs1 = { "boi", "clover", "charis" };
	// data set 2
	static String[] dogs2 = { "aiko", "zooey", "biscuit" };
	// the thread barrier
	final CyclicBarrier barrier;
	
	// the runnable for each thread
	class ProcessDogs implements Runnable {
		
		String[] dogs;
		
		ProcessDogs( String[] d ){
			this.dogs = d;
		}
		
		// uppercase first char in dogname in data set
		public void run() {
			// #1 work on datas set
			for(int i = 0; i < dogs.length; i++) {
				String dogName = dogs[i];
				String newDogName = dogName.substring(0,1).toUpperCase()
						+ dogName.substring(1);
				dogs[i] = newDogName;
			}
			// #2 wait at barrier
			try{
				barrier.await();
			}catch ( InterruptedException | BrokenBarrierException e ) {
				e.printStackTrace();
			}
			// #3 barrier job is done
			System.out.println( Thread.currentThread().getName() +  " is done!");
		}
	}
	
	public CyclicBarrierTest(){
		// #1 set the barrier action
		barrier = new CyclicBarrier(2,  () -> {
			// add dogs from thread 1 to result set
			for( int i = 0; i < dogs1.length; i++ ) {
				result.add(dogs1[i]);
			}
			// add dogs from thread 2 to result set
			for( int i = 0; i < dogs2.length; i++ ) {
				result.add(dogs2[i]);
			}	
			// print result
			System.out.println( Thread.currentThread().getName() +  " result : " + result);
		});
		// #2 create the threads
		Thread t1 = new Thread(new ProcessDogs(dogs1));
		Thread t2 = new Thread(new ProcessDogs(dogs2));
		// #3 start them
		t1.start();
		t2.start();
		System.out.println("main is done.");
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CyclicBarrierTest cbt = new CyclicBarrierTest();
	}

}
