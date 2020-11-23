/**
 * KB 11.2.1
 */
package pkg_7;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 *
 */
public class MonsterCounterAtomic {
	
	private AtomicInteger count = new AtomicInteger();
	
	public void increment() {
		count.getAndIncrement();
	}
	
	public int getValue() {
		return count.intValue();
	}
}
