package assignment.pkg1;

import java.util.logging.Level;
import java.util.logging.Logger;

/* File: CircularBuffer.java
 * Author: Chrystian Rafael Sanches dos Santos
 * Date: September 20, 2018
 * Description: Implements Buffer interface, creates synchronizing access to a shared three-element bounded buffer and establish 'conversation' between Producer and Consumer using synchronized methods.
 */
public class CircularBuffer implements Buffer
{
    //Array of Tuna objects
    protected Tuna[] tunaArray = new Tuna[100];
    
    //Logger for debugging purposes.
    private static final Logger LOGGER = Logger.getLogger( CircularBuffer.class.getName() );
    
    private int occupiedCans = 0;
    private int writeIndex = 0;
    private int readIndex = 0;
  
    /**
     * Populates the array storing a Tuna object into its cells, writeIndex is used to know which cell from the array to write into next and notifies the world about it. Once it is full, it waits until the cells are empty again.
     * @param tuna element
     * @throws InterruptedException in case something unusual with the synchronized methods happens
     */
    @Override
    public synchronized void blockingPut(Tuna tuna) throws InterruptedException
    {
        //Checks if the array is full is full.
        while(occupiedCans == tunaArray.length)
        {
            LOGGER.log(Level.FINE,"Buffer is waiting. Producer is full");
            wait();
        }
        
        //Populates the cell with this Tuna object.
        tunaArray[writeIndex] = tuna;
        
        //Updates the writeIndex.
        writeIndex = (writeIndex + 1) % tunaArray.length;

        ++occupiedCans;

        //Tell the world(threads) what you did.
        notifyAll(); 
    }
    
    /**
     * Returns the Tuna object, uses a readIndex variable to check which cell from the array was read, updates the index, subtracts the cell used and returns a Tuna element that was read.
     * @return tuna element
     * @throws InterruptedException in case something unusual with the synchronized methods happens
     */
    @Override
    public synchronized Tuna blockingGet() throws InterruptedException
    {
        //Checks if the buffer is empty.
        while (occupiedCans == 0)
        {
            LOGGER.log(Level.FINE, "Buffer is empty. Consumer waits");
            wait();
        }
        
        //Populates the Tuna object which was read.
        Tuna tuna = tunaArray[readIndex];
        
        //Updates the circular read index.
        readIndex = (readIndex + 1) % tunaArray.length;

        --occupiedCans;
        
        //Tell the world(threads) what you did.
        notifyAll();
        
        return tuna;
    }
}
