/**
 * BS 9.2.3
 */
package pkg_9;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Test java.nio.file.Files class
 *
 */
public class FilesClassTest1 {

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
	protected FilesClassTest1() throws IOException {
		
	}
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		FilesClassTest1 fct1 = new FilesClassTest1();
		//fct1.test1();
		//fct1.test2();
		//fct1.test3();
		//fct1.test4();
		//fct1.test5();
		//fct1.test6();
		fct1.test7();
	}

	private void test1() {
		System.out.println( "current dir = " + this.workingDir );
		Stream<Path> stream;
		try {
			stream = Files.list(workingDir);
			stream
			.forEach( System.out::println );	
			
//			current dir = /home/andreas/eclipse-workspace/java_ocp
//					/home/andreas/eclipse-workspace/java_ocp/.classpath
//					/home/andreas/eclipse-workspace/java_ocp/src
//					/home/andreas/eclipse-workspace/java_ocp/.project
//					/home/andreas/eclipse-workspace/java_ocp/bin
//					/home/andreas/eclipse-workspace/java_ocp/.settings			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * isSame()
	 * */
	private void test2() throws IOException {
		
		System.out.println("workdir ["+this.workingDir+"] exists = " +  Files.exists(workingDir) ); 
		System.out.println( Files.exists( Paths.get("./src/ostrich") ) );

		Path wd1 = this.workingDir.resolve("zoo/armadillo");
		Path wd2 = this.workingDir.resolve("zoolink/armadillo");
		//Path wd2 = Paths.get(".").toRealPath(LinkOption.NOFOLLOW_LINKS) ;
		
		System.out.println("is same = " + Files.isSameFile(wd1, wd2));
			// true for zoo and symlink to zoo
		
		System.out.println("is same snake.data = " + 
		Files.isSameFile(workingDir.resolve("zoo/snake.data"), workingDir.resolve("zoo/cobra.data")));
		
	}
	
	/**
	 * createDirectories()
	 * */
	private void test3() {
		Path tigersPath = this.workingDir.resolve("zoo/tigers");
		boolean tigersExist = Files.exists( tigersPath );
		if ( !tigersExist ) {
			try {
				tigersPath = Files.createDirectories(tigersPath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			System.out.println(tigersPath.toRealPath());
				// mode 775 with user and group owner 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			tigersPath = Files.createDirectories( workingDir.resolve("zoo/tigers/india/bengal") );
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
	}
	
	/**
	 * move()
	 * */
	private void test4() {
		Path primatesPath = this.workingDir.resolve("zoo/primates");
		if ( ! Files.exists(primatesPath ) ) {
			try {
				Files.createDirectory(primatesPath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			Files.move( primatesPath, this.workingDir.resolve(Paths.get("zoo/primate_house")), StandardCopyOption.REPLACE_EXISTING  );
				// java.nio.file.NoSuchFileException: /home/andreas/eclipse-workspace/java_ocp/src/zoo/primates -> zoo/primate_house
				// /home/andreas/eclipse-workspace/java_ocp/zoo/primate_house does not exist
			
			// and back
			Files.move( this.workingDir.resolve(Paths.get("zoo/primate_house")) , primatesPath  );
			
			Path harryOrangUtan = this.workingDir.resolve("zoo/orangutan.txt");
			if (Files.exists(harryOrangUtan)) {
				Files.move(harryOrangUtan, this.workingDir.resolve("zoo/primates/orangutan.txt"), StandardCopyOption.REPLACE_EXISTING);
			}
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * createDirectory()
	 * */
	private void test5() {
		Path visPath = this.workingDir.resolve("zoo/visitors");
		if ( ! Files.exists(visPath ) ) {
			try {
				Files.createDirectory(visPath);
				System.out.println("delete zoo/visitors : " + Files.deleteIfExists(visPath));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
	}
	
	/**
	 * char value in java is unicode codepoint in decimal
	 * */
	private void test6() {
		String string = "hello";
		char [] chars = string.toCharArray();
		for( char c : chars ) {
			System.out.println("char = " + (short)c); // char value in java is unicode codepoint in decimal 
		}
		
	}
	
	/**
	 * read and write with Files
	 * */
	private void test7() {
		
		Charset charsetDef = Charset.defaultCharset();
		Set<String> aliases = charsetDef.aliases();
		System.out.println("aliases" + aliases);
		System.out.println("canonical name = " + charsetDef.name() );
		System.out.println("----------");
		
		Path path = Paths.get("/home", "andreas", "ThomasMann_TodInVenedig.txt");
		Path pathCopyUTF16 = Paths.get("/home", "andreas", "ThomasMann_TodInVenedig_UTF16.txt");
		System.out.println("file ("+path.toString()+") exists = " + Files.exists(path));
		
		if (Files.exists(path)) {
			try( BufferedReader reader = Files.newBufferedReader(path);
					BufferedWriter writer = Files.newBufferedWriter(pathCopyUTF16, Charset.forName("UTF-16"))
					){
				String line;
				int lineCount = 0;
				while ( (line = reader.readLine()) != null ) {
					//System.out.println(line);
					lineCount++;
					writer.write(line);
				}
				System.out.println("file has lines = " + lineCount);
				System.out.println("file written to = " + pathCopyUTF16.toAbsolutePath());
			}catch( IOException e ) {
				e.printStackTrace();
			}
		}
	}
	
}
