package review_bs;

import javax.swing.plaf.synth.SynthOptionPaneUI;

public class Ch1_Q9 {

	private int x = 42;
	
	private class Inner {
		private int x = Ch1_Q9.this.x;
	}
	
	private int getX() {
		
		final int localInt = 6;		// final or effective final
		
		class InnerLocal {
			private int x = Ch1_Q9.this.x;
			public int getX() {
				return x + localInt;
			}
		}
		
		InnerLocal il = new InnerLocal();
		System.out.println("Inner Local =  " + il.getX() );
		
		return x;
	}
	
	public static void main(String[] args) {
		
		Ch1_Q9 o = new Ch1_Q9();
		System.out.println( o.getX() );
		;
		

	}

}
