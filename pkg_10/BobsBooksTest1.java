/**
 * KB 12
 */
package pkg_10;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;



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
		//bbt1.listDBData();
		//bbt1.printTableData("Customer");
		bbt1.printDBMetaData();
		//bbt1.updatesWithResultSet();
	
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
			rs = stmt.executeQuery("SELECT * FROM book ORDER BY ISBN;");
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
	
	private void printTableData( String tableName ) {
		try( Connection conn = DriverManager.getConnection(jdbcUrlBobsBooks, jdbcUserBobsBooks, jdbcPasswordBobsBooks);
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				){
			boolean hasResultSet = stmt.execute("SELECT * FROM "+tableName+"");
			if (hasResultSet) {
				// get rs
				ResultSet rs = stmt.getResultSet();
				// get meta data
				ResultSetMetaData rsMeta = rs.getMetaData();
				// how many columns in rs?
				int colCount = rsMeta.getColumnCount();
				System.out.println("Table name: " + rsMeta.getTableName(1));
				System.out.println("Columns: " + colCount);
				
				// collect all names
				Set<String> colNames = new LinkedHashSet<>();
				// collect data foreach column
				Map<String,Map<String,Object>> tableData = new LinkedHashMap<>();
				
				for ( int i = 1; i <= colCount; i++ ) {
					System.out.print(rsMeta.getColumnName(i) + "(ds="+rsMeta.getColumnDisplaySize(i)+") | ");
					colNames.add(rsMeta.getColumnName(i));
					
					LinkedHashMap<String,Object> colMap =   new LinkedHashMap<>();
					colMap.put("tableName", rsMeta.getTableName(i));
					colMap.put("colName", rsMeta.getColumnName(i));
					colMap.put("colDisplaySize", rsMeta.getColumnDisplaySize(i));
					colMap.put("colType", rsMeta.getColumnType(i) );
					tableData.put(rsMeta.getColumnName(i), colMap);
					
				}
				//System.out.println("colNames count: " + colNames.size());
				
				//System.out.println();
				//System.out.println(colNames);
				System.out.println();
				
				System.out.println("--data----------");
				// get ddata
				while( rs.next() ) {
					// for each row, work on columns
					StringBuffer rowBuf = new StringBuffer();
					
//					Iterator<String> iter = colNames.iterator();
//					while( iter.hasNext() ) {
//						String s = iter.next();
//						Map colMap = tableData.get(s);
//						int ds = (Integer)colMap.get("colDisplaySize");
//						Object val = rs.getObject(s);
//						String formatColValue = String.format("%1$"+ds+"s ", val);
//						rowBuf.append(formatColValue);
//					}
					
					colNames.forEach(s -> { 
						try {
							//System.out.print( s );
							// get column data
							Map colMap = tableData.get(s);	// is is column name
							//System.out.print( "data for " + s );
							
							int ds = (Integer)colMap.get("colDisplaySize");
							
							// value of col
							Object val = rs.getObject(s);
							// get value
							//System.out.print( val );
							String formatColValue = String.format("%1$-"+(ds > 200 ? (ds-220): ds) +"s ", val);
							//System.out.print(formatColValue);
							//System.out.flush();
							rowBuf.append(formatColValue);
							
						} catch (SQLException e) {
							e.printStackTrace();
						}
					} );
					
					// \n
					System.out.println( rowBuf );
				} // rows
			}
			
		}catch( SQLException e ) {
			e.printStackTrace();
		}
		
	}
	
	private void printDBMetaData() {
		try( Connection conn = DriverManager.getConnection(jdbcUrlBobsBooks, jdbcUserBobsBooks, jdbcPasswordBobsBooks);
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				){
			
			DatabaseMetaData dbmd = conn.getMetaData();
			System.out.println("-- DB and Conn props --------");
			
			System.out.println("DatabaseProductName: " + dbmd.getDatabaseProductName());
			System.out.println("\tversion major: " + dbmd.getDatabaseMajorVersion());
			System.out.println("\tversion minor: " + dbmd.getDatabaseMinorVersion());
			System.out.println("DriverName: " + dbmd.getDriverName());
			System.out.println("DriverVersion: " + dbmd.getDriverVersion());
			
			System.out.println("DefaultTransactionIsolation: " + dbmd.getDefaultTransactionIsolation());
			System.out.println("TransactionIsolation level of current conn. : " + conn.getTransactionIsolation());
			System.out.println("conn. is autoCommit : " + conn.getAutoCommit() );
			System.out.println("supportsANSI92EntryLevelSQL : " + dbmd.supportsANSI92EntryLevelSQL() );
			System.out.println("SQLStateType (mysql: sqlStateSQL99) : " + dbmd.getSQLStateType() );
			
			
			System.out.println("-- RS configuration ----------");
			System.out.println("supp. concurr. TYPE_FORWARD_ONLY/CONCUR_READ_ONLY : " +  dbmd.supportsResultSetConcurrency(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY) );
			System.out.println("supp. concurr. TYPE_FORWARD_ONLY/CONCUR_UPDATABLE : " +  dbmd.supportsResultSetConcurrency(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE) );
			System.out.println("supp. TYPE_FORWARD_ONLY : " +  dbmd.supportsResultSetType(ResultSet.TYPE_FORWARD_ONLY) );
			System.out.println("supp. TYPE_SCROLL_INSENSITIVE : " +  dbmd.supportsResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE) );
			System.out.println("supp. TYPE_SCROLL_SENSITIVE : " +  dbmd.supportsResultSetType(ResultSet.TYPE_SCROLL_SENSITIVE) );
			System.out.println("supp. updates are detected (in type SCROLL_IN) : " + dbmd.updatesAreDetected(ResultSet.TYPE_SCROLL_INSENSITIVE));
			System.out.println("supp. deleted are detected (in type SCROLL_IN) : " + dbmd.deletesAreDetected(ResultSet.TYPE_SCROLL_INSENSITIVE));
			System.out.println("\tfalse means deleted rows are removed from rs");
			
			System.out.println("-- supp. ----------");
			if (dbmd.supportsResultSetType(ResultSet.TYPE_FORWARD_ONLY) 
					) {
				if (dbmd.supportsResultSetConcurrency(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE) ) {
					System.out.println("OK : FWD and UPDATEABLE");
				}
			}
			if (dbmd.supportsResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE) 
					) {
				if (dbmd.supportsResultSetConcurrency(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE) ) {
					System.out.println("OK : SCROLL_INSEN and UPDATEABLE");
				}
			}			
			if (dbmd.supportsResultSetType(ResultSet.TYPE_SCROLL_SENSITIVE) 
					) {
				if (dbmd.supportsResultSetConcurrency(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE) ) {
					System.out.println("OK : SCROLL_SEN and UPDATEABLE");
				}
			}				
			
			System.out.println("-- query ---------");
			ResultSet rs = stmt.executeQuery("SELECT * FROM customer;");
			rs.last();
			System.out.println("last row number: " + rs.getRow() ); 
			rs.beforeFirst();
			while( rs.next() ) {
				int customerId = rs.getInt("CustomerId");
				System.out.println("CustomerId : " + customerId);
				if (customerId == 5007) {
					System.out.println("\t *** rs has rows : "+this.getRowCount(rs)+" ***");
				}
			}
			
			/* 
			 * https://stackoverflow.com/questions/7942520/relationship-between-catalog-schema-user-and-database-instance
			 * https://stackoverflow.com/questions/38557956/databasemetadatagetcolumns-returns-an-empty-resultset
			 */
			System.out.println("-- getColumns() ---------");
			//conn.setCatalog("bobsbooks");
