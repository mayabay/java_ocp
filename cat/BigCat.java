package cat;

public class BigCat {
	
	public String name = "cat";
	
	protected boolean hasFur = true;
	
	boolean hasPaws = true;
	
	private int id;
	
	public static void main( String ... args ) {
		BigCat bc = new BigCat();
		System.out.println( bc.id );

	}
	
	
}
