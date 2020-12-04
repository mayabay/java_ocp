package pkg_3;

import java.util.ArrayDeque;
import java.util.Deque;
import plants.*;

public class LearnStack {

	public static void main(String[] args) {
		LearnStack ls = new LearnStack();
		ls.do1();
	}
	
	private void do1() {
		
		Deque<Box<Citrus>> stack = new ArrayDeque<>(); 
		
		stack.push( fillBox( 24, Orange.class ) );		// Orange
		stack.push( fillBox( 6, Lemon.class ) );		// Lemon, Orange
		stack.push( fillBox( 2, Lime.class ) );			// Lime, Lemon, Orange
		
		System.out.println( stack.peek() );				// Lime
		stack.pop();
		System.out.println( stack.peek() );				// Lemon
	}
	
	private static Box<Citrus> fillBox( int amount, Class<? extends Citrus> type ){
		Box<Citrus> box = new BoxBanana<>();
		for ( int i = 0; i < amount; i++ ) {
			try {
				box.add(  type.newInstance()  );
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace(); 
				System.exit(1);
			}
		}
		return box;
	} 

}
