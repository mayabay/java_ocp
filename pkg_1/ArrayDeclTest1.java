package pkg_1;
class ArrayDeclTest1{

	public static void main(String[] args){

	}

	static void do1(){
		Number[] numbers = new Number[4];
		numbers[0] = new Double(3.14);
		numbers[0] = new Integer(0b1010);

		int[] ints = { 1,2,3,4 };
		int[] ints2 = new int[4];
		int[] ints3;
		ints3 = new int[] { 1,2,3,4 };

		StringBuilder sb = new StringBuilder("Hi");
		String[] strings = new String[4];
		strings[0] = "A";
		//strings[1] = (CharSequence)sb; // DNC incompat

		CharSequence[] cs = new CharSequence[3];
		cs[0] = "A";
		cs[1] = new String("B");	
		cs[2] = new StringBuilder("C");

	}




}
