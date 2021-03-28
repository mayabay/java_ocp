/**
 * 
 */
package review_bs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.nio.file.attribute.BasicFileAttributeView;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import pkg_9.FileAttributes;

/**
 *
 */
public class Ch9_Questions1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Ch9_Questions1 obj = new Ch9_Questions1();
		//obj.test1();
		//obj.test2();
		//obj.test3();
		//obj.test4();
		//obj.test6();
		//obj.test7();
		//obj.test8();
		//obj.test10();
		obj.test11();
	}

	private void test1() {
		Path p = Paths.get("/user/../root","../kodiacbear.txt");	// DNC Path.get
		//p.normalize().relativize("/lion");	// DNC you cannot relativize String objects
		p.normalize().relativize(Paths.get("/lion"));
		//System.out.println(p); // \\user\..\root\..\kodiacbear.txt
			// Path cannot be chained
		
		Path p2 = p.normalize().relativize(Paths.get("/lion"));	// /kodiacbear.txt -> /lion = ../lion
		// System.out.println(p2);	// ..\lion
		
		//System.out.println( Paths.get("/user/../root").normalize() ); // /root
		//System.out.println( Paths.get("../kodiacbear.txt").normalize() ); // ..\kodiacbear.txt
		//System.out.println(Paths.get("/user/../root","../kodiacbear.txt").normalize());	// /kodiacbear.txt
		
		
		Path root = Paths.get("/");
		Path p3 = Paths.get("/lion");
		//System.out.println( root.relativize(p3) );	// lion
		
		Path zebra = Paths.get("/zebra");
		Path lion = Paths.get("/lion");
		//System.out.println( zebra.relativize(lion) );	// ..\lion		
		
	}
	
	private void test2() {
		Path p1 = Paths.get("../dir.txt");
		System.out.println(p1.normalize()); 	// ..\dir.txt
		
		Path p2 = Paths.get("parent/../dir.txt");
		System.out.println(p2.normalize()); 	// dir.txt
		
		Path p3 = Paths.get("/../dir.txt");
		System.out.println(p3.normalize()); 	// \dir.txt
		
		Path p4 = Paths.get("/home/user/../dir.txt");
		System.out.println(p4.normalize()); 	// \home\dir.txt	
		
	}
	
	private void test3() {
		// resolve : joins the other to me
		Path other = Paths.get("/dir/sub/file.txt");
		Path me = Paths.get("/home/user/anotherfile.txt");
		//System.out.println( me.resolve(other) ); // \dir\sub\file.txt
		
		Path other2 = Paths.get("sub");
		Path me2 = Paths.get("/home/user/anotherfile.txt");
		System.out.println( me2.resolve(other2) ); // /home/user/anotherfile.txt/sub
		
	}
	
	private void test4() {
		Path p = Paths.get("/user/../root","../kodiacbear.txt");
		//System.out.println(p);					// \\user\..\root\..\kodiacbear.txt
		//System.out.println(p.normalize());		// \kodiacbear.txt
		
		Path p1 = Paths.get("/home/user");
		Path p2 = Paths.get("/var/data");
		Path p3 = p1.relativize(p2);				// returns path to /var/data
		System.out.println(p3);
		
		System.out.println(p1.resolve(p3));			// returns given path, which is me + path to other
		System.out.println(p1.resolve(p3).normalize());
	}
	
	private void test5() {
		Path p = Paths.get("./tmp");
		try {
			Optional<Path> found = 
			Files.list(p)
			.filter(path->path.toString().endsWith(".txt") )
			.findAny();
			Path p2 = found.orElseThrow(()->new RuntimeException("no files found"));
			BasicFileAttributes attr = Files.readAttributes(p2, BasicFileAttributes.class);
			
			BasicFileAttributeView attrView = Files.getFileAttributeView(p2, BasicFileAttributeView.class);
			BasicFileAttributes attr2 = attrView.readAttributes();
			
			if (attr2.size() > 0 && attr2.creationTime().toMillis() > 0 ) {
				attrView.setTimes(null, null, null); // any null attr will not be set
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void test6() {
		Path path = Paths.get("/zoo/animals/bear/koala/food.txt");
		System.out.println( path.subpath(1, 3).getName(1).toAbsolutePath() );
		
		Set<String> set = FileSystems.getDefault().supportedFileAttributeViews();
		System.out.println(set);
	}
	
	private void test7() {
		Path currDir = FileSystems.getDefault().getPath("./tmp");
		if ( ! Files.exists(currDir) ) {
			Set<String> setFSTypesSupported = FileSystems.getDefault().supportedFileAttributeViews();
			if ( setFSTypesSupported.contains("posix") ) {
				try {
					//Files.createDirectory(currDir, PosixFilePermission.GROUP_WRITE );
					
					Set<PosixFilePermission> setOfPosixPerms = new HashSet<>();
					setOfPosixPerms.add(PosixFilePermission.OWNER_READ);
					setOfPosixPerms.add(PosixFilePermission.OWNER_WRITE);
					setOfPosixPerms.add(PosixFilePermission.OWNER_EXECUTE);
					setOfPosixPerms.add(PosixFilePermission.GROUP_WRITE);
					setOfPosixPerms.add(PosixFilePermission.OTHERS_WRITE);
					FileAttribute<Set<PosixFilePermission>> af = PosixFilePermissions.asFileAttribute(setOfPosixPerms);
					Files.createDirectory(currDir, af );
				} catch (IOException e) {
					e.printStackTrace(); return;
				}				
			}
			else {
				try {
					Files.createDirectory(currDir);
				} catch (IOException e) {
					e.printStackTrace(); return;
				}
			}
			
		}
		try {
			
			Path adam = Paths.get("./tmp", "adam");
			
			FileVisitor fv_toRemove = new SimpleFileVisitor<Path>() {
				@Override
				public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
					System.out.println("will delete : " + dir);
					return FileVisitResult.CONTINUE;
				}
				@Override
				public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
					Files.list(dir)
					.forEach(p -> {
						try {
							System.out.println(".. deleting file " + p.toString());
							Files.deleteIfExists(p);
						} catch (IOException e) {		// why need to catch here????
							e.printStackTrace();
						}
					});					
					Files.delete(dir);
					return FileVisitResult.CONTINUE;
				}
			};
			
			// remove all directories
			if ( Files.isDirectory(adam)) {
				Files.walkFileTree(adam, fv_toRemove);
			}
			
			// create structure
			Files.createDirectory(Paths.get(currDir.toString(),"adam" ));
			Files.createDirectory(Paths.get(currDir.toString(),"adam","abel" ));
			Files.createDirectory(Paths.get(currDir.toString(),"adam","kain" ));
			Files.createDirectories(Paths.get(currDir.toString(),"adam","seth","noah" ));
			if ( !Files.exists(Paths.get(currDir.toString(),"eva" )) )
			Files.createDirectory(Paths.get(currDir.toString(),"eva" ) );
			
			String textAdam = "Und Gott der Herr nahm den Menschen und setzte ihn"
					+ " in den Garten Eden, dass er ihn bebaute und bewahrte.";
			
			BufferedWriter bw = Files.newBufferedWriter(Paths.get("./tmp", "adam", "adam.txt"));
			bw.write(textAdam);
			bw.flush();bw.close();		// !!!
			
			String textNoah = "Aber Noah fand Gnade vor dem Herrn.";
			BufferedWriter bw2 = Files.newBufferedWriter(Paths.get("./tmp", "adam","seth","noah", "noah.txt"));
			bw2.write(textNoah);
			bw2.flush();bw2.close();	// !!!
			
			List<Path> list = new ArrayList<>();
			FileVisitor<Path> fv_showContent = new SimpleFileVisitor<Path>() {
				@Override
				public FileVisitResult visitFile( Path p, BasicFileAttributes attr ) throws IOException {
					System.out.println("called for " + p);
					Files.lines(p, Charset.defaultCharset())
					//.peek(System.out::println)
					.forEach(System.out::println);
					return FileVisitResult.CONTINUE;
				}
				
			};

			if ( Files.exists(adam) ) {
				adam = Paths.get("./tmp", "adam");
				adam = Files.walkFileTree(adam, fv_showContent);
				//Files.walk( adam ) 				.forEach(System.out::println);				
			}
			
		}catch ( IOException e ) {
			e.printStackTrace();System.exit(-1);
		}
	}
	
	
	private void test8() {
		Path zoo = Paths.get(".","zoo");
		Path turkey = Paths.get("turkey");
		try {
			boolean test = Files.isSameFile(zoo, turkey);
				// will access fs because both are not equal
			if (test);
				//Files.createDirectory(turkey.resolve("info"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		File f1 = new File("./zoo/cobra.data_NOT_EXISTING");
		File f2 = new File("./zoo/cobra.data_NOT_EXISTING");
		if ( f1.equals(f2) ) {
			System.out.println("same");
		}
	}
	
	private void test9() {
		Path src = Paths.get("./zoo/snake.data");
		Path trg = Paths.get("./zoo/snake2.data");
		try {
			Path trg2 = Files.move(src, trg, StandardCopyOption.ATOMIC_MOVE, LinkOption.NOFOLLOW_LINKS);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void test10() {
		try {
			Path p = Paths.get(new URI("uri"));
			System.out.println(p);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return;
		}
	}
	
	private void test11() {
		Path p1 = Paths.get("zoo/./bears/../codiacbear.txt");
		Path p2 = Paths.get("zoo/birds/../codiacbear.txt");
		System.out.println( "equals : " + p1.equals(p2)  );		// false
		System.out.println( "equals with normalize : " + p1.normalize().equals(p2.normalize())  ); // true
		try {
			System.out.println( "isSameFile : " + Files.isSameFile(p1, p2) );
			// RTE java.nio.file.NoSuchFileException: zoo/./bears/../codiacbear.txt
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
