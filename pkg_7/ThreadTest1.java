/**
 * KB 11
 */
package pkg_7;

import java.time.Instant;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author andreas
 *
 */
public class ThreadTest1 implements Runnable {

	class Event {
		Instant instant;
		String message;
		Event(Instant instant, String message) {
			super();
			this.instant = instant;
			this.message = message;
		}
		Event(String str){
			this(Instant.now(),str);
		}
		@Override
		public String toString() {
			return "[Event: "+instant+" : "+message+"]";
		}
	}
	
	Queue<Event> log = new ConcurrentLinkedQueue<>(); 
	
	@Override
	public void run() {
		log.offer( new Event(Instant.now(), "ThreadTest1 run() called") );
		this.workload1.run();
	}
	
	Runnable workload1 = () -> {
		Thread t = Thread.currentThread();
		System.out.println("Thread : " + t.getName());
		
		try {
			t.sleep(2000);
		}catch( InterruptedException e ) {
			e.printStackTrace();
		}
		
	};
	
	Runnable workload2 = () -> {
		int n = 0;
		for ( int i = 0; i < 1_000_000; i++ ) {
			n += i;
		}
		this.log.add( new ThreadTest1.Event(Instant.now(),"" + Thread.currentThread().getName() + " n = " + n ) );
	};
	
	class SpecialThread extends Thread {
		
		SpecialThread( String name ){
			super(name);
		}
		
		@Override
		public void run() {
			workload2.run();
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ThreadTest1 tt1 = new ThreadTest1();
		//tt1.test1();
		tt1.test2();
	}

	private void pause(long millis) {
		try {
			Thread.currentThread().sleep(millis);
		}catch( InterruptedException e ) {
			e.printStackTrace();
		}
	}
	
	private void printLog( Queue<Event> log ) {
		int initialSize = log.size(); 
		for( int i = 0; i < initialSize; i++ ) {
			System.out.println(log.poll());
		}
	}	
	
	private void test1() {
		log.offer( new Event(Instant.now(), "test1 started") );
		Thread t1 = new Thread( () -> System.out.println( Thread.currentThread().getName() ) );
		t1.run();	// will be the main thread!
		System.out.println("t1 = " +  t1.getState() );
		
		Thread t2 = new Thread(this);
		t2.start();
		//this.pause(500); w/o RUNNABLE
		System.out.println("t2 = " +  t2.getState() );
		// with pause TIMED_WAITING
		this.pause(4000);
		System.out.println("t2 = " +  t2.getState() );
		
		this.printLog(log);
	}

	private void test2() {
		SpecialThread st1 = new SpecialThread("Fred");	// ctor are not inherited
		SpecialThread st2 = new SpecialThread("Barny");
		st1.start();
		System.out.println(st1.getState());
		st2.start();
		
		try {
			st1.join();
			st2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(st1.getState());
		System.out.println(st2.getState());
		this.printLog(log);
	}

	
}
