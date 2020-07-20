package pkg_1;
public enum CoffeeSize {
	BIG(8), HUGE(10), OVERWHELMING(16);
	
	int ounces;

	//public CoffeeSize( int ounces ){		// DNC public not allowed here
	CoffeeSize
		this.ounces = ounces;
	}
}
