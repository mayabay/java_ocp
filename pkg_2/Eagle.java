package pkg_2;

interface Fly{
	
	public static final int MAX_SPEED = 100;
	
	public int getWingSpan() throws Exception;

	public default void land(){
		System.out.println( "Animal is landing." );

	}

	public static double calculateSpeed(float distance, double time){
		return distance/time;
	}
}

public class Eagle implements Fly {
	public int getWingSpan(){
		return 15;
	}
	
	public void land(){
		System.out.println( "EAgle is diving fast" );

	}

	
	public static void main(String[] args){
		Eagle e = new Eagle();
	}

}
