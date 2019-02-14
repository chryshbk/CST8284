package datatransfer;
/* File: Message.java.
 * Author: Chrystian Santos
 * Modified Date: October 2018.
 * Description: Message class that helps client and server to communicate.
 */
import java.io.Serializable;

/**
 * Message class that implements Serializable. It will send commands in form of String to both client and server. This way they will be able to communicate.
 * @author Chrystian Santos
 * @since October 2018
 */
public class Message implements Serializable{
    private static long serialVersionUID = 1L;
    private String command;
    private Tuna tuna;
    
    /**
     * Message constructor with an argument
     * @param command 
     */
    public Message(String command){
        this.command = command;
    }
    
    /**
     * Returns the command received. 
     * @return command
     */
    public String getCommand(){
        return command;
    }
    
    /**
     * Gets a Tuna object.
     * @return tuna
     */
    public Tuna getTuna(){
        return tuna;
    }
    
    /**
     * Sets a Tuna object.
     * @param tuna 
     */
    public void setTuna(Tuna tuna){
        this.tuna = tuna;
    }
}
