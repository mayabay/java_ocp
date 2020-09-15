package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * NamesDatabases reads names files and builds the database. 
 * Data files are found here: https://github.com/smashew/NameDatabases. 
 * 
 * 
 * sql to create db
 * CREATE DATABASE namesdb CHARACTER SET utf8 COLLATE utf8_general_ci;
 * GRANT ALL PRIVILEGES ON namesdb.* TO 'namesdbadm'@'localhost' IDENTIFIED BY 'Pa$$w0rd';
 * 
 * 
 * 0.2	- fortune500 snf top level domains added
 * 			https://data.iana.org/TLD/tlds-alpha-by-domain.txt	
 * 			https://raw.githubusercontent.com/cmusam/fortune500/master/csv/fortune500-2019.csv
 * 
 * 		- 4 extra tables with the individual values
 * 		- names based on lastNames size because this file has more entries, firstName is randomly selected
 *  	- log file rotates 5 times when size of 1 MB is reached
 * 0.1 	- first and last names (https://github.com/smashew/NameDatabases)
 * 
 * TODO
 * 		
 * 		
 * 
 *  
 *  @author Andreas Mann 
 * */
public class NamesDatabase {


	
	/* 
	 * file logger 
	 * https://www.logicbig.com/tutorials/core-java-tutorial/logging/getting-started.html
	 * https://www.logicbig.com/tutorials/core-java-tutorial/logging/customizing-default-format.html
	 * 
	 * */
	private static Logger logger;
	
	static {
		// load custom props
	      InputStream stream = NamesDatabase.class.getClassLoader().
	              getResourceAsStream("logging.properties");
	      try {
	          LogManager.getLogManager().readConfiguration(stream);
	          logger= Logger.getLogger(NamesDatabase.class.getName());

	      } catch (IOException e) {
	          e.printStackTrace(); System.exit(-1);
	      }		
		
		// add a FileHandler default pattern %h/java%u.log
		try {
			logger.addHandler(new FileHandler( "%h/"+ NamesDatabase.class.getName() +"%u.log", 1_048_576, 5 ,  true ));
		} catch (SecurityException e) {
			e.printStackTrace(); System.exit(-1);
		} catch (IOException e) {
			e.printStackTrace();System.exit(-1);
		}		
		
	}
	
	// entries in the tables
	private static final int NAMES_COUNT = 88305;
	private static final int FIRST_NAMES_COUNT = 19948;
	private static final int LAST_NAMES_COUNT = NAMES_COUNT;
	private static final int TOP_LEVEL_DOMAINS_COUNT = 1509;
	private static final int FORTUNE500_COUNT = 500;
	
	/* files required to build table of names */
	private static final String pathToFirstNamesFile = "D://projects/NameDatabases/NamesDatabases/first names/all.txt";
	private static final File firstNameFile = new File( pathToFirstNamesFile );
	
	private static final String pathToSurNameFile = "D://projects/NameDatabases/NamesDatabases/surnames/all.txt";
	private static final File lastNameFile = new File( pathToSurNameFile );
	
	private static final String pathToTLDFile = "D://projects/NameDatabases/tlds-alpha-by-domain.txt";
	private static final File tLDFile = new File(pathToTLDFile);

	private static final String pathToFortune500File = "D://projects/NameDatabases/fortune500-2019.csv";
	private static final File fortune500File = new File(pathToFortune500File);
	
	static {
		
		// check if files exist
		if ( ! firstNameFile.exists() ) {
			throw new RuntimeException("firstNameFile not found!");
		}
		else if ( ! lastNameFile.exists()  ) {
			throw new RuntimeException("lastNameFile not found!");
		}
		else if ( ! tLDFile.exists() ) {
			throw new RuntimeException("tLDFile not found!");
		}
		else if ( ! fortune500File.exists() ) {
			throw new RuntimeException("fortune500File not found!");
		}
		
	}

	/* number of rows in names table */
	private static int nameEntries;
	
	// connect to the server
	private static final String jdbcUrlDbServer = "jdbc:mysql://localhost:3306";
	private static final String dbUserServer = "root";
	private static final String dbPasswordServer = "Pa$$w0rd";	
	
	// database name
	private static final String dbName = "namesdb";
	
	// jdbcUrl
	private static final String jdbcUrl = "jdbc:mysql://localhost:3306/namesdb";
	
	// database user + password
	private static final String dbUser = "root";
	private static final String dbPassword = "Pa$$w0rd";
	
	private static final String sqlDropDbStmt = "DROP DATABASE IF EXISTS "+dbName+";";
	private static final String sqlCreateDbStmt = "CREATE DATABASE "+ dbName +" CHARACTER SET utf8 COLLATE utf8_general_ci;";
	private static final String sqlUseDb = "USE " + dbName + ";";
	private static final String sqlCreateTableStmt = "CREATE TABLE names "
			+ "("
			+ "id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,"
			+ "timestmp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,"
			+ "first_name VARCHAR(128),"
			+ "last_name VARCHAR(128)"
			+ ");";
	
	private static final String sqlCreateTableFirstNameStmt = "CREATE TABLE first_names "
			+ "("
			+ "id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,"
			+ "timestmp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,"
			+ "first_name VARCHAR(128)"
			+ ");";
	
	private static final String sqlCreateTableLastNameStmt = "CREATE TABLE last_names "
			+ "("
			+ "id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,"
			+ "timestmp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,"
			+ "last_name VARCHAR(128)"
			+ ");";
	
	private static final String sqlCreateTableTLDStmt = "CREATE TABLE top_level_domains "			+ "("
			+ "id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,"
			+ "timestmp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,"
			+ "top_level_domain VARCHAR(128)"
			+ ");";
	
	private static final String sqlCreateTableFortune500Stmt = "CREATE TABLE fortune500 "			+ "("
			+ "id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,"
			+ "timestmp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,"
			+ "fortune500 VARCHAR(128)"
			+ ");";
	
	
	// ref to connection pool instance
	private static ConnectionPool connPool;
	
	// initial open db connections
	private static int initialConnectionCount = 4;
	
	// create new connections if pool size  < minConnectionCount
	private static int minConnectionCount = 1;
	// numbers of firstNames in db
	private static int firstNameCount;
	// last name count in db
	private static int lastNameCount;
	// numbers of companies in db
	private static int fortune500Count; 
	// top level domains in db
	private static int topLevelDomainCount;
	
	private static String[] departments = { "Customer Service", "Development", "Finance",
			"Human Resources", "Marketing", "Production",
			"Quality Management", "Research", "Sales",
			"Deplyoyment", "Consulting", "Pre Sales"};
	
	// generates pseudorandom numbers
	private static Random random = new Random( System.currentTimeMillis() );
	
	// represents an Employee supplier
	private Supplier<Employee> employeeSupplier = () -> {
			NamesDatabase namesDatabase = NamesDatabase.getInstance();
			Employee e = new Employee();
			e.setFirstName(namesDatabase.getRandomValue("firstName"));
			e.setLastName(namesDatabase.getRandomValue("lastName"));
			e.setDepartment(namesDatabase.getRandomDepartment());
			e.setSalary(namesDatabase.getRandomSalary());
			e.setBirthDate(namesDatabase.getRandomBirthDate());
			e.setCompanyName( namesDatabase.getRandomValue("fortune500") );
			StringBuffer buf = new StringBuffer();
			buf.append(e.getFirstName()).append('.').append(e.getLastName()).append(namesDatabase.toEMail(e.getCompanyName(), namesDatabase.getRandomValue("topLevelDomain") ));
			e.seteMail( buf.toString() );
			return e;
	};
	
	/*
	 * Contains an internal list of db connections
	 * ref: https://www.baeldung.com/java-connection-pooling
	 * */
	private class ConnectionPool {
		
		private  Connection dbconn; 
		//private static final String jdbcUrl = "jdbc:mysql:localhost:3306/namesdb";		
		
		// unused connections in pool
		private List<Connection> connList;
		
		// handed out connections
		private List<Connection> connListUsed;
		
		private ConnectionPool( int connectionCount )  {
//			try { Class.forName("com.mysql.jdbc.Driver"); }
//			catch( ClassNotFoundException e ) {
//				e.printStackTrace();System.exit(-1);
//			}
			
			connList = new ArrayList<>( connectionCount );
			connListUsed = new ArrayList<>(  );
			checkPoolSize();
		}
		
		private Connection supplyDBConnection(  ) {
			try {
				return DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
			} catch (SQLException e) {
				e.printStackTrace(); System.exit(-1);
			}	
			return null;
		}		
		
		private Connection getConnection() {
			Connection c = null;
			
			Optional<Connection> opt = connList.stream()
						.findFirst();
			c = opt.orElseGet( () -> {
				// none found in pool
				Connection newCon = supplyDBConnection();
				this.connListUsed.add(newCon);
				checkPoolSize();
				return newCon;
			} );
			// found in pool
			c = connList.remove(connList.size()-1);
			
			if ( isClosed(c) ){
				c = supplyDBConnection();
			}
			
			this.connListUsed.add(c);
			checkPoolSize();
			return c;
		}
		
		private void returnConnection( Connection con ) {
			this.connListUsed.remove(con);
			this.connList.add(con);
			checkPoolSize();
		}
		
		private void checkPoolSize() {
			if ( connList.size() < minConnectionCount ) {
				IntStream ints = IntStream.range( 0, minConnectionCount );
				ints
					.forEach(i -> connList.add(supplyDBConnection())  );				
			}else if ( connList.size() > initialConnectionCount ) {
				int connToRemove = connList.size() - initialConnectionCount;
				for ( int i = 0; i < connToRemove; i++ ) {
					Connection con = connList.remove(connList.size()-1);
					try { con.close(); } catch( SQLException e ) { e.printStackTrace(); continue; }
					
				}
			}
		}
		
		private long getFreeConnectionCount()  {
			return connList.stream()
					.count();
		}
		
		private long getUsedConnectionCount() {
			return connListUsed.stream()
					.count();			
		}
		
		private boolean isClosed( Connection c ) {
			boolean isClosed = false;
			try {
				isClosed = c.isClosed();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return isClosed;
		}		
		
	}	
	
	/**
	 * Creates NamesDatabase, will also create a connection pool
	 * */
	private NamesDatabase(){
		initConnPool();
		readTables();
	}
	
	// singleton
	private static NamesDatabase instance = new NamesDatabase();	
	
	// =========================================================================
	
	/**
	 * Returns the NamesDatabase instance
	 * 
	 * @return
	 * */
	public static NamesDatabase getInstance() {
		return instance;
	}
	
	/* count table entries */
	private static void readTables() {
		firstNameCount = getRowCount("first_names");
		lastNameCount = getRowCount("last_names");
		fortune500Count = getRowCount("fortune500");
		topLevelDomainCount = getRowCount("top_level_domains");
		
//		logger.log(Level.INFO, "tables available:  "
//				+ "firstNameCount = " + firstNameCount
//				+ ", lastNameCount = " + lastNameCount
//				+ ", fortune500Count = " + fortune500Count
//				+ ", topLevelDomainCount = " + topLevelDomainCount
//				);
	}
	
	/* get row count for a table */
	private static int getRowCount(String tableName) {
		Connection con = connPool.getConnection();
		int r = 0;
		try {
			Statement stmt1 = con.createStatement();
			ResultSet rs =  stmt1.executeQuery("SELECT COUNT(id) FROM "+dbName+"."+tableName+";");
			if (rs.next()) {
				r = rs.getInt(1);
			}
		}catch( SQLException e ) {
			e.printStackTrace();
			System.exit(-1);
		}
		connPool.returnConnection(con);		
		return r;
	}
	
	/* creates the database */
	private static void initDb() {
		try {
			Connection con =  DriverManager.getConnection(jdbcUrlDbServer,dbUserServer, dbPasswordServer );
			Statement stmtCreateDb = con.createStatement();
			int r = stmtCreateDb.executeUpdate(sqlCreateDbStmt);
			System.out.println("database created, return value = " + r);
			
		} catch (SQLException e) {
			e.printStackTrace(); System.exit(-1);
		}
		
	}
	
	/* creates the connection pool instance */
	private void initConnPool() {
		if (connPool == null) {
			connPool = new ConnectionPool( NamesDatabase.initialConnectionCount );
			//logger.log(Level.INFO, "connnection pool initiated with n = " + NamesDatabase.initialConnectionCount);
		}
	}
	
	/**
	 * Resets the names database
	 * 
	 * */
	public void resetDb() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(jdbcUrlDbServer,dbUserServer, dbPasswordServer );
		} catch (SQLException e1) {
			e1.printStackTrace(); System.exit(-1);
		}
		int r = 0;	// ret val of statements
		try {
			// drop database
			Statement stmtDropDb = con.createStatement();
			r = stmtDropDb.executeUpdate(sqlDropDbStmt);
			// create database
			Statement stmtCreateDb = con.createStatement();
			r = stmtCreateDb.executeUpdate(sqlCreateDbStmt);
			// use database
			Statement stmtUseDb = con.createStatement();
			r = stmtUseDb.executeUpdate(sqlUseDb);
			// create table
			Statement stmtCreateTable = con.createStatement();
			r = stmtCreateTable.executeUpdate(sqlCreateTableStmt);
			// create table first_names
			Statement stmtCreateTable_first_names = con.createStatement();
			r = stmtUseDb.executeUpdate(sqlCreateTableFirstNameStmt);
			// create table last_names
			Statement stmtCreateTable_last_names = con.createStatement();
			r = stmtUseDb.executeUpdate(sqlCreateTableLastNameStmt);
			// create table top_level_domains
			Statement stmtCreateTable_top_level_domains = con.createStatement();
			r = stmtUseDb.executeUpdate(sqlCreateTableTLDStmt);
			// create table fortune500 
			Statement stmtCreateTable_fortune500 = con.createStatement();
			r = stmtUseDb.executeUpdate(sqlCreateTableFortune500Stmt);
			
		} catch (SQLException e) {
			e.printStackTrace(); System.exit(-1);
		}
		logger.log(Level.INFO, "names database was reset");
	} 
	
	
	/**
	 * Returns a random value for specified name 
	 * 
	 * @param name of (firstName, lastName, fortune500, topLevelDomain)
	 * @return random value for name
	 * @throws IllegalStateException, if name is unknown
	 * */
	public String getRandomValue( String name ) {
		String r = "unspecified";
		switch( name ) {
			case "firstName": {
					r = getFieldFromTableAtRowId( "first_names","first_name", random.nextInt(FIRST_NAMES_COUNT)  );
					break;
				}
			case "lastName": {
					r = getFieldFromTableAtRowId( "last_names","last_name", random.nextInt(LAST_NAMES_COUNT)  );
					break;
				}	
			case "fortune500": {
					r = getFieldFromTableAtRowId( "fortune500","fortune500", random.nextInt(FORTUNE500_COUNT)  );
					break;
				}			
			case "topLevelDomain": {
					r = getFieldFromTableAtRowId( "top_level_domains","top_level_domain", random.nextInt(TOP_LEVEL_DOMAINS_COUNT)  );
					break;
			}			
			default:
				throw new IllegalStateException("unknwon name domain specified");
		
		}
		return r;
	}
	
	/**
	 * Returns a random birth date
	 * 
	 *  @return a birth date
	 * 
	 * */
	public LocalDate getRandomBirthDate(  ) {
		int age = (int)(Math.random() * 100);
		if (age < 18) { age += 21; }
		if ( age > 85 ) { age -= 25; }
		
		LocalDate today = LocalDate.now();
		int yearToday = today.getYear();
		
		int birthYear = yearToday - age;
		int birthMonth = random.nextInt(13); if (birthMonth == 0) { birthMonth = 1; };
		int birthDay = 1;
		
		switch( birthMonth ) {
			case 1: birthDay = random.nextInt(32); break;
			case 2: birthDay = random.nextInt(29); break;
			case 3: birthDay = random.nextInt(32); break;
			case 4: birthDay = random.nextInt(31); break;
			case 5: birthDay = random.nextInt(32); break;
			case 6: birthDay = random.nextInt(31); break;
			case 7: birthDay = random.nextInt(32); break;
			case 8: birthDay = random.nextInt(32); break;
			case 9: birthDay = random.nextInt(31); break;
			case 10: birthDay = random.nextInt(32); break;
			case 11: birthDay = random.nextInt(31); break;
			case 12: birthDay = random.nextInt(32); break;
		}
		
		if (birthDay == 0) { birthDay = 1; }
		
		LocalDate birthDate = LocalDate.of(birthYear, birthMonth, birthDay);
		return birthDate;
	}
	
	// return random row value for specified column and id of table
	private String getFieldFromTableAtRowId( String tableName, String colName, int id ) {
		if (id == 0) { id = 42; }	// :D
		Connection con = connPool.getConnection();
		String r = "";
		try {
			Statement stmt1 = con.createStatement();
			ResultSet rs =  stmt1.executeQuery("SELECT "+colName+" FROM "+dbName+"."+tableName+" WHERE id = '"+id+"';");
			if (rs.next()) {
				r = rs.getString(colName);
			}
		}catch( SQLException e ) {
			e.printStackTrace();
			System.exit(-1);
		}
		connPool.returnConnection(con);		
		return r;		
	}
	
	/**
	 * Returns random department
	 * 
	 * @return String name of department see static array of values
	 * */
	public String getRandomDepartment() {
		return departments[ random.nextInt(departments.length) ];
	}
	
	/**
	 * Returns a random double between 
	 * 
	 * @return double salary
	 * */
	public double getRandomSalary() {
		double salary = (double)random.nextInt( 85001 );
		if (salary < 20000.00) { salary += random.nextInt( 24000 );  }
		return salary;
	}
	
	/**
	 * Returns a new string prepared to be used as domain name in an email 
	 * 
	 * @param String companyName (e.g. Intercontinental Exchange)
	 * @param String topLevelDomain (e.g. DABUR)
	 * @return String e.g. @intercontinental_exchange.dabur
	 * */
	public String toEMail(String companyName, String topLevelDomain) {
		StringBuffer r = new StringBuffer();
		companyName = companyName.toLowerCase().replace(' ', '_').replace('&', '_').replace('.', '_').replace('\'', '_');
		r.append('@').append(companyName).append('.').append(topLevelDomain.toLowerCase());
		return r.toString();
	}
	
	/**
	 * Returns a random employee supplier
	 * 
	 * @return Supplier<Employee>
	 * */
	public Supplier<Employee> getEmpliyeeSupplier(){
		return employeeSupplier;
	}
	
	/*
	 * uploads names into table names
	 * */
	private void loadData() throws FileNotFoundException, IOException {
		
		int inserts = 0;
		
		List<String> firstNames = new ArrayList<>();
		List<String> lastNames = new ArrayList<>();
		List<String> tlds = new ArrayList<>();
		List<String> fortune500 = new ArrayList<>();
		
		try ( 	BufferedReader buf1 = new BufferedReader( new FileReader(firstNameFile) );
				BufferedReader buf2 = new BufferedReader( new FileReader(lastNameFile) );
				BufferedReader buf3 = new BufferedReader( new FileReader(tLDFile) );
				BufferedReader buf4 = new BufferedReader( new FileReader(fortune500File) );
				)
		{
			String line;
			while( (line = buf1.readLine()) != null ) {
				firstNames.add(line);
			}
			while( (line = buf2.readLine()) != null ) {
				lastNames.add(line);
			}			
			while( (line = buf3.readLine()) != null ) {
				tlds.add(line);
			}
			while( (line = buf4.readLine()) != null ) {
				fortune500.add(line);
			}
		}
		
		// load data into table ------------------------------------------------
		//logger.log(Level.INFO, "first names count = " + firstNames.size());
		
		Random random = new Random();
		
		try {
			Connection con = connPool.getConnection();
			PreparedStatement stmtInsert = con.prepareStatement("INSERT INTO names (id,first_name,last_name) VALUES (null,?,?);");
			
			for( int i = 0; i < lastNames.size() ; i++ ) {
				
				String l = lastNames.get(i);	// 88305 inserts
				String f = firstNames.get( random.nextInt(firstNames.size()) );	// 19948
				
				stmtInsert.setString(1, f);
				stmtInsert.setString(2, l);
				int r = stmtInsert.executeUpdate();
				inserts++;
			}
			
			connPool.returnConnection(con);
		}catch(SQLException e) {
			e.printStackTrace(); System.exit(-1);
		}
		logger.log(Level.INFO, "table names loaded with " + inserts +" inserts.");
		nameEntries = inserts;
		//System.out.println("table names loaded with " + inserts +" inserts.");

		// ---------------------------------------------------------------------
		
		// first_names
		for( int i = 0; i < firstNames.size() ; i++ ) {
			loadTable( "first_names", "first_name", firstNames.get(i) );
		}		
		logger.log(Level.INFO, "table first_names loaded with " + firstNames.size() +" inserts.");
		
		// last_names
		for( int i = 0; i < lastNames.size() ; i++ ) {
			loadTable( "last_names", "last_name", lastNames.get(i) );
		}			
		logger.log(Level.INFO, "table lastNames loaded with " + lastNames.size() +" inserts.");

		// top_level_domains
		for( int i = 1; i < tlds.size() ; i++ ) {
			loadTable( "top_level_domains", "top_level_domain", tlds.get(i) );
		}
		logger.log(Level.INFO, "table top_level_domains loaded with " + (tlds.size()-1) +" inserts.");

		// fortune500
		for( int i = 1; i < fortune500.size() ; i++ ) {
			String[] elems = (fortune500.get(i)).split(",");
			loadTable( "fortune500", "fortune500", elems[1]  );
		}	
		logger.log(Level.INFO, "table fortune500 loaded with " + (fortune500.size() -1) +" inserts.");
		
		// ---------------------------------------------------------------------
		
	}

	private static void loadTable( String tableName, String columnName, String value ) {
		try {
			Connection con = connPool.getConnection();
			PreparedStatement stmtInsert = con.prepareStatement("INSERT INTO "+ tableName +" (id,"+columnName+") VALUES (null,?);");
			stmtInsert.setString(1, value);
			int r = stmtInsert.executeUpdate();
			connPool.returnConnection(con);
		}catch(SQLException e) {
			e.printStackTrace(); System.exit(-1);
		}		
	}
	
	// =========================================================================
	
	/**
	 * main()
	 * */
	public static void main(String[] args) {
		
		//1 .) initDb();	// creates the database
		NamesDatabase namesDb = NamesDatabase.getInstance();
		
		// 2.)
		//namesDb.resetDb();	// drops db and creates it again
		
		// test connection pool

		// 3.) load names data into table
		//loadDataIntoTables(namesDb);
		
		// 4.) test
		System.out.println("firstName : " + namesDb.getRandomValue("firstName") );
		System.out.println("lastName : " + namesDb.getRandomValue("lastName") );
		System.out.println("fortune500 : " + namesDb.getRandomValue("fortune500") );
		System.out.println("topLevelName : " + namesDb.getRandomValue("topLevelDomain") );
		
		Stream<Employee> empStream = Stream.generate( namesDb.employeeSupplier );
				empStream
				.limit(4)
				.peek( e -> { NamesDatabase.safeSleep(); } )
				.forEach(System.out::println);
		
		
	}

	private static void safeSleep() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void loadDataIntoTables( NamesDatabase namesDb ) {
		Instant begin = Instant.now();
		System.out.println(  );
		try {
			namesDb.loadData();
		} catch (FileNotFoundException e) {
			e.printStackTrace(); System.exit(-1);
		} catch (IOException e) {
			e.printStackTrace(); System.exit(-1);
		}
		Instant after = Instant.now();
		long timeDiff = after.toEpochMilli() - begin.toEpochMilli();
		logger.log(Level.INFO, "timeDiff = " + timeDiff + ", seconds = " + (timeDiff/1000) + ", mins = " + (timeDiff/1000/60));
		//System.out.println( "timeDiff = " + timeDiff + ", seconds = " + (timeDiff/1000) );		
	}
	
	/* Tests the connection pool.*/
	private void testConnectionPool() {
		System.out.println("free connections = " + connPool.getFreeConnectionCount() );
		
		Connection myCon1 = connPool.getConnection();
		Connection myCon2 = connPool.getConnection();
		Connection myCon3 = connPool.getConnection();
		Connection myCon4 = connPool.getConnection();
		
		System.out.println("free connections (4 used) = " + connPool.getFreeConnectionCount() );
		System.out.println("used connections = " + connPool.getUsedConnectionCount() );
		
		connPool.returnConnection(myCon1);
		connPool.returnConnection(myCon2);
		connPool.returnConnection(myCon3);
		connPool.returnConnection(myCon4);
		
		System.out.println("free connections (4 returned) = " + connPool.getFreeConnectionCount() );		
	}
	
}
