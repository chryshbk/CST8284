package assignment.pkg1;

/* File: Buffer.java
 * Author: Chrystian Rafael Sanches dos Santos
 * Date: September 20, 2018
 * Description: Buffer interface to be used by CircularBuffer.java and BlockingQueueBuffer.java.
 */
public interface Buffer 
{
    /**
     * Puts a Tuna element into an array/variable depending on what type of buffer you are using, throws exception if something abnormal happens.
     * @param tuna element
     * @throws InterruptedException in case something unusual happens 
     */
    public void blockingPut(Tuna tuna) throws InterruptedException;
    /**
     * Gets a Tuna element and throws exception if something abnormal happens.
     * @return Tuna element
     * @throws InterruptedException in case something unusual happens
     */
    public Tuna blockingGet() throws InterruptedException;
}
