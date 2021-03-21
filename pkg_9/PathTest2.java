/**
 * 
 */
package pkg_9;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.UserPrincipal;
import java.nio.file.attribute.UserPrincipalLookupService;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 *
 */
public class PathTest2 {

	AtomicInteger ai = new AtomicInteger();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PathTest2 pt2 = new PathTest2();
		//pt2.test1();
		//pt2.checkFS();
		try {
			pt2.workOn( Paths.get(".", "tmp") );	
		}catch( IOException e ) {
			e.printStackTrace();
		}
		
	}

	private void checkPath(Path p) {
		// Path p1 = Paths.get(".", "tmp");
		System.out.println("given : " + p);
		System.out.println("root : " + p.getRoot() );		// null
		System.out.println("parent : " + p.getParent() );	// .
		System.out.println("normalized() : " + p.normalize() ); // tmp
		System.out.println("name count : " + p.getNameCount());	// 2
		System.out.println("resolve with sub/../Nautilus : " + p.resolve(Paths.get( "sub", ".." ,"Nautilus" )) );
			// resolve with sub/../Nautilus : .\tmp\sub\..\Nautilus
		System.out.println("normalized : " + p.resolve(Paths.get( "sub", ".." ,"Nautilus" )).normalize()  );
			// normalized : tmp\Nautilus
	}
	
	private void test1() {
		Path p1 = Paths.get(".", "tmp");
		this.checkPath(p1);
	}
	
	private void checkFS() {
		Path p = Paths.get(".");
		System.out.println("using path : " + p);
		FileSystem fs =  p.getFileSystem();
		System.out.println("root dirs : " + fs.getRootDirectories()); 
		 	// root dirs : [B:\, C:\, D:\, E:\, F:\, H:\, M:\, P:\, S:\, W:\]
		
		System.out.println("seperator : " +  fs.getSeparator() );// \
		
		try {
			UserPrincipalLookupService upls =  fs.getUserPrincipalLookupService();
			UserPrincipal up =  upls.lookupPrincipalByName("admin");
			System.out.println("admin Userprincipal : " + up.getName()); 
		}catch ( IOException e ) {
			e.printStackTrace();
		}
	}
	
	private synchronized void workOn( Path p ) throws IOException {
		System.out.println("content of : " + p);
		List<Path> paths = 
		Files.list(p)
		.filter(e -> e.toString().endsWith("txt"))
		.peek(e -> System.out.println(e + " : [" + ai.getAndIncrement() + "]" ))
		//.collect( Collectors::toList );	// DNS collector is not a functional interface
		.collect( Collectors.toList() );
		//.forEach(System.out::println);
		
		// Constructs a new Scanner that produces values scanned from the specified input stream.
		Scanner scanner = new Scanner(System.in);
		this.printHelp();
		LOOP: while( true ) {
			String input = scanner.next();
			
			// if ( input.length() > 2  ) continue;
			char[] chars = input.toCharArray();
			if ( chars.length == 0 ) {
				continue;
			}
			input = String.valueOf(chars[0]);
			
			int idxFileSelected = -1;
			if ( chars.length > 1 ) {
				StringBuffer buf = new StringBuffer();
				for( int i = 1; i < chars.length; i++  ) { buf.append(chars[i]); }
				idxFileSelected = Integer.parseInt(buf.toString());
				if (idxFileSelected >= paths.size()) break LOOP; 		
			}

			switch( input ) {
				case "c" : break LOOP;
				case "r": if (idxFileSelected != -1) {printFileContent( paths, idxFileSelected );break;} else break;
				case "d": if (idxFileSelected != -1) {deleteFile( paths, idxFileSelected );break;} else break;
				case "h" : this.printHelp(); break;
				default : System.out.println("?");
			}
		}
		System.out.println("end");
		scanner.close();
	}
	
	private void printFileContent( List<? extends Path> paths, int idxFileSelected ) {
		System.out.println("..");
		File f = paths.get(idxFileSelected).toFile();	// works only with upper bound 
		 
		try ( BufferedReader br =  new BufferedReader( new FileReader( f ) ) ) {
			String line = null;
			while( (line = br.readLine()) != null ) {
				System.out.println(line);
			}
		}catch( IOException e ) {
			e.printStackTrace();
		}
		System.out.println("..");
	}
	
	private void deleteFile( List<? extends Path> paths, int idxFileSelected  ) {
		File f = paths.get(idxFileSelected).toFile();
		if ( !f.exists() )
			return;
		f.delete();
	}
	
	private void printHelp() {
		System.out.println("command");
		System.out.println("show (h)elp");
		System.out.println("(r)ead a file (e.g. r3: reads file 3");
		System.out.println("(d)elete a file (d4 deletes file 4)");
	}
}
