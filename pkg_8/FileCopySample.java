package pkg_8;
import java.io.*;
public class FileCopySample{

	public static void main(String[] args){
		do1();
	}

	static void do1(){
		
		File f = new File("c:/users/andreas/LR1_1.txt");

		InputStream in = null;;

		if ( !f.exists() ){
			System.out.println( "cannot find file" );
			return;
		}

		byte[] bArr;
		byte b1;
		int b2;

		try{
			in = new FileInputStream( f );
			while( (b2 = in.read()) != -1 ){
				System.out.println( b2 );
			}
			in.close();
		}
		catch( IOException ioex ){
			System.out.println( ioex.getMessage() );
			return;
		}

	}
}
