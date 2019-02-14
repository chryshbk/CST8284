package client;

/* File: TunaClient.java
 * Author: Stanley Pieda, based on course example by Todd Kelley, Chrystian Santos, Carlos Barroso
 * Modified Date: October 2018
 * Description: Networking client that uses simple protocol to send and receive transfer objects.
 */
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.UUID;

import datatransfer.Tuna;
import datatransfer.Message;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Class created to act as a client, connecting to a server to try to
 * save tunas in the database
 *
 * @author Chrystian Santos
 */
public class TunaClient {

    private static final Logger LOGGER = Logger.getLogger( TunaClient.class.getName() );
    private String captureMessage = "";
    private static String serverName = "DESKTOP-EJ329VV"; // partner's computer name
    //private static String serverName = "localhost";
    private int portNum = 8081;
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
                (new TunaClient(serverName, 8081)).runClient();
        }

    }

    /**
     * Constructor to set the server name and the port number
     * @param serverName the server name
     * @param portNum  the port number
     */
    public TunaClient(String serverName, int portNum) {
        this.serverName = serverName;
        this.portNum = portNum;
    }

    /**
     * method that will run a new client, asking to create a new Tuna
     * and to send to the server.
     * This method handles the messages returned from the server
     */
    public void runClient() {
       
        /**
         * create variables for the host name, connection, output and input stream, 
         * Message object and creates objects for dataTime and date format
         */
        String myHostName = null;
        Socket connection = null;
        ObjectOutputStream output = null;
        ObjectInputStream input = null;
        Message message = null;
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
        
        //print the firt message to connect to the server
        System.out.printf("TunaClient by: Carlos Barroso run on %s%n", dateTime.format(format));
	System.out.println("Connecting...");
        
        try {
            //try to gete the localhost and hostname
            InetAddress myHost = Inet4Address.getLocalHost();
            myHostName = myHost.getHostName();
        } catch (UnknownHostException e1) {
            e1.printStackTrace();
        }
        try {

            //initialize connection, output and input objects
            connection = new Socket(InetAddress.getByName(serverName), portNum);
            output = new ObjectOutputStream(connection.getOutputStream());
            input = new ObjectInputStream(connection.getInputStream());
            
            //boolean control to start the while loop
            boolean controller = true;

            do {
               
                //new Tuna and new message objects
                Tuna tuna = new Tuna();
                message = new Message("insert");

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

                message.setTuna(tuna);
    
                //send the object to the server
                output.writeObject(message);
                output.flush();
                
                //read the object returned from the server
                message = (Message) input.readObject();
                   
                //if successfuly inserted
                if(message.getCommand().equalsIgnoreCase("insert_success")) {
                    System.out.println("Command: "+message.getCommand()+ " Returned Tuna: "+ tuna);
                } 
                
                //if failed
                if(message.getCommand().equalsIgnoreCase("insert_failed")){
                    System.out.println("Server failed to perform requested operation.");
                    System.out.println("Shutting down connection to server");
                    break;
                }
                
                do {
                    //message to ask the user to create a new user
                System.out.println("Do you want to insert a new Tuna? (Y/N) ");
                captureMessage = br.readLine();
                } while (!captureMessage.toLowerCase().equalsIgnoreCase("n") && !captureMessage.toLowerCase().equalsIgnoreCase("y"));
                
        
                //if no, disconnect from the server
                if (captureMessage.toLowerCase().equals("n")) {
                    message = new Message("disconnect");
                    output.writeObject(message);
                    System.out.println("Shutting down connection to server");
                    
                    //set the controller to false to exit the looping
                    controller = false;
                }
            } while (controller);
   
        } catch (IOException | ClassNotFoundException exception) {
            System.err.println("Server not found");
            LOGGER.log(Level.SEVERE, exception.getMessage());
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
            } catch (IOException ex) {
                LOGGER.log(Level.SEVERE, ex.getMessage());
            }
            try {
                if (output != null) {
                    output.flush();
                    output.close();
                }
            } catch (IOException ex) {
                LOGGER.log(Level.SEVERE, ex.getMessage());
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (IOException ex) {
                LOGGER.log(Level.SEVERE, ex.getMessage());
            }
        }
    }
}
