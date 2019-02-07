package network;

import java.util.Observer;
/**
 * ShannonsController.java: This interface controls the ShannonsModel class. It has three methods, one of them is a method from the Observer interface, the other two are
 * setters from the ShannonsModel class.
 * @author Chrystian Rafael Sanches dos Santos
 * @version 1.0.0 Date November 27 2017
 */
public interface ShannonsController 
{
	/**
	 * Adds an observer to a class that implements Observer.
	 * @param ob - Observer object
	 */
	public void addObserver(Observer ob);
	/**
	 * Sets the value of bandwidth.
	 * @param bandwidth - double
	 */
	public void setBandwidth(double bandwidth);
	/**
	 * Sets the value of signal to noise.
	 * @param signalToNoise - double
	 */
	public void setSignalToNoise(double signalToNoise);
}
