package assignment.pkg1;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/* File: Producer.java
 * Author: Chrystian Rafael Sanches dos Santos
 * Date: September 20, 2018
 * Description: Implements Runnable interface. Opens a .csv file containing Tuna objects, reads the file and stores it into a Buffer.
 */
public class Producer implements Runnable
{
    private final Buffer SHAREDBUFFER;
    
    private static final Logger LOGGER = Logger.getLogger( Producer.class.getName() );
    
    private final String FILEPATH1 = "CST8277_DataSet_18F_1000.csv";
    //private final String FILEPATH2 = "CST8277_DataSet_18F_10000.csv";
    //private final String FILEPATH3 = "CST8277_DataSet_18F_100000.csv";
    
    private int recordsRead = 0;
    private int maxSize = 100;
    
    private Scanner tunaScanner = null;
    
/**
 * Constructor which takes a Buffer as a parameter.
 * @param sharedBuffer buffer object
 */
    public Producer(Buffer sharedBuffer)
    {
        this.SHAREDBUFFER = sharedBuffer;
    }
    
    /**
     * It opens the file using a java.util.Scanner and java.io.File libraries and reads the file using a loop until it finds the last element.
     * While it is reading, it creates a new Tuna object and set values to this object fields. If it flags the last Tuna from the file, it labels the last tuna.
     * After reading it, within the try-catch block, it uses a Buffer object to put the Tuna object into the Buffer and uses the notifyAll method to synchronize the threads.
     * Once the Scanner is done reading, it closes the Scanner.
     */
    @Override
    public void run()
    {
        try
        {
            tunaScanner = new Scanner(new File(FILEPATH1)); // Opens the file path 1.
            //tunaScanner = new Scanner(new File(FILEPATH2)); // Opens the file path 2.
            //tunaScanner = new Scanner(new File(FILEPATH3)); // Opens the file path 3.

            // Looping through the elements in the file.
            while(tunaScanner.hasNext())
            {
                String line = tunaScanner.nextLine();
                String[] fields = line.split(",");
                Tuna tuna = new Tuna();
                tuna.setRecordNumber(Integer.parseInt(fields[0]));
                tuna.setOmega(fields[1]);
                tuna.setDelta(fields[2]);
                tuna.setTheta(fields[3]);
                tuna.setUUID(fields[4]);
                
                try
                {
                    // Labels the last Tuna object from the file.
                    if(!tunaScanner.hasNext())
                        tuna.setLastTuna(true);
                     
                    // Every time it reaches 100 records read, it raises the maxSize variable to more 100 and adds one to the recordsRead variable.
                    if (recordsRead == maxSize)
                    {
                        LOGGER.log(Level.FINEST, "Producer produced {0} records", getRecordsRead());
                        maxSize = maxSize + 100;
                        recordsRead++;
                    } 
                    else
                        recordsRead++;
                    
                    // Synchronizing it to make it thread safe. Notify the world that you are putting a Tuna object into the Buffer.
                    synchronized(this)
                    {   
                        SHAREDBUFFER.blockingPut(tuna);
                        if(tuna.isLastTuna())
                            LOGGER.log(Level.FINEST, "Done reading the file. Last Tuna should be labeled. {0} records were read in total", getRecordsRead());
                        
                        notifyAll();                      
                    }
                }catch (InterruptedException ie){ LOGGER.log(Level.SEVERE, ie.getMessage()); } // thrown in case of error in the synchronized methods.
            }
       } catch(IOException io){ LOGGER.log(Level.SEVERE, io.getMessage()); }  //thrown in case of error in the File.
        
        //Closes the Scanner.
        if(tunaScanner != null)
            tunaScanner.close();
    }
    
    /**
     * Returns the records that were read by the producer.
     * @return recordsRead variable that contains the records read by the Producer
     */
    public int getRecordsRead() { return recordsRead;}
}
