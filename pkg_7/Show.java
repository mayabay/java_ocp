/**
 * KB 10.3 RaceConidition
 * 
 * */
package pkg_7;

import java.util.HashSet;
import java.util.Set;

public class Show {
	private static volatile Show INSTANCE;;
	private Set<String> availableSeats;
	
	public static synchronized Show getInstance() {
		if ( Show.INSTANCE == null ) {
			Show.INSTANCE = new Show();
		}
		return Show.INSTANCE;
	}
	
	private Show() {
		this.availableSeats = new HashSet<String>();
		this.availableSeats.add("1A");
		this.availableSeats.add("1B");
	}
	
	public synchronized boolean bookSeat(String seat) {
		return this.availableSeats.remove(seat);
	}
}
