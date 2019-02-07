package network;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.border.LineBorder;
/**
 * ShannonsJScrollBarPanel.java: This class extends JPanel and uses JScrollBar as a GUI for bandwidth, signal to noise and maximum data rate.
 * It also has a ShannonsController object to control the content of this class which is used in the constructor.
 * @author Chrystian Rafael Sanches dos Santos
 * @version 1.0.0 Date November 27 2017
 */
public class ShannonsJScrollBarPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private ShannonsController controller;
	private JScrollBar maximumDataRateScrollBar, bandwidthScrollBar, signalToNoiseScrollBar;
	/**
	 * Constructor which has an object from the ShannonsController class as an argument.
	 * @param sc ShannonsController object
	 */
	public ShannonsJScrollBarPanel(ShannonsController sc) 
	{
		super();
		this.controller = sc;
		createPanel();
	}
	/**
	 * Creates a panel containing all the JScrollBar objects and stores it in the JPanel.
	 */
	public void createPanel() 
	{
		final int MIN_VALUE = 0;
		final int BANDWIDTH_MAX_VALUE = 1010;
		final int SIGNALTONOISE_MAX_VALUE = 3010;
		final int MDR_MAX_VALUE = 1000010;
		
		bandwidthScrollBar = new JScrollBar(JScrollBar.HORIZONTAL);
		signalToNoiseScrollBar = new JScrollBar(JScrollBar.HORIZONTAL);
		maximumDataRateScrollBar = new JScrollBar(JScrollBar.HORIZONTAL);
		
		setLayout(new GridLayout(3,2, 10, 20));
		
		bandwidthScrollBar.setMinimum(MIN_VALUE);
		bandwidthScrollBar.setMaximum(BANDWIDTH_MAX_VALUE);
		bandwidthScrollBar.addAdjustmentListener(l -> { controller.setBandwidth((double)bandwidthScrollBar.getValue()); });
		
		signalToNoiseScrollBar.setMinimum(MIN_VALUE);
		signalToNoiseScrollBar.setMaximum(SIGNALTONOISE_MAX_VALUE);
		signalToNoiseScrollBar.addAdjustmentListener(l -> { controller.setSignalToNoise((double)signalToNoiseScrollBar.getValue()); });
		
		maximumDataRateScrollBar.setMinimum(MIN_VALUE);
		maximumDataRateScrollBar.setMaximum(MDR_MAX_VALUE);
		maximumDataRateScrollBar.setAutoscrolls(true);
		
		add(new JLabel("Bandwidth(Hz): "));
		add(bandwidthScrollBar);
		
		add(new JLabel("Signal to noise(db): "));
		add(signalToNoiseScrollBar);
		
		add(new JLabel("Maximum Data Rate(bps): "));
		add(maximumDataRateScrollBar);
		
		setBorder(new LineBorder(Color.ORANGE, 5));
		setBackground(Color.RED);
		setVisible(true);	
	}
	/**
	 * Updates the values of bandwidth, signal to noise and maximum data rate according to the scroll bar.
	 * @param bandwidth double
	 * @param signalToNoise double
	 * @param MDR double
	 */
	public void updateAll(double bandwidth, double signalToNoise, double MDR) 
	{
			bandwidthScrollBar.setValue((int) bandwidth);
			signalToNoiseScrollBar.setValue((int) signalToNoise);
			maximumDataRateScrollBar.setValue((int) MDR);
	}

}
