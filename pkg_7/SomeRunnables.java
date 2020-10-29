/**
 * BS 7.1.3
 * 
 * */
package pkg_7;

public class SomeRunnables {

	private static Runnable runnable = () -> {	
		System.out.println("thread start");
		int count = 0;
		for ( int i = 0; i < 100; i++ ) {
			count++;
			System.out.println(count);
			Thread me = Thread.currentThread();
			try {
				me.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//System.out.println(count);		
		System.out.println("thread end");
	};
	
	public static void main(String[] args) {
		SomeRunnables sr = new SomeRunnables();

		Thread t1 = new Thread(runnable);
		t1.setName("t1");
		t1.start();
		
		System.out.println( "t1 state : " + t1.getState().name() );
		System.out.println("main() finished.");
	}

	
	
}
