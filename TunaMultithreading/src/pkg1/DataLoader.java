package assignment.pkg1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/* File: DataLoader.java
 * Author: Stanley Pieda
 * Date: August, 2018
 * Description: Orchestrates multithreaded, producer, consumer for reading and inserting records.
 */

public class DataLoader {
    private static final Logger LOGGER = Logger.getLogger( DataLoader.class.getName() );
	/**
	 * The code to detect when all threads are completed to get the timer to completion
	 * was taken after:
	 * App Shah. (July 15, 2017). How to Run Multiple Threads Concurrently in Java? ExecutorServiceApproach.
	 * Retrieved from
	 * http://crunchify.com/how-to-run-multiple-threads-concurrently-in-java-executorservice-approach/
	 */
	public void processRecords() 
        {
            long elapsedTime;
            try 
            {
                // comment or un-comment and re-run to test each buffer type
                //Buffer buffer = new CircularBuffer();
                Buffer buffer = new BlockingQueueBuffer();

                ExecutorService executor = Executors.newCachedThreadPool();
                Producer producer = new Producer(buffer);
                Consumer consumer = new Consumer(buffer);
			
                new TunaCleaner().deleteAllTuna();
			
                long startTime = System.currentTimeMillis();
                //new Thread(producer).start();
                //new Thread(consumer).start();
                        
                executor.execute(producer);
                executor.execute(consumer);
                executor.awaitTermination(50, TimeUnit.SECONDS);
                executor.shutdown();
                //try
                //{
                    //Thread.sleep(50000);
                //} catch(InterruptedException ie){
                    //LOGGER.log(Level.SEVERE, ie.getMessage());
                    //Thread.currentThread().interrupt();
                //}
                        
                long endTime = System.currentTimeMillis();			
                elapsedTime = endTime - startTime;
                int minutes = (int)elapsedTime / 1000 / 60;
                int seconds = (int)elapsedTime / 1000 % 60;
			
                LocalDateTime dateTime = LocalDateTime.now();
                DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");

                System.out.printf("%n%d records read in total%n", producer.getRecordsRead());
                System.out.printf("%d records inserted in total%n", consumer.getRecordsInserted());
                System.out.printf("%d mileseconds elapsed%n", elapsedTime);
                System.out.printf("%02d minutes, %02d seconds, %03d millisecs%n", minutes, seconds, elapsedTime % 1000);
                System.out.printf("Program by: Chrystian Santos run on %s%n", dateTime.format(format));
                System.out.printf("Buffer type is %s%n", buffer.getClass().getName());
            } catch(Exception ex) {
                LOGGER.log(Level.SEVERE, ex.getMessage());
            }
	}
}
