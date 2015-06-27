package RESTful.library.resources;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import RESTful.library.model.Books;

/** Sqlite Database operations:
 * Access
 * Insert
 * Query
 * Delete
 * Update
 */
public class DataBaseSQLite {

	/** Constructor 
	 * Connects to a database and creates a table. 
	 */
	public DataBaseSQLite() {
		Connection c = null;
		c = accessDB();
		if (c != null) {
			Statement stmt = null;
			try {
				DatabaseMetaData md = c.getMetaData();
				ResultSet rs = md.getTables(null, null, "%", null);
				while (rs.next()) {
					if (rs.getString(3).equals("BOOKS")) {
						c.close();	 
						return;
					}
				}

				// Execute a query
				stmt = c.createStatement();
				String sql = "CREATE TABLE BOOKS " +
						"(ID INTEGER PRIMARY KEY   AUTOINCREMENT   NOT NULL," +
						" NAME            CHAR(50) NOT NULL UNIQUE," +
						" AUTHOR          CHAR(50) NOT NULL,"+
						" YEAR            INTEGER  NOT NULL,"+
						" PUBLISHER       CHAR(50) NOT NULL)"; 
				stmt.executeUpdate(sql);
				stmt.close();
				c.close();
			} catch (Exception e) {
				// Handle errors for Class.forName and handle errors for JDBC 
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );	      
				System.exit(0);
			}
		}
	}

	/** 
	 * @return  
	 */
	public Connection accessDB(){
		Connection c = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Arturo\\Documents\\SelfAdaptiveSystems\\workspace\\library\\library.db");
			System.out.println("Access Granted.");	    
		} catch (Exception e) {
			// Handle errors for Class.forName and handle errors for JDBC
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );	      
			System.exit(0);
		}

		return c;
	}

	/** 
	 * @return  
	 */
	public List<Books> query(){
		List<Books> results = new ArrayList<>();
		Connection c = null; 
		c = accessDB();
		if (c != null){
			try{
				ResultSet rs=null;				 
				Statement stmt = c.createStatement();
				rs = stmt.executeQuery( "SELECT id,name,author,year,publisher FROM BOOKS;" );								
				while ( rs.next() ) {
					//Get record from cursor
					int id = rs.getInt("id");
					String  name = rs.getString("name");
					String author  = rs.getString("author");
					int year = rs.getInt("year");
					String publisher = rs.getString("publisher");
					Books b= new Books(id,name,author,year,publisher);                
					//add the record into the list
					results.add(b);
				}

				rs.close();
				stmt.close();
				c.close();
			}catch ( Exception e ) {
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				System.exit(0);
			}
		}  
		return results;

	}
	
	/** Get a book in the database
	 * @param args id
	 * @return  
	 */	
	public Books get(int ID){
		Books book= new Books();
		Connection c = null; 
		c = accessDB();
		if (c != null){
			try{
				ResultSet rs=null;				 
				Statement stmt = c.createStatement();
				rs = stmt.executeQuery( "SELECT id,name,author,year,publisher FROM BOOKS WHERE ID="+ID+";" );								
				while ( rs.next() ) {
					//Get record from cursor
					int id = rs.getInt("id");
					book.setId(id);
					String  name = rs.getString("name");
					book.setName(name);
					String author  = rs.getString("author");
					book.setAuthor(author);
					int year = rs.getInt("year");
					book.setYear(year);
					String publisher = rs.getString("publisher");
					book.setPublisher(publisher);
				}

				rs.close();
				stmt.close();
				c.close();
			}catch ( Exception e ) {
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				System.exit(0);
			}
			
		}
		return book;
	}

	/** Insert a new book or update a book in the database
	 * @param args name
	 * @param args author
	 * @param args year
	 * @param args publisher
	 * @return  
	 */
	public int put(String name, String author, int year, String publisher) throws Exception {
		Connection c = null;
		Statement stmt = null;
		int lastID = 0;
		int insert = 0;
		c = accessDB();
		if (c != null) {
			try {
				c.setAutoCommit(false);

				// Execute a query
				stmt = c.createStatement();
				String sql = "INSERT OR REPLACE INTO BOOKS (NAME,AUTHOR,YEAR,PUBLISHER) VALUES ( '"+ name +"', '" + author + "', '" + year + "', '" + publisher + "' );"; 
				insert = stmt.executeUpdate(sql);
				
				ResultSet rs = null;
				rs = stmt.getGeneratedKeys();
				if (rs.next()) {
					lastID = rs.getInt(1);
                } else {
                    System.out.println("can't find most recent insert we just entered!");
                }
				
			} catch ( Exception e ) {
				// Handle errors for Class.forName and handle errors for JDBC
				if (e.getMessage().contains("UNIQUE")) 
					return 0;
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				throw e;
			} finally {
				try {
					stmt.close();
					c.commit();
					c.close();
				} catch (SQLException e) {
					// Handle errors for JDBC
					e.printStackTrace();
				}
			}
		}
		if (insert > 0)
			System.out.println("Stored ("+name+","+author+")");
		return lastID;
	}

	/** Delete a book from the database
	 * @param id
	 * @return true if everything is done otherwise returns false
	 */
	public boolean delete(int id) {
		Connection c = null;
		c = accessDB();
		if (c != null) {
			try {
				Statement stmt = null;
				// Execute a query
				stmt = c.createStatement();
				String sql = "DELETE FROM BOOKS WHERE ID='"+ id +"';";
				stmt.executeUpdate(sql);
				c.commit();

				stmt.close();
				c.close();
			} catch ( Exception e ) {
				// Handle errors for Class.forName
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				return false;
			}
		}
		System.out.println("Deleted "+id);
		return true;
	}
}
