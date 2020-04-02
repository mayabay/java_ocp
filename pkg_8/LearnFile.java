package pkg_8;
import java.io.*;
public class LearnFile {

	public static void main(String[] args){
		do1();
	}

	static void do1(){
		System.out.println( System.getProperty( "file.separator" ));
	
		System.out.println( File.separator );

		File home = new File("c:/users/andreas");

		File[] files = home.listFiles();

		for( File f : files ){
			System.out.println( f );

		}

		pfp(home);

	}

	static void pfp (File f){
		System.out.println( "name\t\t" + f.getName() );
		System.out.println( "length\t\t" + f.length() );
		System.out.println( "lastModified\t\t" + f.lastModified() );

	}

}
