/*
 * java -cp ".;D:\projects\java_libs\sqlite-jdbc-3.30.1.jar" pkg_10/LearnJDBC
 * */
package pkg_10;
import java.io.IOException;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
public class LearnJDBC{

	
	public static void main(String[] args) throws IOException{
		
		Path pathZooDB = Paths.get(".","src","zoo.db").toRealPath(LinkOption.NOFOLLOW_LINKS) ;
		
		try(
		Connection con = DriverManager.getConnection("jdbc:derby:" + pathZooDB.toString());
		)
		{
			System.out.println( con );
	
		}catch( SQLException sqle ){
			System.out.println( sqle.getMessage() );

		}


	}


}
