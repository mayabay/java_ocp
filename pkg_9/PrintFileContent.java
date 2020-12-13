/**
 * BS 9.4.5
 */
package pkg_9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 
 *
 */
public class PrintFileContent {

	private Path workingDir = Paths.get(".","src","zoo").toRealPath(LinkOption.NOFOLLOW_LINKS) ;
	
	public PrintFileContent() throws IOException {}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		PrintFileContent pfc = new PrintFileContent();
		pfc.test1();
	}
	
	private void test1() {
		Path path = Paths.get(workingDir.toString(),"primate_species.txt");
		try {
			Files.lines(path)
				.forEach( System.out::println );
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
