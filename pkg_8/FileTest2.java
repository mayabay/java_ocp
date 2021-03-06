/**
 * BS 8
 */
package pkg_8;

import java.io.File;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.function.Function;

/**
 * Basic File IO
 *
 */
public class FileTest2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FileTest2 ft2 = new FileTest2();
		ft2.test1();
	}
	
	private void test1() {
		File currentWorkDir = new File(".");
		System.out.println(". absolute path = " +  currentWorkDir.getAbsolutePath() );
		
		File tmpDir = new File("./tmp");
		System.out.println( "./tmp exists = " + tmpDir.exists() );
		tmpDir.mkdir();
		System.out.println( "./tmp exists = " + tmpDir.exists() );
		
		String[] dirEntries = currentWorkDir.list();
		
		Map<String, Long> fileMap =
		Arrays.stream(dirEntries, 0, dirEntries.length)
			.filter(s -> new File(s).isFile())
			.collect( Collectors.toMap(Function.identity(), s -> { File f = new File(s); return f.length(); }  ) );
		
		System.out.println(fileMap);
		
		// delete tmp on exit
		currentWorkDir.deleteOnExit();
	}

}
