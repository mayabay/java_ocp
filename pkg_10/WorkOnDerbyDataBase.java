/*
 * BS 10 
 */
package pkg_10;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * sql chapter 10
 *
 */
public class WorkOnDerbyDataBase {

	/**
	 * jdbc url for zoodb
	 * */
	public static final String url = "jdbc:derby:/tmp/zoodb";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		WorkOnDerbyDataBase wodb = new WorkOnDerbyDataBase();
		//wodb.test1();
		//wodb.test2();
		wodb.test3();

	}

	private static Connection getConn() {
		return null;
	}
	
	private void test1() {
		
		try(
				Connection conn = DriverManager.getConnection(url);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT date_born FROM animal WHERE name = 'Elsa'");
			){
		    
		    if ( rs.next() ) {
		    	java.sql.Date sqlDate = rs.getDate(1);
		    	System.out.println("sql DATE : " + sqlDate);
		    	LocalDate localDate = sqlDate.toLocalDate();
		    	System.out.println("local DATE : " + localDate);
		    	
		    	java.sql.Time sqlTime = rs.getTime(1);
		    	System.out.println("sql TIME : " + sqlTime);
		    	LocalTime localTime = sqlTime.toLocalTime();
		    	System.out.println("local TIME : " + localTime);
		    	
		    	java.sql.Timestamp sqlTimestatmp = rs.getTimestamp(1);
		    	System.out.println("sqlTimestatmp : " + sqlTimestatmp);
		    	LocalDateTime ldt = sqlTimestatmp.toLocalDateTime();
		    	System.out.println("ldt : " + ldt);
		    	
		    }
		    
		    
		}catch( SQLException e ) {
			e.printStackTrace();
		}			
		
	}
	
	private void test2() {
		try(
				Connection conn = DriverManager.getConnection(url);
				//Statement stmt = conn.createStatement(  );
				Statement stmt = conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY );
				ResultSet rs = stmt.executeQuery("SELECT id FROM species ORDER BY id");
				){
		    
		    rs.afterLast();	
		    	// with def rs 
		    	// java.sql.SQLException: The 'afterLast()' method is only allowed on scroll cursors.
		    System.out.println("isAfterLast : " + rs.isAfterLast());
		    System.out.println("first() : " + rs.first() + ", id = " + rs.getInt("id"));
		    System.out.println("next() : " + rs.next() + ", id = " + rs.getInt("id"));
		    System.out.println("previous() : " + rs.previous() + ", id = " + rs.getInt("id"));
		    System.out.println("last() : " + rs.last() + ", id = " + rs.getInt("id"));
		    
		}catch( SQLException e ) {
			e.printStackTrace();
		}						
	}
	
	private void test3() {
		try(
				Connection conn = DriverManager.getConnection(url);
				Statement stmt = conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY );
				ResultSet rs = stmt.executeQuery("SELECT id, name FROM animal ORDER BY id");
				){
		    

		    while( rs.next() ) {
		    	System.out.println( "id = " + rs.getInt("id") + ", name = " + rs.getString("name") );
		    }
		    System.out.println("----------");
		    
		    rs.beforeFirst();
		    System.out.println("next() : " + rs.next() + ", id = " + rs.getInt("id"));
		    System.out.println("next() : " + rs.next() + ", id = " + rs.getInt("id"));
		    System.out.println("relative(4) : " + rs.relative(4) + ", isAfterLast() = " + rs.isAfterLast() );
		    System.out.println("relative(-1) : " + rs.relative(-1) + ", id = " + rs.getInt("id"));
		    
		}catch( SQLException e ) {
			e.printStackTrace();
		}						
	}	
	
}
