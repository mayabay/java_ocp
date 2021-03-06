/**
 * BS 9.1.1
 */
package pkg_8;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

/**
 * 
 *
 */
public class PathsTest1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PathsTest1 pt1 = new PathsTest1();
		pt1.test2();

	}

	private void test1(){
		//ConfigObj.IMMO_DATA_PATH
		Path path =  Paths.get( ConfigObj.IMMO_DATA_PATH );
		System.out.println( path.getFileName() );
		System.out.println( "name elem count = " + path.getNameCount() );
		
		// underlying filesystem
		FileSystem fs =  path.getFileSystem();
		System.out.println("seperator in this fs = " + fs.getSeparator() );
		
		// elements of Path
		Iterator<Path> it =  path.iterator();
		while( it.hasNext() ) {
			System.out.println( (it.next()).toString() );
		}
		
		URI pathAsUri = path.toUri();
		System.out.println(pathAsUri);
	}
	
	private void test2() {
		try {
			FileSystem fileSystem = FileSystems.getFileSystem(new URI("http://www.selikoff.net"));	// RTE
			// Exception in thread "main" java.nio.file.ProviderNotFoundException: Provider "http" not found
			Path path = fileSystem.getPath("duck.txt");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
}
