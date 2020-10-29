/**
 * KB 10.3 p.671 
 */
package pkg_7;

/**
 * 
 *
 */
public class AccountDanger implements Runnable{

	private Account account = new Account();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AccountDanger r = new AccountDanger();
		Thread one = new Thread(r);
		Thread two = new Thread(r);
		one.setName("Fred");
		two.setName("Lucy");
		one.start();
		two.start();
	}

	@Override
	public void run() {
		for ( int i = 0; i < 5; i++ )  {
			makeWithdrawal(10);
			if ( this.account.getBalance() < 0  ) {
				System.out.println("account is overdrawn!");
			}
		}
	}
	
	// add synchronized keyword to make check and withdrawal atomic
	private synchronized void makeWithdrawal(int amt) {
		if ( this.account.getBalance() >= amt ) {
			System.out.println( Thread.currentThread().getName() + " is going to withdraw" );
			try {
				Thread.sleep(500);
			}catch(InterruptedException e) {
				//
			}
			this.account.withdraw(amt);
			System.out.println( Thread.currentThread().getName() + " completes the withdrawal" );
		}
		else {
			// not enough balance
			System.out.println("Not enough in account for " +
			Thread.currentThread().getName() + " to withdraw " + this.account.getBalance());
		}
	}
	
	
}
