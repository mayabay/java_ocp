/**
 * KB 10.3 
 */
package pkg_7;

/**
 * @author Andreas Mann (lokal)
 *
 */
public class TestShow {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestShow testThreads = new TestShow();
		testThreads.go();
	}

	public void go() {
		
		// create thread-1
		Thread getSeats1 = new Thread(
			() -> {
				ticketAgentBooks("1A");
				ticketAgentBooks("1B");
			}
		);
		
		// create thread-2
		Thread getSeats2 = new Thread(
			() -> {
				ticketAgentBooks("1A");
				ticketAgentBooks("1B");
			}
		);
		getSeats1.start();
		getSeats2.start();
	}
	
	public void ticketAgentBooks(String seat) {
		// get Show instance
		Show show = Show.getInstance();
		// book a set
		System.out.println(Thread.currentThread().getName() + " : " + show.bookSeat(seat));
		
	}
	
	
}
