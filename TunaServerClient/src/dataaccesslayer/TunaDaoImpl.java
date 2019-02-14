package dataaccesslayer;
/* File: TunaDaoImpl.java
 * Author: Stanley Pieda
 * Modified by: Chrystian Santos
 * Date: Sept, 2017
 * Modified Date: October, 2018
 * References:
 * Ram N. (2013). Data Access Object Design Pattern or DAO Pattern [blog] Retrieved from
 * http://ramj2ee.blogspot.in/2013/08/data-access-object-design-pattern-or.html
 */ 
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import datatransfer.Tuna;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Partially complete implementation for DAO design pattern.
 * Has one insert method, and one find-by-UUID method.
 * @author Stanley Pieda
 * Modified by: Chrystian Santos
 */
public class TunaDaoImpl implements TunaDao
{
    private static final Logger LOGGER = Logger.getLogger( TunaDaoImpl.class.getName() );
	/** 
	 * Returns a reference to a Tuna object, loaded with data
	 * from the database, based on lookup using a UUID as String
	 * @param uuid String based UUID
	 * @return Tuna transfer object, or null if no match based on uuid found
	 * @throws SQLException
	 * @author Stanley Pieda
         * Modified by: Chrystian Santos
	 */
	@Override
	public Tuna getTunaByUUID(String uuid) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Tuna tuna = null;
		try{
			pstmt = DataSource.getConnection().prepareStatement(
					"SELECT * FROM Tunas WHERE uuid = ?");
			pstmt.setString(1, uuid);
			
			rs = pstmt.executeQuery();
			
			rs.next();
			tuna = new Tuna();
			tuna.setId(rs.getInt("id"));
			tuna.setRecordNumber(rs.getInt("recordnumber"));
			tuna.setOmega(rs.getString("omega"));
			tuna.setDelta(rs.getString("delta"));
			tuna.setTheta(rs.getString("theta"));
			tuna.setUUID(rs.getString("uuid"));
		} catch(SQLException ex) {
			System.out.println(ex.getMessage());
                        LOGGER.log(Level.SEVERE, "");
		} catch(NullPointerException npe){ System.out.println(npe.getMessage());}
		finally{
			try{ if(rs != null){ rs.close(); }}
			catch(SQLException ex){System.out.println(ex.getMessage());}
			try{ if(pstmt != null){ pstmt.close(); }}
			catch(SQLException ex){System.out.println(ex.getMessage());}
		}
		return tuna;
	}

    /**
	 * Accepts a Tuna object reference, inserting the encapsulated data into database.
	 * @param tuna with data for record insertion
	 * @throws SQLException
	 * @author Stanley Pieda
         * Modified by: Chrystian Santos
	 */
        @Override
	public void insertTuna(Tuna tuna) throws SQLException{
            PreparedStatement pstmt = null;
            try{
                pstmt = DataSource.getConnection().prepareStatement(
				"INSERT INTO Tunas (recordnumber, omega, delta, theta, uuid) " +
				"VALUES(?, ?, ?, ?, ?)");
		pstmt.setInt(1, tuna.getRecordNumber());
		pstmt.setString(2, tuna.getOmega());
		pstmt.setString(3, tuna.getDelta());
		pstmt.setString(4, tuna.getTheta());
		pstmt.setString(5, tuna.getUUID());
		pstmt.executeUpdate();
            } catch(SQLException ex) {
		System.out.println(ex.getMessage());
		throw ex;
            } catch(NullPointerException npe){
            }finally{
		try{ if(pstmt != null){ pstmt.close(); }}
		catch(SQLException ex){System.out.println(ex.getMessage());}
            }
	}
}
