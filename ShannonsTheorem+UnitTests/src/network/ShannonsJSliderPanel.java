package network;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.LineBorder;
/**
 * ShannonsJSliderPanel.java: This class extends JPanel and uses JSlider as a GUI for bandwidth, signal to noise and maximum data rate.
 * It also has a ShannonsController object to control the content of this class which is used in the constructor.
 * @author Chrystian Rafael Sanches dos Santos
 * @version 1.0.0 Date November 27 2017
 */
public class ShannonsJSliderPanel extends JPanel 
{
	private static final long serialVersionUID = 1L;
	private ShannonsController controller;
	private JSlider maximumDataRateSlider, bandwidthSlider, signalToNoiseSlider;
	
	/**
	 * Constructor which has an object from the ShannonsController class as an argument.
	 * @param sc ShannonsController object
	 */
	public ShannonsJSliderPanel(ShannonsController sc) 
	{
		super();
		this.controller = sc;
		createPanel();
	}
	/**
	 * Creates a panel containing all the JSlider objects and stores it in the JPanel.
	 */
	public void createPanel() 
	{
		final int MIN_VALUE = 0;
		final int BANDWIDTH_MAX_VALUE = 1000;
		final int SIGNALTONOISE_MAX_VALUE = 3000;
		final int MDR_MAX_VALUE = 1000000;
		
		bandwidthSlider = new JSlider(JSlider.HORIZONTAL, MIN_VALUE, BANDWIDTH_MAX_VALUE, MIN_VALUE);
		signalToNoiseSlider = new JSlider(JSlider.HORIZONTAL, MIN_VALUE, SIGNALTONOISE_MAX_VALUE, MIN_VALUE);
		maximumDataRateSlider = new JSlider(JSlider.HORIZONTAL, MIN_VALUE, MDR_MAX_VALUE, MIN_VALUE);
		
		setLayout(new GridLayout(3,2, 10, 20));
		
		bandwidthSlider.setMajorTickSpacing(500);
		bandwidthSlider.setMinorTickSpacing(250);
		bandwidthSlider.setPaintTicks(true);
		bandwidthSlider.setPaintLabels(true);
		
		bandwidthSlider.addChangeListener(l -> { controller.setBandwidth((double)bandwidthSlider.getValue()); });
		
		signalToNoiseSlider.setMajorTickSpacing(1000);
		signalToNoiseSlider.setMinorTickSpacing(500);
		signalToNoiseSlider.setPaintTicks(true);
		signalToNoiseSlider.setPaintLabels(true);
		
		signalToNoiseSlider.addChangeListener(l -> { controller.setSignalToNoise((double)signalToNoiseSlider.getValue()); });
		
		maximumDataRateSlider.setMajorTickSpacing(500000);
		maximumDataRateSlider.setMinorTickSpacing(25000);	
		maximumDataRateSlider.setPaintTicks(true);
		maximumDataRateSlider.setPaintLabels(true);
		maximumDataRateSlider.setEnabled(false);
		
		add(new JLabel("Bandwidth(Hz): "));
		add(bandwidthSlider);
		
		add(new JLabel("Signal to noise(db): "));
		add(signalToNoiseSlider);
		
		add(new JLabel("Maximum Data Rate(bps): "));
		add(maximumDataRateSlider);
		
		setBorder(new LineBorder(Color.GRAY, 5));
		setBackground(Color.WHITE);
		setVisible(true);
		
	}
	/**
	 * Updates the values of bandwidth, signal to noise and maximum data rate according to the slider.
	 * @param bandwidth double
	 * @param signalToNoise double
	 * @param MDR double
	 */
	public void updateAll(double bandwidth, double signalToNoise, double MDR) 
	{
			bandwidthSlider.setValue((int) bandwidth);
			signalToNoiseSlider.setValue((int) signalToNoise);
			maximumDataRateSlider.setValue((int) MDR);
	}
}
