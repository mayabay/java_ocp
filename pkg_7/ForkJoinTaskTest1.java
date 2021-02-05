/**
 * KB 11
 */
package pkg_7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.OptionalDouble;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import pkg_4.ManuSNPTest1;
import pkg_4.ManuSNPTest1.SNP;
import pkg_7.ThreadTest1.Event;

/**
 * @author andreas
 *
 */
public class ForkJoinTaskTest1 {

	// events added by different threads
	Queue<ThreadEvent> log = new ConcurrentLinkedQueue<>(); 
	
	// what we look for
	private static final String CHROMOSOME = "17";
	private static final String GENO_TYPE = "AA";
	
	// the total data we get at beginning
	private List<pkg_4.ManuSNPTest1.SNP> list;
	
	// the result queue of Doubles from each thread
	Queue<Double> result = new ConcurrentLinkedQueue<>();
	
	/* out workload
	 * mean position value for genotype AA on chromosome 17
	 * */
	class Workload1 extends RecursiveAction {
		
		Predicate<SNP> isChr = snp -> snp.getChromosome().equals(CHROMOSOME);
		Predicate<SNP> isGenoType = snp -> snp.getGenotype().equals(GENO_TYPE);
		
		private int begin = 0;
		private int end = 0;
		private int THRESHOLD = 250_000;
		
		Workload1( int begin, int end ){
			this.begin = begin;
			this.end = end;
			String name = Thread.currentThread().getName();
			System.out.println("\t\t "+name+" begin = " + begin);
			System.out.println("\t\t "+name+" end = " + end);
		}
		
		@Override
		public void compute() {

			if ( end - begin <= THRESHOLD ) {
				// (1) base case	
				// our part of work
				List<SNP> internal = new ArrayList<>();
				for ( int i = begin; i <= end; i++ ) {
					internal.add( list.get(i) );
				}
				double avg =
				internal.stream()
				//.filter( isChr.and(isGenoType) )	// do not filter
				.collect( Collectors.averagingDouble( snp -> Double.valueOf(snp.getPosition()) ) );
				System.out.println("\t " + avg);
				result.offer( Double.valueOf(avg) );
			}
			else {
				// (2) recursive case
				// fin middle
				int middle = begin + ( (end - begin) / 2);
				// left wl
				Workload1 w1 = new Workload1(begin, middle-1);
				// right wl
				Workload1 w2 = new Workload1(middle, end);
				// put in queue
				ForkJoinTask.invokeAll(w1,w2);
			}
		}
	}
	
	private void printLog( Queue<ThreadEvent> log ) {
		int initialSize = log.size(); 
		for( int i = 0; i < initialSize; i++ ) {
			System.out.println(log.poll());
		}
	}		
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ForkJoinTaskTest1 fjtt1 = new ForkJoinTaskTest1();
		
		fjtt1.log.offer( new ThreadEvent("started") );
		
		// (1) get list of SNP
		pkg_4.ManuSNPTest1 msnpt1 = new ManuSNPTest1();
		fjtt1.list = msnpt1.getManusSNPList( true );	// true use test data
		
		fjtt1.log.offer( new ThreadEvent("got SNP list (entries = "+fjtt1.list.size()+"), now start ForkJoinTask") );
		
		// (2) build ForkJoinPool
		ForkJoinPool fjp = new ForkJoinPool();
		
		// (3) submit workload to task
		//ForkJoinTask<Void> task = 
				fjp.invoke( fjtt1.new Workload1(0, fjtt1.list.size()-1 ) );
		
		// (4) result should be finished
		//task.join();
		
		fjtt1.log.offer( new ThreadEvent("task finished, result queue delivered with entries: " + fjtt1.result.size()) );
		
		OptionalDouble optResult =
		fjtt1.result.stream()
			.mapToDouble( d -> d.doubleValue() )
		.average();
		
		fjtt1.log.offer( new ThreadEvent("final result = " + optResult.getAsDouble() ) );
		
		fjtt1.printLog(fjtt1.log);
	}

}
