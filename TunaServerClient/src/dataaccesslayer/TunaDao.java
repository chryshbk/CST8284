/* File: TunaDao.java
 * Author: Stanley Pieda
 * Date: Jan 2018
 * Description: Sample solution to Assignment 2
 * References:
 * Ram N. (2013).  Data Access Object Design Pattern or DAO Pattern [blog] Retrieved from
 * http://ramj2ee.blogspot.in/2013/08/data-access-object-design-pattern-or.html
 */
package dataaccesslayer;

import java.sql.SQLException;
import datatransfer.Tuna; 

/**
 * Partially complete interface for DAO design pattern.
 * Has one insert method, and one find-by-UUID method.
 * @author Stanley Pieda
 */
public interface TunaDao 
{
	/** 
	 * Should return a reference to a Tuna object, loaded with data
	 * from the database, based on lookup using a UUID as String
	 * @param uuid String based UUID
	 * @return Tuna transfer object, or null if no match based on uuid found
	 * @throws SQLException
	 * @author Stanley Pieda
	 */
	Tuna getTunaByUUID(String uuid) throws SQLException;
	
	/**
	 * Should accept a Tuna object reference, insert the encapsulated data into database.
	 * @param tuna with data for record insertion
	 * @throws SQLException
	 * @author Stanley Pieda
	 */
	void insertTuna(Tuna tuna) throws SQLException;
}
