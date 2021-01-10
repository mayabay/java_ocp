package pkg_1;
/**
 * https://upload.wikimedia.org/wikipedia/commons/thumb/3/38/Sonnensystem-Grafik.pdf/page1-1280px-Sonnensystem-Grafik.pdf.jpg
 * */
public enum Planets {
	MERCURY,
	VENUS,
	EARTH(1) {
		int AU = 1;
		
		public String getInfo() {
			return "Earth is the third plant from sun, life is present. AU value is "+AU+".";
		}
	},
	MARS,
	JUPITER,
	SATURN,
	URANUS,
	NEPTUN;
	
	private int AU;
	
	private Planets(){}
	
	private Planets(int AU){
		this.AU = AU;
	}
	
	public String getInfo() {
		return "A planet in the solar system.";
	}
}
