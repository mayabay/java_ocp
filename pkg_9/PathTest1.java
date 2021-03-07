/**
 * 
 */
package pkg_9;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * KB 5.2NIO.2
 *
 */
public class PathTest1 {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		PathTest1 pt1 = new PathTest1();
		pt1.test1();

	}

	private void test1() throws IOException {
		//Path p1 = Paths.get(".", "src", "zoo", "primates", "..", "snake.data");
		// /home/andreas/eclipse-workspace/java_ocp/src/zoo/snake.data
		
		Path p1 = Paths.get(".", "src", "zoo",".", "primates", "..", "snake.data");
		// /home/andreas/eclipse-workspace/java_ocp/src/zoo/snake.data	
		
		System.out.println( p1.toRealPath().toString() );
		
	}
	
}
