/**
 * 
 */
package pkg_9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributes;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.PosixFileAttributes;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

/**
 *
 */
public class PathTest4 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Path workingDir = Paths.get("./tmp");
		PathTest4 pt4 = new PathTest4();
		//pt4.createDirectories(workingDir);
		//pt4.listDirectoryContent(workingDir, (p)->Files.exists(p) && !Files.notExists(p) );
		//pt4.listDirectoryContent(workingDir, (p)->!Objects.isNull(p));
		//pt4.listDirectoryContent(workingDir, (p)-> Files.isDirectory(p) );
		//pt4.test1();
		//pt4.test2();
		
		try {
			pt4.checkFile( pt4.getAnyFile(workingDir) );
		}catch( IOException e ) {
			e.printStackTrace();
		}
		
	}

	private void createDirectories( Path p ) {
		if ( Files.exists(p,LinkOption.NOFOLLOW_LINKS ) ) return;
		try {
			Files.createDirectory(Paths.get(p.toString(), "local"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 
	
	private void listDirectoryContent( Path p, Predicate<Path> filter ) {
		try {
			Files.list(p)
			.filter(filter)
			.forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void test1() {
		Path workingDir  = Paths.get("./tmp");
		Path subDirs = Paths.get(workingDir.toString(), "sub", "subsub", "theend");
		try {
			Files.createDirectories(subDirs);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private Path getAnyFile( Path p ) {
		
		try {
			Optional<Path> opt =
			Files.list(p)
			.filter(Files::isRegularFile)
			.filter(e->e.toString().endsWith("txt"))
			.findAny();
			return opt.orElseThrow(()->new RuntimeException("No file available!"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		throw new IllegalStateException("Sould not arrive here!");
	}
	
	private void test2() {
		Path p = Paths.get("/path1/path2/../sub/file");
		System.out.println( p.normalize() );	// \path1\sub\file
	}
	
	private void checkFile( Path p ) throws IOException {
		System.out.println("path given : " + p);
		System.out.println("exists  : " + (Files.exists(p, LinkOption.NOFOLLOW_LINKS) &&
				!Files.notExists(p, LinkOption.NOFOLLOW_LINKS) ) );
		FileTime ft = Files.getLastModifiedTime(p);
		System.out.println("last modified : " + ft);
		System.out.println("isDirectory : " + Files.isDirectory(p));
		System.out.println("isReadable : " + Files.isReadable(p));
		System.out.println("isWritable : " + Files.isWritable(p));
		System.out.println("isExecutable : " + Files.isExecutable(p));
		
		System.out.println("BasicFileAttributes");
		BasicFileAttributes basicFileAttr = Files.readAttributes(p, BasicFileAttributes.class, LinkOption.NOFOLLOW_LINKS);
		System.out.println("bfa creationTime : " + basicFileAttr.creationTime());
		System.out.println("bfa lastAccessTime : " + basicFileAttr.lastAccessTime());
		System.out.println("bfa lastModifiedTime : " + basicFileAttr.lastModifiedTime());
		
		System.out.println("DosFileAttributes");
		DosFileAttributes dosFileAttr = Files.readAttributes(p, DosFileAttributes.class, LinkOption.NOFOLLOW_LINKS);
		System.out.println("dfa isSystem : " + dosFileAttr.isSystem() );
		
		System.out.println("PosixFileAttributes");
		//PosixFileAttributes posixFileAttr = Files.readAttributes(p, PosixFileAttributes.class, LinkOption.NOFOLLOW_LINKS);
			// RTE  java.lang.UnsupportedOperationException when run under Windows
		//System.out.println("pfa isRegularFile : " + posixFileAttr.isRegularFile() );
		
		
		
	}
}
