/**
 * BS 9.4.2
 */
package pkg_9;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Predicate;

/**
 * NIO.2 stream api
 *
 */
public class DirectoryWalkerTest1 {

	private Path workingDir = Paths.get(".","src","zoo").toRealPath(LinkOption.NOFOLLOW_LINKS) ;
	
	public DirectoryWalkerTest1() throws IOException {}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		DirectoryWalkerTest1 dwt1 = new DirectoryWalkerTest1();
		//dwt1.test1();
		dwt1.test2();

	}

	private void test1() {
		System.out.println(this.workingDir);
		Path path = Paths.get(this.workingDir.toString(), "bigcats");
		System.out.println(path);
		System.out.println("----------");
		try {
			Files.walk(path)
				.filter(p -> p.toString().endsWith(".java"))
				.forEach(System.out::println);
			
			System.out.println("----------");
			
			Files.find( path, 1, (p,a) -> {
				return p.toString().endsWith("ion.java") && a.isRegularFile();
			}, FileVisitOption.FOLLOW_LINKS )
				.forEach( System.out::println );
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void test2(){
		
		Predicate<Path> pred1 = Files::isDirectory;
		
		try {
			Files.list(workingDir)
				//.filter( p -> Files.isDirectory(p) )
				//.filter( Files::isDirectory )
				.filter( pred1.negate() )
				.forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
