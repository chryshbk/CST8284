/* File TunaService.java
 * Author: Stanley Pieda
 * Modified By: Chrystian Santos
 * Modified On: November 2018
 * Description: Remote (RMI) interface for a Remote Object
 *              that will allow Cabbage objects to be inserted
 *              and searched by uuid String.
 */
package remoteinterface;

import java.rmi.Remote;
import java.sql.SQLException;

import datatransfer.Tuna;

/**
 * Methods for use by remote clients are listed here
 * @author Stanley Pieda
 * Modified by: Chrystian Santos
 */
public interface TunaService extends Remote {
	
	/**
	 * Should allow remote client to insert a Tuna into data store
	 * @param tuna the Tuna to be inserted
	 * @throws java.rmi.RemoteException
         * @throws java.sql.SQLException
	 */
	public void insert(Tuna tuna) throws java.rmi.RemoteException, SQLException;
	
	/**
	 * Should allow remote client to lookup a Tuna based on UUID
	 * @param uuid A String based UUID
	 * @return A Tuna object, retrieved with given uuid, or null if uuid not in database
	 * @throws java.rmi.RemoteException
         * @throws java.sql.SQLException
	 */
	public Tuna findByUUID(String uuid) throws java.rmi.RemoteException, SQLException;
}
