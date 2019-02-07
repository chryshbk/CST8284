/*
 *  @(#)Test_ShannonsTheorem.java	Feb 17, 2005
 *
 *
 *  This software contains confidential and proprietary information
 *  of Dyer Consulting ("Confidential Information").  You shall not disclose
 *  such Confidential Information and shall use it only in accordance with the
 *  terms of the license agreement you entered into with Dyer Consulting.
 *
 *  This software is provided "AS IS,".  No warrantee of any kind, express
 *  or implied, is included with this software; use at your own risk, responsibility
 *  for damages (if any) to anyone resulting from the use of this software rests
 *  entirely with the user even if Dyer Consulting has been advised of the
 *  possibility of such damages.
 *
 *  This software is not designed or intended for use in on-line control of
 *  aircraft, air traffic, aircraft navigation or aircraft communications; or in
 *  the design, construction, operation or maintenance of any nuclear
 *  facility. Licensee represents and warrants that it will not use or
 *  redistribute the Software for such purposes.
 *
 *  Distribute freely, except: don't remove my name from the source or
 *  documentation, mark your changes (don't blame me for your possible bugs),
 *  don't alter or remove any of this notice.
 *
 */

package networktest;


import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.Result;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.notification.Failure;

import network.ShannonsModel;


/**
 *	JUnit tests for the ShannonsModel class from the "network" project which calculates the maximum data rate.
 * @author Chrystian Rafael Sanches dos Santos.
 * @version 2.0.0 October 05, 2017
 */
public class Test_ShannonsModel extends Assert {

	private ShannonsModel shannonsModel;
	
	/**
	 * Constructor for the Test_ShannonsModel class.
	 */
	public Test_ShannonsModel() {}

	/**
	 * It executes before the tests.
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception{
		shannonsModel = new ShannonsModel();
	}

	/**
	 * It executes after the tests.
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception{
		shannonsModel = null;
	}
 	/**
 	 * This test method is going to test the constructor from the ShannonsModel class and see if the value is not null.
 	 */
	@Test
	public void testConstructor() {
      System.out.println("\tExecuting Test_ShannonsModel.testConstructor");
		assertNotNull("\t\tTest_ShannonsModel.testConstructor: ShannonsTheorem is null"+shannonsModel);
	}
	
	/**
	 * This method is going to test setters and getters of bandwidth from the ShannonsModel class and check if it is getting the value of bandwidth.
	 * @see ShannonsModel#getBandwidth()
	 * @see ShannonsModel#setBandwidth(double)
	 */
	@Test
	public void testBandwidth() {
		System.out.println("\tExecuting Test_ShannonsModel.testBandwidth");
		shannonsModel.setBandwidth(10.0);
		assertThat(10.0, Is.is(shannonsModel.getBandwidth())); // check to see if it is the same number.
		
	}
	/**
	 * This method is going to test getters and setters related to signalToNoise from the ShannonsModel class and check if it is getting the value of signal to noise.
	 * @see ShannonsModel#getSignalToNoise()
	 * @see ShannonsModel#setSignalToNoise(double)
	 */
	@Test
	public void testSignalToNoise() {
		System.out.println("\tExecuting Test_ShannonsModel.testSignalToNoise");
		shannonsModel.setSignalToNoise(5);
		assertEquals(5, shannonsModel.getSignalToNoise(), 0);
	}
	
	/**
	 * This method is going to test the method getMaximumDataRate() from the ShannonsModel class and check if the calculation is getting the right result.
	 * @see ShannonsModel#getMaximumDataRate()
	 */
	@Test
	public void testMaximumDataRate() {
		System.out.println("\tExecuting Test_ShannonsModel.testMaximumDataRate");
		shannonsModel.setBandwidth(10);
		shannonsModel.setSignalToNoise(5);
		double expected = 20.57;
		assertEquals(shannonsModel.getMaximumDataRate(), expected, 0.2);
	}

	/**
	 * Test to check if negative bandwidth is allowed in the ShannonsModel class
	 * @see ShannonsModel#setBandwidth(double)
	 */
	@Test
	public void testNegativeBandwidth() {
      System.out.println("\tExecuting Test_ShannonsModel.testNegativeBandwidth");	
		try 
		{
			shannonsModel.setBandwidth(-1);
			assertTrue(false);
		} catch(IllegalArgumentException iae) {
			assertTrue(true);
		}
	}
	
	/**
	 * Test to check if negative signal to noise is allowed in the ShannonsModel class.
	 * @see ShannonsModel#setSignalToNoise(double)
	 */
	@Test
	public void testNegativeSignalToNoise() {
		System.out.println("\tExecuting Test_ShannonsModel.testNegativeSignalToNoise");	
		try 
		{
			shannonsModel.setSignalToNoise(-10);
			assertTrue(false);
		} catch(IllegalArgumentException iae) {
			assertTrue(true);
		}
	}
	
	/*	STAND-ALONE ENTRY POINT -----------------------------------------	*/
	/**
	 *	Main line for standalone operation.
	 *	@param	args	Standard string command line parameters.
	 */
	public static void main(String[] args) {
      System.out.println("Executing Test_ShannonsModel: ");
      Result result = JUnitCore.runClasses(Test_ShannonsModel.class);
		
      for (Failure failure : result.getFailures()) 
         System.out.println(failure.toString());
		
      System.out.println(result.wasSuccessful());
  }
}	/*	End of CLASS:	Test_ShannonsModel.java				*/
