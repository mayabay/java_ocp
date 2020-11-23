/**
 * KB 11.3
 */
package pkg_7;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 *
 */
public class ArrayListRunnable implements Runnable {

	private List<Integer> list = new ArrayList<>();
	
	public ArrayListRunnable() {
		for ( int i = 0; i < 100_000; i++ ) {
			list.add(i);
		}
	}
	
	@Override
	public void run() {
		while( !list.isEmpty() ) {
			System.out.println(Thread.currentThread().getName() +  " removed " + list.remove(0));
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayListRunnable alr = new ArrayListRunnable();
		Thread t1 = new Thread( alr );
		Thread t2 = new Thread( alr );
		t1.start();
		t2.start();
	}

}
