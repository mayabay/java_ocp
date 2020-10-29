/**
 * BS 7.5.2
 */
package pkg_7;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class WhaleDataCalculator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		WhaleDataCalculator wdc = new WhaleDataCalculator();

		// define data
		List<Integer> data = new ArrayList<>();
		for( int i = 0; i < 4000; i++ ) {
			data.add(i);
		}
		
		// process data
		long start = System.currentTimeMillis();
		wdc.processAllData(data);
		double time = (System.currentTimeMillis() - start) / 1000;
		
		// report result
		System.out.println("\nTasks completed in " + time + " seconds");
	}

	public void processAllData( List<Integer> data ) {
		data.stream().map( i -> processRecord(i) ).count();	 // Tasks completed in 63.0 seconds
		//data.parallelStream().map( i -> processRecord(i) ).count();	 // 12.0 seconds
		
	}
	
	public int processRecord( int input ) {
		try {
			Thread.sleep(10);
		}catch( InterruptedException e ) {
			e.printStackTrace();
		}
		return input +1;
	}
	
}
