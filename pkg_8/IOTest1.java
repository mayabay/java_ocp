/**
 * review bs ch 8
 */
package pkg_8;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 *
 */
public class IOTest1 {

	private Random r = new Random();
	
	private Supplier<String> strSupp = () -> {
		String result =
		r.ints( 1024, 0, 255 )
		.filter( i -> i >= 65 && i <= 90 )
		.limit(128)
		.mapToObj( i -> new Character( (char)i) )
		.map( c -> c.toString() )
		.collect( Collectors.joining() );		
		return result;
	};

	private static final int LINE_CHAR_COUNT = 1024;
	
	private static final int FILE_COUNT = 16;
	
	private Supplier<Character> charSupp = () -> {
		
		while (true) {
			char v = (char)r.nextInt(90);
			if ( v > 64 ) { return new Character(v); }
		}
		
	};
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IOTest1 iot1 = new IOTest1();
		//iot1.test1();
		iot1.createWorkingDir();
		iot1.writeFile();
		
	}

	private void test1() {
		File f = new File("/nothing/here");
		System.out.println( f.toString() +  ", exists? : " + f.exists() );
	}
	
	private void createWorkingDir() {
		
		File currentDir = new File(".");
		System.out.println("working dir : " + currentDir.getAbsolutePath());
		
		File f = new File("./tmp");
		if (!f.exists())
			f.mkdir();
		else {
			this.clearDirectory(f);
			f.mkdir();
		}
	}
	
	private void clearDirectory( File f ) {
		if (f.isDirectory()) {
			for( File fin : f.listFiles() ) {
				clearDirectory(fin);
			}
			if (f.delete()) 
				System.out.println( f.getAbsolutePath() + "deleted." );
		}else {
			if (f.delete())
				System.out.println(f.getAbsolutePath() + " deleted.");
		}
	}
	
	private File getWorkingDir() {
		return new File("./tmp").getAbsoluteFile();
	}
	
	private void writeFile() {
		
		File[]  files = new File[FILE_COUNT];
		
		for( int i = 0; i < files.length; i++ ) {

			LocalDateTime ldt = LocalDateTime.now();
			File toWrite = new File( 
					getWorkingDir().toString() +
						"/aFile" +
							ldt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME).replace(':', '-') +
							this.charSupp.get() +
						".txt"
					);
			files[i] = toWrite;
			if ( !toWrite.exists() )
				toWrite.delete();

			try( FileOutputStream fout = new FileOutputStream(toWrite);
				OutputStreamWriter osw = new OutputStreamWriter(fout);
					//FileWriter fr = new FileWriter(osw); // DNC fr is  low level stream
					){
				for(int k = 0; k< LINE_CHAR_COUNT; k++) {
					osw.write( this.strSupp.get() + System.lineSeparator() );
				}
				
			}catch( IOException io ) {
				io.printStackTrace();System.exit(-1);
			}
			
			if ( toWrite.exists() )	
				System.out.println( toWrite.toString() + " has been written." );			
			
		} // file write loop
	}
	
	private void countCharInData( char ... c ) {
		File dataDir = this.getWorkingDir();
				
				
	}
}
