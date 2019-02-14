package server;
/* File: TunaServer.java.
 * Author: Stanley Pieda, based on course materials by Todd Kelley, Chrystian Santos, Carlos Barroso.
 * Modified Date: October 2018.
 * Description: Simple echo client.
 */
import dataaccesslayer.DataSource;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import datatransfer.Message;
import dataaccesslayer.TunaDaoImpl;
import java.sql.SQLException;

/**
 * Need programming comments with correct author name throughout this class
 * @author Chrystian Santos
 * @since October 2018
 * Description: Networking server that uses simple protocol to handle multiple clients at the same time.
 */
public class TunaServer {

        private static final Logger LOGGER = Logger.getLogger( TunaServer.class.getName() );
        private Message message;
        private TunaDaoImpl tunaDao;
	private ServerSocket server;
	private Socket connection;
	private int portNum = 8081;
	public static ExecutorService threadExecutor = Executors.newCachedThreadPool();

    /**
     * Main method that runs the server.
     * @param args 
     */
    public static void main(String[] args) {
        if(args.length > 0){
            (new TunaServer(Integer.parseInt(args[0]))).runServer();
        } else{
            (new TunaServer(8081)).runServer();
        }
    }
    
    /**
     * TunaServer constructor.
     * @param portNum 
     */
    public TunaServer(int portNum){
	this.portNum = portNum;
    }
    
    /**
     * Method which establishes connection with the client by sending messages, receiving and performing actions according to the message received.
     * @param connection 
     */
    public void talkToClient(final Socket connection) {
	threadExecutor.execute( () -> {	// Thread executing the service using a lambda sign instead of new Runnable.
            ObjectOutputStream output = null;
            ObjectInputStream input = null;
            System.out.println("Got a connection"); // Once the client is connected.
            try {
                    SocketAddress remoteAddress = connection.getRemoteSocketAddress();
                    String remote = remoteAddress.toString();
                    output = new ObjectOutputStream (connection.getOutputStream());
                    input = new ObjectInputStream( connection.getInputStream()); 
                    tunaDao = new TunaDaoImpl();
                    do {
                        try {
                            message = (Message) input.readObject(); //Reading the message from the client
                        } catch(EOFException | SocketException eof){
                            LOGGER.log(Level.FINE, eof.getMessage());
                            break;
                        }
                        if(message == null){
                            try {
                                wait(); // Wait for the next message.
                            } catch(InterruptedException ie){ LOGGER.log(Level.SEVERE, ie.getMessage()); }
                        }
                            if(message.getCommand().equalsIgnoreCase("insert")) {
                                try {
                                    System.out.println("From: " + remote + " Command: add Tuna: "+message.getTuna());
                                    tunaDao.insertTuna(message.getTuna()); // Try to insert it into the database.
                                    if(tunaDao.getTunaByUUID(message.getTuna().getUUID()) != null) {
                                        message = new Message("insert_success"); // Success if the UUID can be retrieved (Message will be sent later).
                                    } else { message = new Message("insert_failed"); }
                                } catch(SQLException se){ 
                                    message = new Message("insert_failed");
                                    LOGGER.log(Level.SEVERE, se.getMessage());
                                    break;
                                } catch(NullPointerException npe){
                                    message = new Message("insert_failed");
                                    System.out.println(npe.getMessage());
                                    break;
                                }
                            }
                            else if (message.getCommand().equalsIgnoreCase("disconnect")) { //If the client disconnects
                                System.out.println("From: " + remote + " Command: "+message.getCommand()+ " Tuna: "+message.getTuna());
                                break;
                            } 
                            output.writeObject(message); // Sends message to the client.
                            output.flush();
			} while (message != null);
                    System.out.println("Client "+remote + " disconnected via request"); // Gets here if the client disconnects
	        } catch (IOException | ClassNotFoundException exception) { // Multi-catch block
	            LOGGER.log(Level.SEVERE, exception.getMessage());
	        } 
                finally {
                    try { if(input != null){ input.close(); } // Closes the ObjectInputStream.
                    } catch(IOException ex){ LOGGER.log(Level.SEVERE, ex.getMessage()); }
                    try { if(output != null){ output.flush(); output.close();} // Closes the ObjectOutputStream.
                    } catch(IOException ex){ LOGGER.log(Level.SEVERE, ex.getMessage()); }
                    try { if(connection != null){ connection.close(); } // Closes the Socket.
                    } catch(IOException ex){ LOGGER.log(Level.SEVERE, ex.getMessage()); }
                    try{ if(DataSource.getConnection() != null){ DataSource.closeConnection(); }
                    } catch(SQLException ex){System.out.println(ex.getMessage());}
	        }
	});
    }
    /**
     * Method that runs the server.
     */
    public void runServer(){
	try {
            server = new ServerSocket(portNum);
	}catch (IOException e){
            LOGGER.log(Level.SEVERE, e.getMessage());
	}
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
        System.out.printf("TunaServer by: Chrystian Santos run on %s%n", dateTime.format(format));
	System.out.println("Listening for connections...");
	while(true) {
            try{
		connection = server.accept(); //Accept connections
		talkToClient(connection); // Talk to client X
                
            } catch (IOException exception) {
		LOGGER.log(Level.SEVERE, exception.getMessage());
            }
	}
    }
}
