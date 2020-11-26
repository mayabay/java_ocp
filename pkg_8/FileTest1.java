/**
 * BS 8.1
 */
package pkg_8;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * 
 *
 */
public class FileTest1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FileTest1 ft1 = new FileTest1();
		//ft1.printSystemEnvironment();
		ft1.test1();
		
	}
	
	private void printSystemEnvironment() {
		Map<String, String> env =  System.getenv();
		Set<String> keys =  env.keySet();
		for ( String key : keys ) {
			System.out.println(key + " : " + env.get(key));
		}
		
	}
	
	private void test1() {
		
		// USERDOMAIN
		System.out.println("current user is " + System.getenv("USERDOMAIN"));
		
		// get users home directory
		String strPathUserHome =  System.getenv("USERPROFILE");
		System.out.println("user home is " + strPathUserHome);
		
		System.out.println("path seperator : " +  File.separatorChar);
		
		File homeDir = new File( System.getenv("USERPROFILE") );
		File[] files = homeDir.listFiles();
//		for ( File f : files ) {
//			//System.out.println(f);
//		}

		Predicate<File> isFile = File::isFile;	// method reference instance method
		Predicate<File> logInName = file -> file.getName().contains( "log" ) ;
		Predicate<File> lengthGreaterThan0 = file -> file.length() > 0;
		
		Predicate<File> targetCond = isFile.and(logInName).and(lengthGreaterThan0);
		
		Consumer<File> fileConsumer = file -> { 
			System.out.println( file.getName() + " : " + file.length() + " bytes" );
		};
		
		Arrays.stream(files)
			//.filter(isFile)
			//.filter( f -> f.isFile() )
			//.filter(f -> f.getName().contains(".log"))
			.filter(targetCond)
			//.forEach(System.out::println);
			.forEach(fileConsumer);
		
		StringBuffer buf = new StringBuffer();
		// C:\Users\Andreas\vimfiles
		File rootDir = new File (strPathUserHome + File.separatorChar + "immodata");
		System.out.println( rootDir.getAbsolutePath() );
		System.out.println( "isDir " + rootDir.isDirectory() );
		//System.out.println( rootDir.listFiles() );
		buf = this.displayDirTree(rootDir, buf, 0);
		System.out.println(buf);
	}

	private StringBuffer displayDirTree( File dir, StringBuffer buf, int level ) {
		if ( dir.listFiles().length == 0 ) {
			level--;
			return buf;
		}
		// sub folder
		level++;
		buf.append("\n");
		
		for ( File subDir : dir.listFiles() ) {
			
			//if ( subDir.isFile() ) continue;
			if ( subDir.isFile() ) {
				this.printFileBytes(subDir);
			}
			
			for(int i = 0; i < level; i++) {
				//System.out.print("\\t" + subDir.getName());
				buf.append("\t");
			}
			buf.append(" - " + subDir.getName() + " |"+level+"| " +  "\n");
			
			this.displayDirTree(subDir, buf, level);
		}
		return buf;
	}
	
	private void printFileBytes( File file ) {
		try(BufferedInputStream in = new BufferedInputStream(new FileInputStream(file))) {
			int byteRead = 0;
			while( (byteRead = in.read()) != -1 ) {
				System.out.println(byteRead);
			}
		}catch( IOException e ) {
			e.printStackTrace();
		}
	}
	
}
