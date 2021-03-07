package review_bs;

interface Secret {
	String magic(double d);
}

class MySecret implements Secret {
	public String magic(double d) {
		return "Poof";
	}
}

public class Ch2_Q11 {

	public static void main(String[] args) {
		Ch2_Q11 o = new Ch2_Q11();
		//String r = o.test1( (e) -> { String e = "Poof"; return "Poof"; } , 42.0D); // DNC Duplicate local variable e
	}

	private String test1( Secret s, double d ) {
		return s.magic(d);
	}
	
}
