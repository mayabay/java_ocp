/*
 * BS 10 Setup the derby demo database 
 */
package pkg_10;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 *
 */
public class SetupDerbyDatabase {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		Path workingDir = Paths.get(".");
		System.out.println( workingDir.toAbsolutePath().normalize() );
		
			// /home/andreas/eclipse-workspace/java_ocp/.				
			// /home/andreas/eclipse-workspace/java_ocp			normalize()
		
		//create();
		connect();
	}
	
	private static void create () throws SQLException {
		String url = "jdbc:derby:/tmp/zoodb;create=true";

		try(
				Connection conn = DriverManager.getConnection(url);
				Statement stmt = conn.createStatement();
				){
			
			//stmt.executeUpdate("DROP DATABASE zoo");
			//stmt.executeUpdate("CREATE DATABASE zoo");
			
		    //stmt.executeUpdate("DROP TABLE animal");
		    //stmt.executeUpdate("DROP TABLE species");			
			
			// (1) create tables
			stmt.executeUpdate("CREATE TABLE species ( "
					+ "id INTEGER PRIMARY KEY, "
					+ " name VARCHAR(255), "
					+ " num_acres DECIMAL(4,1) "
					+ ") ");
			stmt.executeUpdate("CREATE TABLE animal ( "
					+ "id INTEGER PRIMARY KEY, "
					+ "species_id INTEGER REFERENCES species (id), "
					+ "name VARCHAR(255), "
					+ "date_born TIMESTAMP "
					+ ")");
			
			// (2) insert data
			stmt.executeUpdate("INSERT INTO species VALUES (1,'African Elefant', 7.5)");
			stmt.executeUpdate("INSERT INTO species VALUES (2,'Zebra', 1.2)");

			stmt.executeUpdate("INSERT INTO animal VALUES (1, 1, 'Elsa', '2001-05-06 02:15:00')");
		    stmt.executeUpdate("INSERT INTO animal VALUES (2, 2, 'Zelda', '2002-08-15 09:12:00')");
		    stmt.executeUpdate("INSERT INTO animal VALUES (3, 1, 'Ester', '2002-09-09 10:36:00')");
		    stmt.executeUpdate("INSERT INTO animal VALUES (4, 1, 'Eddie', '2010-06-08 01:24:00')");
		    stmt.executeUpdate("INSERT INTO animal VALUES (5, 2, 'Zoe', '2005-11-12 03:44:00')");			
			
		    ResultSet rs = stmt.executeQuery("select count(*) from animal");
		    rs.next();
		    System.out.println("animals in tables : " + rs.getInt(1));			
		}			
	}
	
	private static void connect() throws SQLException {
		String url = "jdbc:derby:/tmp/zoodb";

		try(
				Connection conn = DriverManager.getConnection(url);
				Statement stmt = conn.createStatement();
				){
		    ResultSet rs = stmt.executeQuery("select count(*) from animal");
		    rs.next();
		    System.out.println("animals in tables : " + rs.getInt(1));				
		}		
	}
}
