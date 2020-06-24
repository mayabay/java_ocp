package pkg_1;
public interface Bounceable{
	double maxHeight = 101.25D;
	void bounce();
	void setBounceFactor(int bf);
	default void m1(){
		System.out.println( "m1()" );
	} 
	static void m2(){
		System.out.println( "m2()" );
	}


}

