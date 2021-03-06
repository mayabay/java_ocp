package pkg_2;

public interface Fly {
	public static final int MAX_SPEED = 100;
	
	public int getWingSpan() throws Exception;

	public default void land(){
		System.out.println( "Animal is landing." );

	}

	public static double calculateSpeed(float distance, double time){
		return distance/time;
	}
}
