/**
 * Test low level synchronization tools
 */
package pkg_7;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import pkg_7.ThreadTest1.Event;

/**
 * @author andreas
 *
 */
public class ThreadTest2 {

	// Event is inner class of ThreadTest1, need enclosing instance
	ThreadTest1 tt1;
	
	// wait beefore start here
	CountDownLatch ctl;
	// supplier for wood identities
	private static volatile int counter;
	// supplier for wood identities
	private static AtomicInteger atom;
	private Random random = new Random();
	// max storage capacity
	private static final int MAX_STORAGE_CAPACITY = 24;
	// log entries > this value -> Runnable will stop
	private static final int MAX_LOG_SIZE = 32;
	// min amount of milli sec. a thread will sleep
	private static final int THREAD_SLEED_MILLIS_MIN = 1000;
	// max random bound in milli sec., is added to THREAD_SLEED_MILLIS_MIN for total wait timer
	private static final int THREAD_SLEED_MILLIS_MAX_BOUND = 3000;
	
	static {
		atom = new AtomicInteger();
	}
	
	// wood storage
	private ConcurrentLinkedQueue<Wood> woodStorage = new ConcurrentLinkedQueue<ThreadTest2.Wood>();
	
	// log events
	private Queue<Event> log = new ConcurrentLinkedQueue<>(); 
	
	/**
	 * Represents a peace of wood
	 * */
	class Wood {
		private int id;

		Wood(int id) {
			super();
			this.id = id;
		}
		
		@Override
		public int hashCode() {
			return 17 * id;
		}
		
		@Override
		public boolean equals( Object other ) {
			if ( this == other ) return true;
			if ( other == null ) {
				return false;
			}
			if ( ! (other instanceof Wood) ) {
				return false;
			}
			
			Wood otherWood = (Wood)other;
			if ( this.id == otherWood.id ) {
				return true;
			}
			
			return false;
		}
		
		@Override
		public String toString() {
			return "[Wood "+ this.id +"]";
		}
		
	}
	
	Runnable producer = () -> {
		
		String tName = Thread.currentThread().getName();
		
		ctl.countDown();
		this.log.offer(tt1.new Event( "prod "+tName+":\t wait at ctl =" + ctl.getCount()  ));
		try {
			ctl.await();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		while( true ) {
			// (1) go into wood 
			int timeInWood = this.random.nextInt(THREAD_SLEED_MILLIS_MAX_BOUND);	// let prod run faster
			this.pause( timeInWood );
			// (2) now store wood
			synchronized( this ) {
				if (woodStorage.size() >= MAX_STORAGE_CAPACITY) {
					// no place to store
					this.log.offer(tt1.new Event( "prod "+tName+":\t no free place, is " + woodStorage.size() ));
					try {
						this.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				if ( woodStorage.size() >= MAX_STORAGE_CAPACITY ) {
					// still not ok
					this.log.offer(tt1.new Event( "prod "+tName+":\t no free place, is " + woodStorage.size() ));
					continue;
				}else {
					// finally add some wood
					this.woodStorage.offer( this.new Wood( ThreadTest2.atom.incrementAndGet() ) );
					this.log.offer(tt1.new Event( "prod "+tName+":\t added wood, now " + woodStorage.size() ));
					this.notifyAll();
				}
			}		
			
			if( this.log.size() > ThreadTest2.MAX_LOG_SIZE  ) { 
				this.log.offer(tt1.new Event( "prod "+tName+":\t finished, is " + woodStorage.size() ));
				break;
			}
			
		}	// producer job
		
	};
	
	Runnable consumer = () -> {
		
		String tName = Thread.currentThread().getName();

		ctl.countDown();
		this.log.offer(tt1.new Event( "cons "+tName+":\t wait at ctl =" + ctl.getCount()  ));
		try {
			ctl.await();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}		
		
		while( true ) {
			
			// (1) go to storage
			int timeGoToStorage = this.random.nextInt(THREAD_SLEED_MILLIS_MIN + THREAD_SLEED_MILLIS_MAX_BOUND);
			this.pause( timeGoToStorage );			
			
			// (2) i want buy wood
			synchronized ( this ) {
				if ( this.woodStorage.size() == 0 ) { 
					this.log.offer(tt1.new Event( "cons "+tName+":\t empty, is " + woodStorage.size() ));
					try {
						this.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (  this.woodStorage.size() == 0  ) {
						this.log.offer(tt1.new Event( "cons "+tName+":\t still empty, is " + woodStorage.size() ));	
						continue;
					}
				}
				else {
					// get wood
					Wood w = this.woodStorage.poll();
					this.log.offer(tt1.new Event( "cons "+tName+":\t got "+w+", is " + woodStorage.size() ));	
					// bring home
					this.pause( timeGoToStorage );	
					// call waiting producer
					this.notifyAll();
				}				
			}
			
			if( this.log.size() > ThreadTest2.MAX_LOG_SIZE  ) { 
				this.log.offer(tt1.new Event( "cons "+tName+":\t finished, is " + woodStorage.size() ));
				break;
			}			
		}
		
	};
	
	public ThreadTest2() {
		tt1 = new ThreadTest1();
	}
	
	// print log entries
	private void printLog( Queue<Event> log ) {
		int initialSize = log.size(); 
		for( int i = 0; i < initialSize; i++ ) {
			System.out.println(log.poll());
		}
	}		
	
	// pause a while
	private void pause(long millis) {
		try {
			Thread.currentThread().sleep(millis);
		}catch( InterruptedException e ) {
			e.printStackTrace();
		}
	}	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ThreadTest2 tt2 = new ThreadTest2();
		
		// 1 producer and 2 consumer
		int partiesInPlay = 3;
		
		tt2.ctl = new CountDownLatch(partiesInPlay);
		
		Thread prod1 = new Thread(tt2.producer, "p1");
		prod1.start();
		Thread cons1 = new Thread(tt2.consumer, "c1");
		cons1.start();
		Thread cons2 = new Thread(tt2.consumer, "c2");
		cons2.start();
		
		try {
			prod1.join();
			tt2.log.offer(tt2.tt1.new Event( "main :\t prod1 finished" ));
			cons1.join();
			tt2.log.offer(tt2.tt1.new Event( "main :\t cons1 finished" ));
			cons2.join();
			tt2.log.offer(tt2.tt1.new Event( "main :\t cons2 finished" ));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		tt2.printLog(tt2.log);
		
		System.out.println("main finished");

	}

}
