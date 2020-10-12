/**
 * Learn Assertions
 * 
 * */
package pkg_6;

public class LearnAssertions {

	public static void main(String[] args) {
		LearnAssertions la = new LearnAssertions();
		la.test1();
	}

	private void test1() {
		int n = 11;
		String name = null;
		
		assert n > 10;
		assert name != null : "name not set";	// Exception in thread "main" java.lang.AssertionError
								//at pkg_6.LearnAssertions.test1(LearnAssertions.java:19)
		
	}
	
}
