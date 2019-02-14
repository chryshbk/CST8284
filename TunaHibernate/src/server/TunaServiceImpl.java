/* File TunaServiceImpl.java
 * Author: Stanley Pieda
 * Modified By: Chrystian Santos
 * Modified On: November 2018
 * Description: Remote Object that permits clients to insert
 *              Tuna records into a database, as well as
 *              retrieve them using String based uuid.
 */
package server;

import java.rmi.RemoteException;
import java.sql.SQLException;

import datatransfer.Tuna;

import dataaccesslayer.TunaDao;
import dataaccesslayer.TunaDaoImpl;

import remoteinterface.TunaService;

/**
 * TunaServiceImpl class which implements TunaService interface, doing all the procedures to talk to the database to insert a Tuna, 
 * find one by its uuid and shuts the instance down.
 * @author Chrystian Santos
 */
public class TunaServiceImpl implements TunaService  {

    /**
     * Default constructor, empty.
     */
    public TunaServiceImpl() {}
	
    /**
     * Method to insert the Tuna into the database which calls the insertTuna from the TunaDao interface.
     * @param tuna
     * @throws RemoteException
     * @throws SQLException 
     */
    @Override
    public void insert(Tuna tuna) throws RemoteException, SQLException{
	TunaDao dao = TunaDaoImpl.INSTANCE; // Sets the TunaDao interface as the TunaDaoImpl instance.
	dao.insertTuna(tuna); // Uses the insertTuna from TunaDao to insert the Tuna into the database.
    }

    /**
     * Method to find the Tuna by its UUID, which throws RemoteException and SQLException in case of an error.
     * @param uuid
     * @return Tuna
     * @throws RemoteException
     * @throws SQLException 
     */
    @Override
    public Tuna findByUUID(String uuid) throws RemoteException, SQLException{
	TunaDao dao = TunaDaoImpl.INSTANCE; // Sets the TunaDao interface as the TunaDaoImpl instance.
	return dao.findByUUID(uuid); // Returns the Tuna found by the TunaDao and its UUID.
    }
	
    /**
     * Shuts the TunaDaoImpl instance down.
     */
    public void shutDownDao() { TunaDaoImpl.INSTANCE.shutdown(); }
}
