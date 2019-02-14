package dataaccesslayer;
/* File: DataSource.java
 * Author: Stanley Pieda
 * Modified by: Chrystian Santos, Carlos Barroso
 * Date: Jan 2018
 * Modified Date: October, 2018
 * References:
 * Ram N. (2013). Data Access Object Design Pattern or DAO Pattern [blog] Retrieved from
 * http://ramj2ee.blogspot.in/2013/08/data-access-object-design-pattern-or.html
 */ 

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The only purpose of this class is to open a connection to a MySQL database
 * and return a reference to that connection object.
 * @author Stanley Pieda
 * Modified by: Chrystian Santos
 *
 */
public class DataSource {
        private static final Logger LOGGER = Logger.getLogger( DataSource.class.getName() );
	private static Connection conn = null;
	private static final String CONNECTIONSTRING = "jdbc:mysql://localhost:3306/assignment2?useSSL=false";
	private static final String USERNAME = "assignment2";
	private static final String PASSWORD = "password";

	/**
	 * Returns a reference to a connection object, configured to communicate with a MySQL database. 
	 * @return A connection to the configured MySQL database
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException{
            try {
                conn = DriverManager.getConnection(CONNECTIONSTRING, USERNAME, PASSWORD);
            } catch(SQLException ex) {
		System.out.println(ex.getMessage());
                if(ex.getCause() instanceof CommunicationsException)
                    LOGGER.log(Level.SEVERE, ex.getMessage());
            }
            return conn;
	}
        
        /**
         * Ceases the connection with MySQL database
         */
        public static void closeConnection() {
	try{ 
            if(conn != null) conn.close(); 
        } catch(SQLException ex){ System.out.println(ex.getMessage()); }
    }
}
