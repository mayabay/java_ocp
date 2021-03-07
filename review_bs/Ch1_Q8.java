package review_bs;

public class Ch1_Q8 {

	public class Inner {
		private static final double TEMPERATURE = 34.35D;
	}
	
	public static void main(String[] args) {
		(new Ch1_Q8()).new Inner();

		new Ch1_Q8().new Inner();
	}

}
