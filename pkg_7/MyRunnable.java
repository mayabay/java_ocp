package pkg_7;

public class MyRunnable implements Runnable {

	private int count = 0;
	
	@Override
	public void run() {
		
		// object reference is local to thread
		MyObject object = new MyObject();
		
		for (int i = 0; i < 1_000_000 ; i++) {
			synchronized(this) {
				this.count++;
			}
		}
		
		System.out.println(
				Thread.currentThread().getName() +
				" : " + this.count
				);
		
		//System.out.println(object);
	}
	
}
