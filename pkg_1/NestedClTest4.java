package pkg_1;
class NestedClTest4 {

	private int number;
	private static int number2;

	public static class Adder{
		private int a, b, c = number2;
		public Adder( int a, int b ){
			this.a = a; this.b = b;
		}
		public int sum(){ return a + b; }
	}

	public static void main(String[] args){
		Adder adder = new Adder(1,2);
		Adder adder2 = new Adder(4,3);
		System.out.println( sumTotal( new Adder[] {adder, adder2} ) );
	}

	private static int sumTotal( Adder[] adders ){
		int total = 0;
		for( Adder a : adders ){
			total += a.sum();
		}
		return total;
	}
}
