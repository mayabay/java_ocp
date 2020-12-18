/**
 * KB 12
 */
package pkg_10;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC with bobsbooks database
 * 
 */
public class BobsBooksTest1 {

	private static final String jdbcUrlBobsBooks = "jdbc:mysql://10.0.0.205:3306/bobsbooks?serverTimezone=Europe/Rome";
	private static final String jdbcUserBobsBooks = "bob";
	private static final String jdbcPasswordBobsBooks = "Pa$$w0rd";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BobsBooksTest1 bbt1 = new BobsBooksTest1();
		bbt1.listDBData();

	}

	
	/*
	 * ResultSet.TYPE_FORWARD_ONLY
	 * 
	 * */
	private void listDBData() {
		try( Connection conn = DriverManager.getConnection(jdbcUrlBobsBooks, jdbcUserBobsBooks, jdbcPasswordBobsBooks);
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				){
			
			// list all tables
			System.out.println("-- all tables ----------");
			String sqlAllTables = "SHOW TABLES;";
			ResultSet rs =  stmt.executeQuery(sqlAllTables);
			while( rs.next() ) {
				System.out.println(rs.getString(1));	// RS columns indexes start with 1
			}

//			author
//			book
//			books_by_author
//			customer			
			
			System.out.println("-- book data ----------");
			rs = stmt.executeQuery("SELECT * FROM book ORDER BY PubDate;");
			while( rs.next() ) {
				System.out.print("ISBN = " + rs.getString("ISBN") + ", " );
				System.out.print("Title = " + rs.getString("TITLE") + ", " );
				//System.out.print("PubDate = " + rs.getDate("PubDate") + ", " );
				System.out.printf("Pubdate = %1$tF, ", rs.getDate("PubDate"));
				System.out.print("BookFormat = " + rs.getString("BookFormat") + ", " );
				System.out.printf( "UnitProce = %1$3.2f", rs.getFloat("UnitPrice") );
				System.out.println();
			}
			System.out.println("-- navigate in rs --------");
			rs.afterLast();
			rs.relative(-1);
			System.out.println("Title = " + rs.getString("TITLE") + ", " );
			rs.absolute(3);
			System.out.println("Title = " + rs.getString("TITLE") + ", " );
			
			
			
		}catch( SQLException e ) {
			e.printStackTrace();
		}
		System.out.println("end");
	}
	
}
