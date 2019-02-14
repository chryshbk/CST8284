package assignment.pkg1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* File: Consumer.java
 * Author: Chrystian Rafael Sanches dos Santos
 * Date: September 20, 2018
 * Description: Implements Runnable, opens/closes the connection with database, populates a temporary Tuna object taking data from a Buffer, inserts data taken into the database and records it into a variable.
 */
public class Consumer implements Runnable
{
    //Shared buffer
    protected final Buffer SHAREDBUFFER;
    
    private static final Logger LOGGER = Logger.getLogger( Consumer.class.getName() );
    
    //Static variables
    protected static Connection conn = null;
    protected static PreparedStatement pstmt;
    protected static final String CONNECTIONSTRING = "jdbc:mysql://localhost:3306/assignment?useSSL=false";
    protected static final String USERNAME = "assignment1";
    protected static final String PASSWORD = "password";
    
    //Tuna object
    private Tuna tempTuna;
    
    private int recordsInserted;
    private int maxCount = 100;
    
    /**
     * Constructor for Consumer which takes a Buffer as a parameter.
     * @param sharedBuffer buffer object
     */
    public Consumer(Buffer sharedBuffer)
    {
        this.SHAREDBUFFER = sharedBuffer;
    }
    
    /**
     * Opens the connection to the local database.
     */
    protected static void openConn()
    {
        try
        {
            if (conn != null)
                Logger.getLogger("Cannot create new connection. One already exists.");
            else
                conn = DriverManager.getConnection(CONNECTIONSTRING, USERNAME, PASSWORD);
        } catch(SQLException ex){ Logger.getLogger(ex.getMessage()); }      
    }
    
    /**
     * Closes the connection with the database.
     */
    protected static void closeConn() 
    {
	try
        { 
            if(conn != null) conn.close(); 
        } catch(SQLException ex){ System.out.println(ex.getMessage()); }
    }
    
    /**
     * Overridden method from Runnable interface, executes the insertion of Tuna objects taken from the buffer into the database.
     */
    @Override
    public void run()
    {
        //Connection should have been opened in the TunaCleaner, here is just a precausion.
        if (conn == null)
            openConn();
        
        // Loops until it gets the last element from the file.
        while(true)
        {
            try
            {
                //Uses tempTuna, which is a temporary Tuna object, to store data from the buffer and inserts it into the database
                insertTuna(tempTuna = SHAREDBUFFER.blockingGet());
            } catch(InterruptedException ie){ Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ie); }
            
            // Should it be the last Tuna, 
            if (tempTuna.isLastTuna())
            {
                System.out.printf("%d records inserted by consumer, task completed", getRecordsInserted());
                Logger.getLogger(getRecordsInserted()+ " records inserted by consumer, task completed.");
                break;
            }
        }
        try
        {
            // Closes the PreparedStatement object.
            if(pstmt != null) pstmt.close();
        } catch(SQLException ex) { System.out.println(ex.getMessage()); }
        
        closeConn(); // Shuts connection at the end.
    }
    
    /**
     * It takes a Tuna object as a parameter and adds this object into the Database using the java.sql.PreparedStatement interface.
     * @param tuna element
     */
    public void insertTuna(Tuna tuna)
    {   
        try
        {
            if (conn == null)
                Logger.getLogger("No connection");
 
            pstmt = conn.prepareStatement(
                    "INSERT INTO tunas (recordnumber, omega, delta, theta, uuid) " +
                    "VALUES (?, ?, ?, ?, ?)");
            pstmt.setInt(1, tuna.getRecordNumber());
            pstmt.setString(2, tuna.getOmega());
            pstmt.setString(3, tuna.getDelta());
            pstmt.setString(4, tuna.getTheta());
            pstmt.setString(5, tuna.getUUID());
            pstmt.execute();
            
            ++recordsInserted;
            if(recordsInserted == maxCount && !tuna.isLastTuna())
            {
                System.out.printf("%d records inserted by Consumer%n", getRecordsInserted());
                LOGGER.log(Level.FINEST,"Inserting records is working properly, should not get the last element. {0} records now", getRecordsInserted());
                maxCount = maxCount + 100;
            }
        } catch (SQLException e){ 
            Logger.getLogger(e.getMessage()); 
        }
    }
    
    /**
     * This method returns all the records inserted by the Consumer into the database.
     * @return recordsInserted variable that contains records were inserted by the Consumer.
     */
    public int getRecordsInserted(){ return recordsInserted; }
}
