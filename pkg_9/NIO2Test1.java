/**
 * KB 5.4
 */
package pkg_9;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * NIO.2 Stream API
 *
 */
public class NIO2Test1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		NIO2Test1 nt1 = new NIO2Test1();
		nt1.test1();

	}

	private void test1() {
		Path path = Paths.get(".");
		System.out.println(path.toAbsolutePath().normalize());
		// /home/andreas/eclipse-workspace/java_ocp/.
		// . removed after normalize()
		
		Path homePath = Paths.get("/", "home");
		System.out.println("path ("+ homePath.toString() +") name count : " + homePath.getNameCount());

		try( DirectoryStream<Path> stream = Files.newDirectoryStream(homePath); ){
			
			for (Path userPath : stream) {
				System.out.println("user : " + userPath.toString());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
}
