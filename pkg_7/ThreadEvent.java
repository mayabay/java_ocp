/**
 * 
 */
package pkg_7;

import java.time.Instant;

/**
 * A log entry kind class 
 *
 */
public class ThreadEvent implements  Comparable<Instant> {

	private Instant instant;
	private String message;
		
	ThreadEvent(Instant instant, String message) {
		super();
		this.instant = instant;
		this.message = message;
	}
	
	ThreadEvent(String message) {
		this(Instant.now(), message);
	}	
	
	@Override
	public int compareTo( Instant other ) {
		return this.instant.compareTo(other);
	}
	
	@Override
	public String toString() {
		return "[Event: "+instant+" : "+message+"]";
	}

	/**
	 * @return the instant
	 */
	public Instant getInstant() {
		return instant;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	
	
}
