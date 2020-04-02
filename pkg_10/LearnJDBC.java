/*
 * java -cp ".;D:\projects\java_libs\sqlite-jdbc-3.30.1.jar" pkg_10/LearnJDBC
 * */
package pkg_10;
import java.sql.*;
public class LearnJDBC{

	
	public static void main(String[] args){
		
		try(
		Connection con = DriverManager.getConnection("jdbc:sqlite:D:\\projects\\java_ocp\\zoo.db");
		)
		{
			System.out.println( con );
	
		}catch( SQLException sqle ){
			System.out.println( sqle.getMessage() );

		}


	}


}
