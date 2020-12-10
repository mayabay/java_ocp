/**
 * BS 9.1
 */
package pkg_9;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.UserPrincipal;
import java.nio.file.attribute.UserPrincipalLookupService;
import java.util.Iterator;

/**
 * Learn to create paths
 */
public class CreatingPaths {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CreatingPaths cp = new CreatingPaths();
		cp.test1();
		// cp.test1();

	}

	private void test1() {
		
		// w/o root / path is relative
		Path imPath1 = Paths.get(File.separator, "home", "andreas", "immodata");
		
		System.out.println( "name count = " + imPath1.getNameCount() );
		
		System.out.println( "absolute = " + imPath1.isAbsolute() );
		
		System.out.println( "as uri = " +  imPath1.toUri() );
		
		try {
			URI uri = new URI("http://www.selikoff.net");
			System.out.println("URI example =  " + uri);
			FileSystem fs = FileSystems.getFileSystem(uri);
			Path pathToDuck = fs.getPath("duck.txt");
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
	}
	private void test2() {
		Path imPath1 = Paths.get(File.separator, "home", "andreas", "immodata");
		FileSystem fs = imPath1.getFileSystem();
		
		Iterable<Path> iterable = fs.getRootDirectories();
		Iterator<Path> iterator = iterable.iterator();
		
		// only / for linux
		while ( iterator.hasNext() ) {
			System.out.println("root directory = " + iterator.next() );
		}
		
		FileSystem fs2 = FileSystems.getDefault();
		UserPrincipalLookupService upls = fs2.getUserPrincipalLookupService();
		try {
			UserPrincipal user =  upls.lookupPrincipalByName(System.getenv("USER"));
			System.out.println("user principal = " +  user.getName() );
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
}
