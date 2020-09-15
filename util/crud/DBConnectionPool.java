package util.crud;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.FileHandler;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.stream.IntStream;

import util.LoggerBuilder;
import util.NamesDatabase;

public class DBConnectionPool {
	
	private static Logger logger = LoggerBuilder.getInstance().build( DBConnectionPool.class );
	
	// connect to the server
	private static final String jdbcUrlDbServer = "jdbc:mysql://localhost:3306";
	private static final String dbUserServer = "root";
	private static final String dbPasswordServer = "Pa$$w0rd";	

	// database name
	private static String dbName = "namesdb";
	
	// jdbcUrl
	private static String jdbcUrl = "jdbc:mysql://localhost:3306/" + dbName;
	
	// database user + password
	private static String dbUser = "root";
	private static String dbPassword = "Pa$$w0rd";	
	
	// initial open db connections
	private static int initialConnectionCount = 4;
		
	// create new connections if pool size  < minConnectionCount
	private static int minConnectionCount = 1;	
	
	// unused connections in pool
	private List<Connection> connList;
	
	// handed out connections
	private List<Connection> connListUsed;	

    // check connection thread workload
    Runnable poolCheckRunnable = () -> {
    	
    	while( true ) {
	    	List<Connection> toBeRemoved = new ArrayList<>();
	    	for ( Connection c : this.connListUsed ) {
	    		
	    		boolean isClosed = false;
	    		try {
	    			isClosed = c.isClosed();
	    		}catch( SQLException e ) {
	    			e.printStackTrace();
	    			continue;
	    		}
	    		
	    		if ( isClosed ) { toBeRemoved.add(c); }
	    	}
	    	
	    	for( Connection c : toBeRemoved ) {
	    		this.connListUsed.remove(c);
	    	}
	    	
	    	this.checkPoolSize();
	    	
	    	try {
				Thread.sleep(1000 * 25);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    };	
	
	// pool instance
	private static DBConnectionPool instance = new DBConnectionPool(initialConnectionCount);

    // Singleton
    private DBConnectionPool( int connectionCount ) {
		connList = new ArrayList<>( connectionCount );
		connListUsed = new ArrayList<>(  );
		checkPoolSize();    
		
		// start pool checker thread
		Thread PoolChecker = new Thread( poolCheckRunnable );
		PoolChecker.run();
    }


    // -------------------------------------------------------------------------
    
	/**
	 * Returns the pool object
	 * 
	 * @return DBConnectionPool
	 * */
	public static DBConnectionPool getInstance() {
		return instance;
	}    
    
	/**
	 * Resets all connections to new database on this server
	 * 
	 * @param closeAllConnections
	 * @param dbName new database on this server
	 * @param user to connect to new database
	 * @param password to connect to new database
	 * @throws IllegalStateException if connections are in use
	 * */
	public boolean resetConnectionProps(boolean closeAllConnections,  String dbName, String user, String password  ) {
		
		if ( this.getUsedConnectionCount() > 0 ) {
			if ( closeAllConnections ) {
				for( Connection c : this.connListUsed ) {
					try {
						c.close();
					} catch (SQLException e) {
						e.printStackTrace(); 
					}
				}
				this.connListUsed.clear();
			}
			else {
				throw new IllegalStateException("connections currently in use");	
			}
			
		}  
		
		this.dbName = dbName;
		this.dbUser = user;
		this.dbPassword = password;
		
		this.checkPoolSize();
		
		return false;
	} 
	
    /**
     * Gets a connection from the pool
     * 
     * @return Connection
     * */
	public Connection getConnection() {
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

	/**
	 * Return connection to the pool
	 * 
	 * @param con 
	 * */
	public void returnConnection( Connection con ) {
		this.connListUsed.remove(con);
		this.connList.add(con);
		checkPoolSize();
	}	
	
	/**
	 * Returns number of free connections
	 * 
	 * @return number of unused connections
	 * */
	private long getFreeConnectionCount()  {
		return connList.stream()
				.count();
	}
	
	/**
	 * Returns used connections count
	 * 
	 * @return number of connections in us
	 * */
	private long getUsedConnectionCount() {
		return connListUsed.stream()
				.count();			
	}	
	
	
	// -------------------------------------------------------------------------
	
	private boolean isClosed( Connection c ) {
		boolean isClosed = false;
		try {
			isClosed = c.isClosed();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isClosed;
	}
	
	private Connection supplyDBConnection(  ) {
		try {
			return DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
		} catch (SQLException e) {
			e.printStackTrace(); System.exit(-1);
		}	
		return null;
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
	
	@Override
	public String toString() {
		return "["+this.getClass().getCanonicalName()+": jdbcUrl = "+jdbcUrl+", user = "+dbUser+", open = "+this.getFreeConnectionCount()+", used = "+this.getUsedConnectionCount()+" ]";
	}
	
}
