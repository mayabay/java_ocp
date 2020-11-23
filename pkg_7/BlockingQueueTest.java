package pkg_7;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Learn BlockingQueue
 * 
 * */
public class BlockingQueueTest {

	/**
	 * @param args
	 * */
	public static void main(String[] args) {
		BlockingQueueTest bqt = new BlockingQueueTest();
		bqt.test1();
	}
	
	private void test1() {
		
		BlockingQueue<Integer> bq = new ArrayBlockingQueue<>(1);
		try {
			bq.put(42); System.out.println("added 42");
			bq.put(43); System.out.println("added 43");
		} catch (InterruptedException e) {
			e.printStackTrace();
			return;
		}
		
		
	}
	
}
