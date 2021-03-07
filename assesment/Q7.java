package assesment;

import java.nio.file.Path;
import java.nio.file.Paths;



public class Q7 {

	public static void main(String[] args) {
		Q7 q7 = new Q7();
		q7.test2();
	}

	private void test1() {
		Path path1 = Paths.get("/bats/night","../");
		System.out.println(path1);	// /bats/night/..
		
		System.out.println( path1.getName(1) );	// night
		System.out.println( path1.getName(2) );	// ..		
	}
	
	private void test2() {
		Path path1 = Paths.get("/bats/night","../").resolve(Paths.get("./sleep.txt")).normalize();
	
		Path path2 = Paths.get("/bats/sleep.txt");
		System.out.println( path2.equals(path1) );
		//System.out.println(path1);
	}
	
}
