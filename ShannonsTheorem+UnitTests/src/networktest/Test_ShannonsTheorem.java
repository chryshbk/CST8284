package networktest;

import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import network.ShannonsTheorem;

/**
 *	JUnit tests for the ShannonsTheorem class from the "network" project which calculates the maximum data rate.
 * @author Chrystian Rafael Sanches dos Santos.
 * @version 1.0.0 October 05, 2017
 */
public class Test_ShannonsTheorem extends Assert{

	private ShannonsTheorem shannonsTheorem;
	
	/**
	 * Constructor for the Test_ShannonsTheorem class.
	 */
	public Test_ShannonsTheorem() {}
	
	/**
	 * It executes before the tests.
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception { shannonsTheorem = new ShannonsTheorem(); }

	/**
	 * It executes after the tests.
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception { shannonsTheorem = null; }

 	/**
 	 * This test method is going to test the constructor from the ShannonsTheorem class and see if the value is not null.
 	 */
	@Test
	public void testConstructor() 
	{
		System.out.println("\tExecuting Test_ShannonsTheorem.testConstructor");
		assertNotNull(shannonsTheorem);
	}

	/**
	 * Test to check if negative bandwidth is allowed in the ShannonsTheorem class
	 * @see ShannonsTheorem#setBandwidth(double)
	 */
	@Test
	public void testNegativeBandwidth() 
	{
		System.out.println("\tExecuting Test_ShannonsTheorem.testNegativeBandwidth");
		try {
			shannonsTheorem.setBandwidth(-102102);
			assertTrue(false);
		} catch (IllegalArgumentException iae) {
			assertTrue(true);
		}
	}

	/**
	 * Test to check if negative signal to noise is allowed in the ShannonsTheorem class
	 * @see ShannonsTheorem#setSignalToNoise(double)
	 */
	@Test
	public void testNegativeSignalToNoise() 
	{
		System.out.println("\tExecuting Test_ShannonsTheorem.testNegativeSignalToNoise");
		try {
			shannonsTheorem.setSignalToNoise(-20139140);
			assertTrue(false);
		} catch (IllegalArgumentException iae) {
			assertTrue(true);
		}
	}
	
	/**
	 * This method is going to test setters and getters of bandwidth from the ShannonsTheorem class and check if it is getting the value of bandwidth.
	 * @see ShannonsTheorem#getBandwidth()
	 * @see ShannonsTheorem#setBandwidth(double) 
	 */
	@Test
	public void testBandwidth() 
	{
		System.out.println("\tExecuting Test_ShannonsTheorem.testBandwidth");
		shannonsTheorem.setBandwidth(10);
		double expected = 10;
		assertEquals(expected, shannonsTheorem.getBandwidth(), 0);
	}
	
	/**
	 * This method is going to test setters and getters of signal to noise from the ShannonsTheorem class and check if it is getting the value of signal to noise.
	 * @see ShannonsTheorem#getSignalToNoise()
	 * @see ShannonsTheorem#setSignalToNoise(double) 
	 */
	@Test
	public void testSignalToNoise() 
	{
		System.out.println("\tExecuting Test_ShannonsTheorem.testSignalToNoise");
		shannonsTheorem.setSignalToNoise(5);
		double expected = 5;
		assertThat(shannonsTheorem.getSignalToNoise(), Is.is(expected));
	}
	
	/**
	 * This method is going to test the method getMaximumDataRate() from the ShannonsTheorem class and check if the calculation matches the result.
	 * @see ShannonsTheorem#getMaximumDataRate()
	 */
	@Test
	public void testMaximumDataRate() 
	{
		System.out.println("\tExecuting Test_ShannonsTheorem.testMaximumDataRate");
		shannonsTheorem.setBandwidth(2);
		shannonsTheorem.setSignalToNoise(2);
		double expected = 2.74;
		assertThat("It should be the same ",shannonsTheorem.getMaximumDataRate(), Is.is(expected));
	}
	
	/**
	 *	Main line for standalone operation.
	 *	@param	args	Standard string command line parameters.
	 */
	public static void main(String[] args) {
	      System.out.println("Executing Test_ShannonsTheorem: ");
	      Result result = JUnitCore.runClasses(Test_ShannonsTheorem.class);
			
	      for (Failure failure : result.getFailures())
	         System.out.println(failure.toString());
			
	      System.out.println(result.wasSuccessful());
	  }
}
