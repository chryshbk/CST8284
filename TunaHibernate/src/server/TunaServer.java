/* File TunaServer.java
 * Author: Todd Kelley
 * Modified By: Stanley Pieda, Chrystian Santos
 * Modified On: November 2018
 * Description: RMI Server startup.
 */
package server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
/*
 * Referenced works on shutting down RMI within the application:
 * https://coderanch.com/t/210114/java/Shut-RMI-Registry
 * https://community.oracle.com/thread/1180058?start=0&tstart=0
 */

/**
 * TunaServer which uses RMI to establish connection to the client.
 * @author Chrystian Santos
 */
public class TunaServer {
    public static void main(String[] args) {
    	try {
            int portNum = 1099;
            if(args.length > 0){ portNum = Integer.parseInt(args[0]); }

            TunaServiceImpl tunaServiceImpl = new TunaServiceImpl(); // replace null

            // Creates RMI registry, saving reference to it
            LocateRegistry.createRegistry(1099);
            LocateRegistry.getRegistry();

            // Message to users (this is done already on, next line)
            System.out.println( "Registry created" );

            // Exports the remote service
            UnicastRemoteObject.exportObject(tunaServiceImpl, portNum);

            // Rebind using portNum with service name /TunaService
            Naming.rebind("//localhost:" + portNum + "/EchoService", tunaServiceImpl);

            // Message to users (this is done already on next line)
            System.out.println( "Exported" );

            System.out.println("Press any key to shutdown remote object and end program");
            Scanner input = new Scanner(System.in);
            input.nextLine(); // pause, let server-side admin close down connections

            tunaServiceImpl.shutDownDao(); // close down Hibernate resources

            System.out.println("un-exporting remote object");
            UnicastRemoteObject.unexportObject(tunaServiceImpl, true); // remove remote object
	} catch (NumberFormatException | MalformedURLException | RemoteException e) {
            System.out.println("Trouble: " + e);
            e.printStackTrace();
	}
    }
}