//			ResultSet rsTables = dbmd.getColumns(null, null, "author", null);
//			while( rsTables.next() ) {
//				System.out.printf("%1$s %2$s %3$s %4$s %n", rs.getString("TABLE_CAT"),
//						rs.getString("COLUMN_NAME"),
//						rs.getString("TYPE_NAME"),
//						rs.getString("COLUMN_SIZE")
//						);
//			}
			
		}
		catch( SQLException e ) {
			e.printStackTrace();
		}		
		
		
	}
	
	private int getRowCount( ResultSet rs ) throws SQLException {
		// save curr pos
		int currentCursorPos = rs.getRow();	// if 0 could be before OR after
		if (rs.isAfterLast()) currentCursorPos = -1;
		if (rs.isBeforeFirst()) currentCursorPos = 0;
		// get max row 
		int numRows = -1;
		if (rs.last()) numRows = rs.getRow();
		// return rs cursor
		if ( currentCursorPos == -1 ) rs.afterLast();
		else if ( currentCursorPos == 0 ) rs.beforeFirst();
		else rs.absolute(currentCursorPos);
		
		return numRows;
	}
	
	private void printTable(ResultSet rs) throws SQLException {
		System.out.printf( "'%1$8s' '%2$20s' '%3$20s' %n", "AuthorID","FirstName","LastName");
		System.out.println("---------------------------------------------------------------");
		// (2) print data
		while( rs.next() ) {
			System.out.printf( "'%1$8d' '%2$20s' '%3$20s' %n", rs.getInt("AuthorId"),
					rs.getString("FirstName"), rs.getString("LastName") );
		}		
	}
	
	/* 
	 * execute 'DELETE FROM author WHERE AuthorID = 1010;' before running method
	 */
	private void updatesWithResultSet() {
		try( Connection conn =  DriverManager.getConnection(jdbcUrlBobsBooks, jdbcUserBobsBooks, jdbcPasswordBobsBooks);
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE); ){
			
			// (1) get rs
			ResultSet rs = stmt.executeQuery("SELECT * FROM Author;");
			// (2) show current data
			this.printTable(rs);
			System.out.println("cursor current row = " + rs.getRow() + " after displaying all rows.");
			// (3) insert an author
			System.out.println("-- Insert new Author Charles Darwin. ----------");
			rs.moveToInsertRow();						// move to insert row
			rs.updateInt("AuthorID", 1010);				// update all values
			rs.updateString("FirstName", "Charles");
			rs.updateString("Lastname", "Darwin");
			rs.insertRow();								// send row to db
			rs.moveToCurrentRow();						// move cursor  back
			// (4) check cursoe pos
			System.out.println("cursor is after last row = " + rs.isAfterLast());
			System.out.println("cursor current row = " + rs.getRow() + " after insertion of Darwin.");
			rs.absolute(0);
			// (5) show table again
			//this.printTable(rs);
			
			// (6) update darwin
			System.out.println("-- update Darwin to Wallace ----------");
			if (!rs.last()) {return;} else { System.out.println("isLast = " + rs.isLast() ); }
			int r = rs.getRow();
			System.out.println("r is " + r);
			do {
				
				if ( rs.getString("LastName").equals("Darwin") ) {	// RT java.sql.SQLException: Before start of result set
					rs.updateString("FirstName","Alfred");
					rs.updateString("LastName","Wallace");
					rs.updateRow();
				}
				rs.relative(-1);
				r--;
			}while( r >=0 );
			this.printTable(rs);
			
			// (7) delete Alfred Wallace
			System.out.println("-- delete Wallace ----------");
			rs.last();
			while( rs.previous() ) {
				if ( rs.getString("LastName").equals("Wallace") ) {
					rs.deleteRow();
					break;
				}
			}
			System.out.println("cursor current row = " + rs.getRow() + " after deletion of Wallace's row. ");
		}catch( SQLException e ) {
			e.printStackTrace();
			return;
		}
	}
}
