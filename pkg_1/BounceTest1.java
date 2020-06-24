package pkg_1;
public class BounceTest1 implements Bounceable{

	private int bf;

	public static void main(String[] args){
		//bounce();	// DNC 7: error: non-static method bounce() cannot be referenced from a static context
		BounceTest1 bt1 = new BounceTest1();
		bt1.bounce();

		//m2();	// DNC 11: error: cannot find symbol
		Bounceable.m2();
	}

	public void bounce(){
		System.out.println( "bounce()" );
	}

	public void setBounceFactor(int bf){
		this.bf = bf;
	}
}
