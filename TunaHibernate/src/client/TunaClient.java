package client;

/* File: TunaClient.java
 * Author: Stanley Pieda, based on course example by Todd Kelley
 * Modified By: Chrystian Santos
 * Modified Date: November 2018
 * Description: Networking client that uses simple protocol to send and receive transfer objects.
 */
import java.io.IOException;
import java.net.*;
import java.util.UUID;

import datatransfer.Tuna;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.logging.Level;
import java.util.logging.Logger;
import remoteinterface.TunaService;

/**
 * Class created to act as a client, connecting to a server to try to
 * save tunas in the database
 *
 * @author Chrystian Santos
 */
public class TunaClient {

    private static final Logger LOGGER = Logger.getLogger( TunaClient.class.getName() );
    private String captureMessage = "";
    private static String serverName = "localhost";
    private int portNum = 1099;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Main method creating new tunaClient
     * @param args 
     */
    public static void main(String[] args) {
        switch (args.length) {
            case 2:
                (new TunaClient(args[0], Integer.parseInt(args[1]))).runClient();
                break;
            case 1:
                (new TunaClient(serverName, Integer.parseInt(args[0]))).runClient();
                break;
            default:
                (new TunaClient(serverName, 1099)).runClient();
        }

    }

    /**
     * Constructor to set the server name and the port number
     * @param serverName the server name
     * @param portNum  the port number
     */
    public TunaClient(String serverName, int portNum) {
        TunaClient.serverName = serverName;
        this.portNum = portNum;
    }

    /**
     * Method that will run a new client, asking to create a new Tuna
     * and to send to the server.
     * This method handles the messages returned from the server
     */
    public void runClient() {

        String myHostName = null;
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");

        TunaService tunaService = null;
        
        //print the first message to connect to the server
        System.out.printf("TunaClient run on %s%n", dateTime.format(format));
	System.out.println("Connecting...");
        
        try {
            //try to get the localhost and hostname
            InetAddress myHost = Inet4Address.getLocalHost();
            myHostName = myHost.getHostName();
        } catch (UnknownHostException uhe) {
            LOGGER.log(Level.SEVERE, uhe.getMessage());
        }
        try {
            // Uses RMI to return a reference, a stub for the remote object associated with the specified name 
            tunaService = (TunaService) Naming.lookup("rmi://"+ serverName + ":"+ portNum + "/EchoService"); 
            } catch (NotBoundException | MalformedURLException | RemoteException  ex) {
                LOGGER.log(Level.SEVERE, ex.getMessage());
            }
        //boolean control to start the while loop
        boolean controller = true;

        try {
                
            do {

                //new Tuna and new message objects
                Tuna tuna = new Tuna();

                System.out.println("Enter data for a new Tuna ");

                /**
                 * The following try/catch block will create a Tuna object and send to the server.
                 * It will handle if the insertion of the Tuna on the database is successful or not.
                 */
                try{
                   //set the Tuna record number
                    System.out.print("Please enter record number: ");
                    captureMessage = br.readLine();
                    tuna.setRecordNumber(Integer.valueOf(captureMessage));
                    captureMessage = " ";
                } catch (NumberFormatException e) {
                    LOGGER.log(Level.FINE, e.getMessage());
                    continue;
                } 

                while(captureMessage.equals(" ")) {
                   //set the omega
                    System.out.print("Please enter omega: ");
                    captureMessage = br.readLine();
                    tuna.setOmega(captureMessage);
                }
                captureMessage = " ";

                while(captureMessage.equals(" ")) {
                   //set the delta
                    System.out.print("Please enter delta: ");
                    captureMessage = br.readLine();
                    tuna.setDelta(captureMessage);
                }
                captureMessage = " ";

                while(captureMessage.equals(" ")) {
                   //set theta
                    System.out.print("Please enter theta: ");
                    captureMessage = br.readLine();
                    tuna.setTheta(captureMessage);
                }
                captureMessage = " ";

                //create a random uuid value
                UUID uuid = UUID.randomUUID();             
                tuna.setUUID(uuid.toString());

                //Checks if the service is still running. Otherwise, exits the loop.
                if(tunaService != null){
                    try{
                        tunaService.insert(tuna); // Starts the insert chain to insert the Tuna object.
                        System.out.println("Found "+tunaService.findByUUID(tuna.getUUID())+ " in the Database"); // only executed once the insert is done.
                    } catch(SQLException sqle){ LOGGER.log(Level.SEVERE, sqle.getMessage()); }
                } else{
                    controller = false;
                }

                do {
                   //message to ask the user to create a new user
                    System.out.println("Do you want to insert a new Tuna? (Y/N) ");
                    captureMessage = br.readLine();
                } while (!captureMessage.toLowerCase().equalsIgnoreCase("n") && !captureMessage.toLowerCase().equalsIgnoreCase("y"));


                //if no, disconnect from the server
                if (captureMessage.toLowerCase().equals("n")) {
                    System.out.println("Shutting down connection to server");

                   //set the controller to false to exit the looping
                    controller = false;
                }
            } while (controller);
   
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Server disconnected");
        }
    }
}
