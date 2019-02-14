package assignment.pkg1;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/* File: BlockingQueueBuffer.java.
 * Author: Chrystian Rafael Sanches dos Santos
 * Date: September 20, 2018
 * Description: Implements Buffer interface. Creates a synchronized buffer using an ArrayBlockingQueue of Tuna objects.
 */
public class BlockingQueueBuffer implements Buffer 
{   
    //ArrayBlockingQueue of Tuna objects
    private final ArrayBlockingQueue<Tuna> buffer;
    
    //Logger created for debugging purposes.
    private static final Logger LOGGER = Logger.getLogger( BlockingQueueBuffer.class.getName() );
    
    /**
     * Constructor that initializes an ArrayBlockingQueue of Tuna objects.
     */
    public BlockingQueueBuffer()
    {
        buffer = new ArrayBlockingQueue<>(1);
        LOGGER.log(Level.FINEST,"Constructor successfully initialized");
    }
    
    /**
     * Puts a Tuna object into the ArrayBlockingQueue. Exception is thrown if not successful.
     * @param tuna element
     * @throws InterruptedException in case it cannot place the element into the buffer
     */
    @Override
    public void blockingPut(Tuna tuna) throws InterruptedException
    {
        buffer.put(tuna);
    }
    
    /**
     * Returns a Tuna object taken from the ArrayBlockingQueue. Exception is thrown if not successful.
     * @return buffer Tuna element taken from the buffer
     * @throws InterruptedException in case it cannot take anything from the buffer
     */
    @Override
    public Tuna blockingGet() throws InterruptedException
    { 
        return buffer.take();
    }
}
