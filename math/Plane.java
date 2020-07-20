package math;
/**
 * Represents a Plane
 * */
public class Plane{

	/**
	 * Maximum X unit value
	 * */
	public final int X_MAX;

	/**
	 * Minimum X unit value
	 * */
	public final int X_MIN;
	
	/**
	 * Maximum Y unit value
	 * */
	public final int Y_MAX;
	
	/**
	 * Minimum Y unit value
	 * */
	public final int Y_MIN;

	/**
	 * Constructs Plane with x -10-10 and y -10- 10
	 * */
	public Plane(){
		this(-10,10,-10,10);
	}

	/**
	 * Constructs plane with x and y values
	 * @param xMax
	 * @param xMin
	 * @param yMax
	 * @param yMin
	 * */
	public Plane( int xMax, int xMin, int yMax, int yMin ){
		this.X_MAX = xMax;
		this.X_MIN = xMin;
		this.Y_MAX = yMax;
		this.Y_MIN = yMin;
	}
}
