package pkg_2;
class ArrayTest1{

	public static void main(String[] args){
		do1();
	}

	private static void do1(){
		
		int idx = (int) (Math.random() * 5);
		String name = new String[] { "Andrew", "Bert", "Carl","Dan","Elf" } [idx];
		System.out.println( name );

	}
}
