package pkg_2;

public interface Run extends Walk {
	public abstract boolean canHuntWhileRunning();
	abstract double getMaxSpeed();
	//public default String toString() { return ""; }	// DNC A default method cannot override a method from java.lang.Objec
}
