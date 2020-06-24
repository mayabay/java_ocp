package pkg_1;
public enum CoffeeSize {
	BIG(8), HUGE(10), OVERWHELMING(16);
	
	int ounces;

	CoffeeSize( int ounces ){
		this.ounces = ounces;
	}
}
