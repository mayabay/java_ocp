/**
 * KB 11.2.1
 * 
 * */
package pkg_7;


public class MonsterSpawning extends Thread {

	private MonsterCounterAtomic counter;
	
	public MonsterSpawning( MonsterCounterAtomic counter ) {
		this.counter = counter;
	}
	
	public void run() {
		for (int i = 0; i< 10000; i++) {
			counter.increment();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		//MonsterCounter counter = new MonsterCounter();
		MonsterCounterAtomic counter = new MonsterCounterAtomic();
		
		Thread monsterSpawning1 = new MonsterSpawning(counter) ;
		Thread monsterSpawning2 = new MonsterSpawning(counter);
		
		monsterSpawning1.start();
		monsterSpawning2.start();

		monsterSpawning1.join();
		monsterSpawning2.join();
		
		System.out.println("counter value : " + counter.getValue());
	}

	
	
}
