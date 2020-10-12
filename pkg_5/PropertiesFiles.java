/**
 * Learn about properties files
 */
package pkg_5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

/**
 * 
 *
 */
public class PropertiesFiles {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		
		PropertiesFiles pf = new PropertiesFiles();
		pf.printWorkingDir();
		pf.readAndPrint();

	}

	private void printWorkingDir() {
		
		// https://stackoverflow.com/questions/4871051/how-to-get-the-current-working-directory-in-java
		
		// io
		System.out.println("Working Directory = " + System.getProperty("user.dir"));
		// nio
		Path currentRelativePath = Paths.get("");
		
	}
	
	private void readAndPrint() {
		
		Properties appProps = loadAndGetProperties( Paths.get("src\\pkg_5\\app.properties") );
		
		System.out.println(appProps);	// {key=value}
		
		System.out.println("---"); 
		
		Set<String> keyStrSet  = appProps.stringPropertyNames();
		
		for( String key : keyStrSet ) {
			System.out.println(key + " : " + appProps.getProperty( key ) );
		}
		
		appProps.setProperty("keyC", "valueC");
		
		//appProps.list(System.out);
		
		//appProps.setProperty("keyD", "valueD");
		
		//storeProperties(appProps, Paths.get("src\\pkg_5\\app.properties"));
	}
	
	
	/**
	 * Loads properties from given location
	 * 
	 * @param Path pathToFile
	 * @return Properties
	 * */
	public Properties loadAndGetProperties( Path pathToFile ) {
		Properties properties = new Properties();
	
		File f = pathToFile.toFile() ;
		
		try( BufferedReader in = new BufferedReader(new FileReader(f)) )  {
			
			properties.load(in);
		}catch(IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}	
		return properties;
	}
	
	/**
	 * stores properties to given location
	 * 
	 * @param Properties properties
	 * @param Path pathToFile
	 * */
	public void storeProperties( Properties properties, Path pathToFile ) {
		File f = pathToFile.toFile() ;
		
		try( BufferedWriter out = new BufferedWriter( new FileWriter( f ) ) ){
			// if file exists, current values will be added to existing values!
			if (f.exists())
				f.delete();			
			
			properties.store(out, "demo properties");
		}catch( IOException e ) {
			e.printStackTrace();
			System.exit(-1);			
		}
		
	}
	
	
}
