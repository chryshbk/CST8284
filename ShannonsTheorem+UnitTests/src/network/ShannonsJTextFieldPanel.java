package network;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
/**
 * ShannonsJTextFieldPanel.java: This class extends JPanel and uses JTextField as a GUI for bandwidth, signal to noise and maximum data rate.
 * It also has a ShannonsController object to control the content of this class which is used in the constructor.
 * @author Chrystian Rafael Sanches dos Santos
 * @version 1.0.0 Date November 27 2017
 */
public class ShannonsJTextFieldPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private JTextField MDRTextField, bandwidthTextField, signalToNoiseTextField;
	private ShannonsController controller;
	
	/**
	 * Constructor which has an object from the ShannonsController class as an argument.
	 * @param sc ShannonsController object
	 */
	public ShannonsJTextFieldPanel(ShannonsController sc) 
	{
		super();
		this.controller = sc;
		createPanel();
	}
	/**
	 * Creates a panel containing all the JTextField objects and stores it in the JPanel.
	 */
	public void createPanel() 
	{
		bandwidthTextField = new JTextField();
		signalToNoiseTextField = new JTextField();
		MDRTextField = new JTextField();
		
		//Default values
		bandwidthTextField.setText("0.0");
		signalToNoiseTextField.setText("0.0");
		MDRTextField.setText("0.0");
		
		setBorder(new LineBorder(Color.CYAN));
		
		setLayout(new GridLayout(3,2, 10, 20));
		setBackground(Color.GRAY);
		
		add(new JLabel("Bandwidth(Hz): "));
		add(bandwidthTextField);
		
		add(new JLabel("Signal to Noise(db): "));
		add(signalToNoiseTextField);

		add(new JLabel("Maximum Data Rate(bps): "));
		add(MDRTextField);
		
		bandwidthTextField.addActionListener(al -> 
		{
			try 
			{
				if (Double.parseDouble(bandwidthTextField.getText()) < 0)
					JOptionPane.showMessageDialog(null, "Number has to be positive");
				else
				controller.setBandwidth(Double.parseDouble(bandwidthTextField.getText())); 
			} catch (NumberFormatException nfe) { JOptionPane.showMessageDialog(null, "Only numbers are allowed"); } 
			  catch (NullPointerException npe) { JOptionPane.showMessageDialog(null, "Number has to be positive"); }
		});

		signalToNoiseTextField.addActionListener(al -> 
		{
			try 
			{
				if (Double.parseDouble(signalToNoiseTextField.getText()) < 0)
					JOptionPane.showMessageDialog(null, "Number has to be positive");
				else
				controller.setSignalToNoise(Double.parseDouble(signalToNoiseTextField.getText())); 
			} catch (NumberFormatException nfe) { JOptionPane.showMessageDialog(null, "Only numbers are allowed"); } 
			  catch (NullPointerException npe) { JOptionPane.showMessageDialog(null, "Number has to be positive"); }
		});
		
		MDRTextField.setEditable(false);
		setBorder(new LineBorder(Color.DARK_GRAY, 5));
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
			bandwidthTextField.setText(String.valueOf(bandwidth));
			signalToNoiseTextField.setText(String.valueOf(signalToNoise));
			MDRTextField.setText(String.valueOf(MDR));
	}
}
