/**
 * 
 */
package review_bs;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.BasicFileAttributeView;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
		obj.test7();
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
	}
	
	private void test7() {
		Path currDir = FileSystems.getDefault().getPath("./tmp");
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
	
}
