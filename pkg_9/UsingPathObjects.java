/**
 * BS 9.2.2
 */
package pkg_9;

import java.io.IOException;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * methods of the Path interface
 */
public class UsingPathObjects {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		UsingPathObjects upo = new UsingPathObjects();
		//upo.test1();	

		Path path = Paths.get(pkg_8.ConfigObj.IMMO_DATA_PATH);
		//upo.viewPaths(path);
		
		//upo.accessPathComponents(path);
		//upo.detectAbsolutePath(path);
		//upo.relativizePaths();
		upo.morePathConfusion();
	}

	private void test1() {
		Path path = Paths.get(pkg_8.ConfigObj.IMMO_DATA_PATH);
		Path path2 = Paths.get("/", "home", "andreas", "immodata", "..", "log");	// /home/andreas with log or log/
		//Path path2 = Paths.get("/home/andreas/../log");	// /home
		System.out.println(path2.getParent().normalize().toAbsolutePath());
		
	}
	
	private void printPathElems( Path path ) {
		for ( int i = 0; i < path.getNameCount(); i++ ) {
			System.out.println("\t element of path = " + path.getName(i));
		}		
	}
	
	private void printParentPath( Path path ) {
		Path parentPath = path.getParent();
		if ( parentPath == null  ) {
			System.out.println("parent of " + path + " = null"   );	
			return;
		}
		
		System.out.println("parent of " + path + " = " + parentPath );
		this.printParentPath(parentPath);
	}
	
	private void viewPaths( Path path ) {
		// paths submitted = /home/andreas/immodata, name count = 3
		System.out.println("paths submitted = " + path + ", name count = " + path.getNameCount());
		
		this.printPathElems(path);

		System.out.println("----------");
		
		Path relPath = Paths.get("zoo", "hippo", "harry.happy");
		
		this.printPathElems(relPath);
	}
	
	private void accessPathComponents( Path path ) {
		
		System.out.println("file/directory name = " +  path.getFileName() );
		Path endLocation = path.getName(path.getNameCount()-1);
		System.out.println("end location = " + endLocation);
		
		Path relPath = Paths.get("zoo", "hippo", "harry.happy");
		
		System.out.println("root of rel path = " + relPath.getRoot()); // null, relative path has no root
		
		System.out.println("----------");
		this.printParentPath(path);
		System.out.println("----------");
		System.out.println("rel path " + relPath + ", as absolute = " + relPath.toAbsolutePath());
		this.printParentPath(relPath);
		
	}
	
	private void detectAbsolutePath( Path path ) {
		System.out.println("path " + path + " is absolute = " + path.isAbsolute());
		
		Path relPath = Paths.get("zoo", "hippo", "harry.happy");
		Path abs = relPath.toAbsolutePath();
		System.out.println("abs = " + abs );
				
	}
	
	private void relativizePaths() {
		
		Path path1 = Paths.get("zoo/armadillo/arma.txt");
		Path path2 = Paths.get("zoo/zoodb.txt");
		System.out.println(path1.relativize(path2));	// ../zoodb.txt
			// ../../zoodb.txt
		System.out.println(path2.relativize(path1));	// armadillo/arma.txt
			// ../armadillo/arma.txt
		
		System.out.println("----------");
		Path path3 = Paths.get("zooSF");	// same as ./zooSF
		Path path4 = Paths.get("zooNY");	// same as ./zooNY	
		
		System.out.println(path3.relativize(path4));
			// ../zooNY
		System.out.println(path4.relativize(path3));
			// ../zooSF
		
		System.out.println("----------");
		Path path5 = Paths.get("/data");
		Path path6 = Paths.get("/user/home");
		Path relPath = path5.relativize(path6); // ../user/home
		
		System.out.println(relPath);
		System.out.println(path5.resolve(relPath));	// /data/../user/home
		System.out.println(path5.resolve(relPath).normalize());	// /user/home
		
		
	}
	
	private void morePathConfusion() {
		try {
			// print current working directory
			System.out.println(Paths.get(".").toRealPath(LinkOption.NOFOLLOW_LINKS));
				// /home/andreas/eclipse-workspace/java_ocp 
			
		}catch( IOException e ) {
			e.printStackTrace();
		}
	}
	 
}
