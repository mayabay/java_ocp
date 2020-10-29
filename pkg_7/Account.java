/**
 * KB 10.3
 */
package pkg_7;

/**
 * @author Andreas Mann (lokal)
 *
 */
public class Account {
	
	private int balance = 50;
	
	public int getBalance() {
		return this.balance;
	}
	
	public void withdraw(int amount) {
		this.balance = balance - amount;
	}
}
