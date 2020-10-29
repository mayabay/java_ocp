package pkg_7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Fox {

	public void eatAndDrink( Food food, Water water ) {
		synchronized (food) {
			System.out.println("Got food!");
			move();
			synchronized (water) {
				System.out.println("Got water!");
			}
		}
	}
	
	public void drinkAndEat( Food food, Water water ) {
		synchronized (water) {
			System.out.println("Got water!");
			move();
			synchronized (food) {
				System.out.println("Got food!");
			}
		}
	}	
	
	public void move() {
		try {
			Thread.sleep(100);
		}catch(InterruptedException e) {
			// handle
		}
	}

	public static void main(String[] args) {
		Fox foxy = new Fox();
		Fox tails = new Fox();
		Food food = new Food();
		Water water = new Water();
		
		ExecutorService es = null;
		
		try {
			es = Executors.newScheduledThreadPool(10);
			es.submit(()-> foxy.eatAndDrink( food, water ));
			es.submit(()-> tails.drinkAndEat( food, water ));
		}finally {
			if (es != null) es.shutdown();
		}
	}

}
