package util.crud;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.crud.AbstractEntity;

/**
 * Abstract base class for all entities
 * 
 * CRUD Create Read Update Delete
 * 
 * GFN OCP Java course April 2020.
 * */
public abstract class AbstractCRUD<T extends AbstractEntity> {

	private DBConnectionPool connPool = DBConnectionPool.getInstance();
	
	/**
	 * Name of database table
	 * */
    public final String TABLE;

    /**
     * Constructor sets the table name
     * */
    public AbstractCRUD(String table) {
        this.TABLE = table;
    }	

    /**
     * Saves given entity reference to database.
     * 
     * @param entity to be saved
     * */
    public boolean save(T t) throws SQLException{
        if (t.getId() > 0 ){
            return update(t);
        }else{
            return insert(t);
        }
    }    
    
    /**
     * Creates T instance by data from given result set
     * 
     * */
    public abstract T create(ResultSet rs) throws SQLException;   
    
    /**
     * Find by id
     * 
     * @param id unique identifier 
     * */
    public  T find( int id ) throws SQLException{
        try(    Connection con = connPool.getConnection();
                Statement stmt = con.createStatement();
                ){
            // add compile time final static var TABLE is inserted in String 
            ResultSet result =  stmt.executeQuery("SELECT * FROM "+ TABLE +" WHERE id = " + id);
            result.next();  // move to first row
            return create( result );       
        }
    }    
    
    /**
     * Find all instances
     * 
     * @return List of T
     * */
    public  List<T> find() throws SQLException {
        try(    Connection con = connPool.getConnection();
                Statement stmt = con.createStatement();
                ){
            // add compile time final static var TABLE is inserted in String 
            ResultSet result =  stmt.executeQuery("SELECT * FROM "+ TABLE );
            List<T> list = new ArrayList<>();
            while( result.next() ){
                list.add( create(result) );
            }
            return list;       
        }    
    }    
    
    /**
     * Find instances in range
     * 
     * @param start begin index
     * @param num row count to retrieve
     * @return List of instances 
     * */
    public List<T> find(int start, int num) throws SQLException {
        try(    Connection con = connPool.getConnection();
                Statement stmt = con.createStatement();
                ){
            // add compile time final static var TABLE is inserted in String 
            ResultSet result =  stmt.executeQuery("SELECT * FROM "+ TABLE + " WHERE id >= " + start + " LIMIT " + num);
            List<T> list = new ArrayList<>();
            while( result.next() ){
                list.add( create(result) );
            }
            return list;       
        }    
    }    
    
    /**
     * Deletes entity by reference
     * 
     * @param t object reference to delete
     * */
    public  boolean delete (T t) throws SQLException{
        return delete( t.getId() );
    }
    
    /**
     * Deletes entity by id.
     * 
     * @param id
     * */
    public boolean delete (int id) throws SQLException {
        try(    Connection con = connPool.getConnection();
                Statement stmt = con.createStatement();
                ){
            // add compile time final static var TABLE is inserted in String 
            int result =  stmt.executeUpdate( 
                    "DELETE FROM " + TABLE + " WHERE id = " + id  
            );
            if ( result == 1 ) {return true;}
            else{ return false; }
        }        
    }      
    
    
    /**
     * Updates database entry of given reference
     * 
     * @param entity reference
     * */
    private boolean update (T t) throws SQLException {
    
        //StringBuilder keys = new StringBuilder();
        //StringBuilder values = new StringBuilder();        
        StringBuilder pairs = new StringBuilder();
        
        int id = t.getId();
        
        //String sql = "UPDATE " + TABLE + " SET firstname = ?, lastname = ?, email = ? WHERE id = ?; ";
        String sql = "UPDATE " + TABLE + " SET %s WHERE id = %s; ";
        
        Field[] fields =  t.getClass().getDeclaredFields();
        
        for ( Field f : fields ){
            
            if ( f.getName().equals("id") ){
                continue;
            }
            if ( pairs.length() > 0 ) {
                pairs.append(", ");
            }
                        
            pairs.append( f.getName().toLowerCase() + " = ? "  );
            
            //System.out.println(f.getName());
        }        
        
        sql = String.format( sql, pairs, id );
        
        return executePrepared(sql, t, false );
    }    
    
    /**
     * Stores entity in database.
     * 
     * @return true, if successful
     * */
    private boolean insert(T t) throws SQLException {
        
        StringBuilder keys = new StringBuilder();
        StringBuilder values = new StringBuilder();
        
        String sql = "INSERT INTO "+TABLE+" (%s) VALUES (%s)";
        //String sql = "INSERT INTO "+ TABLE +" (id, firstname, lastname, email) VALUES ( null, ?, ?, ? );";  
  
        Field[] fields =  t.getClass().getDeclaredFields();
  
        for ( Field f : fields ){
            
            if ( f.getName().equals("id") ){
                continue;
            }
            
            if ( keys.length() > 0 ) {
                keys.append(",");
                values.append(",");
            }
            keys.append(f.getName().toLowerCase());
            values.append("?");
            //System.out.println(f.getName());
        }
          
        sql = String.format( sql, keys, values );
        
        return executePrepared(sql, t, true );
       
    }    

    
    // #########################################################################
    
    /*
     * Sends prepared statement to database. 
     * */
    private boolean executePrepared( String sql, T t, boolean isInsert ) throws SQLException {
        try(    Connection con = connPool.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ){
            // add compile time final static var TABLE is inserted in String 

            Field[] fields =  t.getClass().getDeclaredFields();
            int i = 0;
            for ( Field f : fields ){
               try{
                   f.setAccessible(true);
                   stmt.setObject(++i, f.get(t));
               }catch(IllegalArgumentException | IllegalAccessException e){
                   // thrown by f.get(t)
                }
            }
            
            boolean result =  stmt.execute();
            
            if ( isInsert ) {
                ResultSet ids = stmt.getGeneratedKeys();
                int id = ids.getInt(1);
                if (id > 0){
                    t.setId(id);
                    return true;
                }else{
                    return false;
                }
            }
            return result;
        }        
    };     
    
}
