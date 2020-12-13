/**
 * BS 9.3
 */
package pkg_9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

/**
 * learn file attributes
 *
 */
public class FileAttributesTest1 {

	private Path workingDir = Paths.get(".").toRealPath(LinkOption.NOFOLLOW_LINKS) ;
	
	{
		// in eclipse or in git?
		Path withSrc = workingDir.resolve("src");
		if ( Files.exists( withSrc ) ) {
			workingDir = withSrc;
		}
	}
	
	/**
	 * workingDir might throw an exception!!!
	 * */
	protected FileAttributesTest1() throws IOException {
		
	}	
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		FileAttributesTest1 fat1 = new FileAttributesTest1();
		//fat1.test1();
		//fat1.test2();
		fat1.test3();
	}

	/**
	 * test file attributes
	 * */
	private void test1() {
		Path path = Paths.get("/home/andreas/ThomasMann_TodInVenedig.txt");
		System.out.println("path "+ path.toString() +" exists: " + Files.exists(path));
		
		System.out.println("is dir:  " + Files.isDirectory(path) );
		System.out.println("is exe:  " + Files.isExecutable(path) );
		System.out.println("is exe:  " + Files.isExecutable(path) );
		
		System.out.println("----------");
		
		FileAttributes fa = new FileAttributes(path, false);
		System.out.println(fa);
		
	}
	
	private void test2() {
		Path path = Paths.get("/home/andreas/ThomasMann_TodInVenedig.txt");
		System.out.println("path "+ path.toString() +" exists: " + Files.exists(path));
		try {
			BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
			System.out.println("is dir:  " + attr.isDirectory() );
			System.out.println("is reg. file:  " + attr.isRegularFile() );
			System.out.println("is symb link:  " + attr.isSymbolicLink() );
			System.out.println("is other:  " + attr.isOther() );
			System.out.println("size in bytes:  " + attr.size() );
			System.out.println("last acc:  " + attr.lastAccessTime() );
			System.out.println("last mod:  " + attr.lastModifiedTime() );
			System.out.println("unique file key :  " + attr.fileKey() );
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private void test3() {
		Path path = Paths.get("/home/andreas/ThomasMann_TodInVenedig.txt");
		System.out.println("path "+ path.toString() +" exists: " + Files.exists(path));		
		
		// view can be modified
		BasicFileAttributeView view =  Files.getFileAttributeView(path, BasicFileAttributeView.class);
		
		try {
			BasicFileAttributes attr = view.readAttributes();
			System.out.println("last mod. : " + attr.lastModifiedTime());
			
			FileTime lastModifiedTime = FileTime.fromMillis(attr.lastModifiedTime().toMillis() + 10_000 );
			view.setTimes(lastModifiedTime, null,null);
			System.out.println("-----");
			attr = view.readAttributes();	// update attr. values
			System.out.println("last mod. : " + attr.lastModifiedTime());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
