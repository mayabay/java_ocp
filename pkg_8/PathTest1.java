/**
 * test Path and Paths methods
 */
package pkg_8;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 
 *
 */
public class PathTest1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PathTest1 pt1 = new PathTest1();
		//pt1.test1();
		// pt1.test2();
		pt1.test3();

	}

	private void test1() {
		System.out.println("# --- NORMALIZE");
		String str = "/root/dir1/dir2/../anotherDir";
		String str2 = "/path1/path2/../anotherDir";
		Path p1 = Paths.get(str2);
		System.out.println( str2 + " -> " + p1.normalize() );
		
	}
	
	private void test2() {
		System.out.println("# --- RESOLVE");
		Path p1 = Paths.get("/home/amann/");
		
		// Path p2 = Paths.get("/home/lucy/music/u2/exit.mp3");
			// returns just the second path
		
		// Path p2 = Paths.get("music/u2/exit.mp3");
			// // \home\amann\music\\u2\exit.mp3
		
		//Path p2 = Paths.get("");
			// \home\amann
		
		Path p4 = Paths.get("lucy");
		Path p2 = Paths.get("/home/amann");
		
		Path p5 = p4.resolve(p2);
			// \home\amann
		System.out.println(p5);
	}
	
	private void test3() {
		System.out.println("# --- RESOLVE");
		Path p1 = Paths.get("dir");
		Path p2 = Paths.get("exit.mp3");
		//System.out.println( p1.resolve(p2) );
			// dir\exit.mp3
		
		System.out.println( p2.resolve(p1) );
			// exit.mp3\dir
		
		// System.out.println( Path.resolve(null) );
		// Exception in thread "main" java.lang.Error: Unresolved compilation problem: 
		// The method resolve(Path) is ambiguous for the type Path
		Path p3 = Paths.get("");
		// System.out.println( p3.resolve( (String)null) );
			// 70: NullPointer 
	}	
	
}


