/*
 *  @(#)ShannonsTheorem.java   1.0 YY/MM/DD
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
 */

package network;													/*	Package for class placement	*/

import java.util.Observable;

/**
 * ShannonsModel.java: This class creates a small calculator using the ShannonsTheorem, which requires a bandwidth value
 * and a signal to noise value in order to return the maximum data rate using the appropriate calculation for this result.
 * It extends Observable in order to be observed by the ShannonsTheorem.java class.
 * @author    Chrystian Rafael Sanches dos Santos
 * @version   1.0.0 Date October 5 2017
 */
public class ShannonsModel extends Observable
{

	 private double bandwidth;
	 private double signalToNoise;
	 
	/* CONSTRUCTORS	--------------------------------------------------	*/
	/**
	 *	Default constructor of the class ShannonsModel.
	 */
	public ShannonsModel()	
	{ 
		super();
		bandwidth = 0.0;
		signalToNoise = 0.0;
	}

	/* ACCESSORS	-----------------------------------------------------	*/

	/**
	 * This method allows access to the private variable bandwidth.
	 * @return bandwidth
	 */
	public double getBandwidth() { return bandwidth; } // returns bandwidth.
	
	/**
	 * This method allows access to the private variable signalToNoise.
	 * @return signalToNoise
	 */
	public double getSignalToNoise() { return signalToNoise; } // returns signal to noise.
	/**
	 * This method calculates the maximum data rate using bandwidth in form of hertz and signal to noise.
	 * @param hertz bandwidth in form of hertz.
	 * @param signalToNoise same as the variable.
	 * @return the calculation of the maximum data rate.
	 */
	private double getMaximumDataRate(double hertz, double signalToNoise) {	return (hertz*(Math.log( (Math.pow( 10, (signalToNoise / 10)) + 1)) / Math.log(2))); }
	
	/**
	 * This method will get the result from the method getMaximumDataRate(double,double) containing only 2 decimal places after the calculation.
	 * @return getMaximumDataRate(double, double) - returns the result of the calculation with two parameters.
	 */
	public double getMaximumDataRate() { return Math.round(getMaximumDataRate(bandwidth, signalToNoise) * 100d) / 100d; }

	/* MODIFIERS	-----------------------------------------------------	*/
	/**
	 * Sets the value of bandwidth and throws an exception in case the number declared is less than 0.
	 * @param bandwidth - same as the variable bandwidth.
	 */
	public void setBandwidth(double bandwidth) 
	{ 
		if (bandwidth < 0)
			throw new IllegalArgumentException("Number less than 0 is invalid.");
		this.bandwidth = bandwidth; // sets the value of bandwidth.
		setChanged();
		notifyObservers();
	} 
	/**
	 * Sets the value of signal to noise and throws an exception in case the number declared is less than 0.
	 * @param signalToNoise same as the variable signalToNoise.
	 */
	public void setSignalToNoise(double signalToNoise) 
	{ 
		if (signalToNoise < 0)
			throw new IllegalArgumentException("Number less than 0 is invalid.");
		this.signalToNoise = signalToNoise;  // sets the value of signal to noise.
		setChanged();
		notifyObservers();
	}
	/**
	 *	Returns the result of the calculation.
	 *	@return	String result containing 2 decimal places.
	 */
	 public String toString()	{ return Double.toString(getMaximumDataRate()); } // returns the result in form of string, with 2 decimal places only.
}	/*	End of CLASS:	ShannonsModel.java			*/