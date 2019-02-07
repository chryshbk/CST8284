package network;

import java.util.Observer;

/**
 * ShannonsTheorem.java: This class creates a small calculator using the Shannons Theorem, which requires a bandwidth value
 * and a signal to noise value in order to return the maximum data rate using the appropriate calculation for this result.
 * It uses a ShannonsModel object using its methods and it also implements ShannonsController.
 * @author    Chrystian Rafael Sanches dos Santos
 * @version   1.0.0 Date October 5 2017
 */
public class ShannonsTheorem implements ShannonsController 
{
	private ShannonsModel model;
	/**
	 *	Default constructor of the ShannonsTheorem class.
	 */
	public ShannonsTheorem() { 	}
	
	/**
	 * @see ShannonsModel#getBandwidth()
	 */
	public double getBandwidth() { return model.getBandwidth(); }
	
	/**
	 * @see ShannonsModel#setBandwidth(double)
	 */
	@Override
	public void setBandwidth(double bandwidth) { model.setBandwidth(bandwidth); }
	
	/**
	 * @see ShannonsModel#getSignalToNoise()
	 */
	public double getSignalToNoise() { return model.getSignalToNoise(); }
	
	/**
	 * @see ShannonsModel#setSignalToNoise(double)
	 */
	@Override
	public void setSignalToNoise(double signalToNoise) { model.setSignalToNoise(signalToNoise); }
	
	/**
	 * @see ShannonsModel#getMaximumDataRate()
	 */
	public double getMaximumDataRate() { return model.getMaximumDataRate(); }

	/**
	 * It sets the ShannonsModel object.
	 * @param model ShannonsModel object
	 */
	private void setModel(ShannonsModel model) { this.model = model; }

	/**
	 * This method adds an Observer object from the ShannonsModel class, which extends Observable.
	 * @param ob - Observer object
	 */
	@Override
	public void addObserver(Observer ob) { model.addObserver(ob);}
	/**
	 * @see ShannonsModel#toString()
	 */
	@Override
	public String toString() { return model.toString(); }
	/**
	 * Standard main method.
	 * @param args String array
	 */
	public static void main(String args[]) 
	{ 
		ShannonsTheorem st = new ShannonsTheorem();
		ShannonsView shannonsView = new ShannonsView();
		ShannonsModel model = new ShannonsModel();
		st.setModel(model);
		st.addObserver(shannonsView);
		shannonsView.setController(st);
		shannonsView.run();
	}
	
}
