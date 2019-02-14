/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.pkg1;

import static assignment.pkg1.Consumer.*;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* File: TunaCleaner.java
 * Author: Chrystian Rafael Sanches dos Santos
 * Date: September 20, 2018
 * Description: Truncates the table "tunas" in order to start with an empty database.
 */
public class TunaCleaner 
{
    private static final Logger LOGGER = Logger.getLogger( TunaCleaner.class.getName() );
    
/**
 * Method which deletes all the Tuna objects from the database.
 */
    public void deleteAllTuna() 
    {
       // opens the connection, once it is called before the consumer is executed. No need to disconnect here.
       openConn();
       try 
       {
            if (conn == null || conn.isClosed())
                System.out.printf("Cannot delete records, no connection or connection closed%n");

            pstmt = conn.prepareStatement(
                    "TRUNCATE TABLE tunas");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        }
    }

}

