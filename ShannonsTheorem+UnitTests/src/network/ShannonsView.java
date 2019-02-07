package network;

import java.util.Observable;
import java.util.Observer;

//import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * ShannonsView.java: Class that extends JFrame and implements Observer. It uses an object from each of this classes that extends JPanel such as
 *  ShannonsJTextFieldPanel.java, ShannonsJSliderPanel.java and ShannonsJScrollBarPanel.java in order to create an interface using all these panels. 
 *  It also uses an object from the ShannonsController.java class. This class is the main GUI.
 * 
 * @author Chrystian Rafael Sanches dos Santos
 * @version 1.0.2 Date November 27, 2017
 */
public class ShannonsView extends JFrame implements Observer 
{
	private static final long serialVersionUID = 1L;
	private ShannonsController controller;
	private ShannonsJTextFieldPanel textFieldPanel;
	private ShannonsJSliderPanel sliderPanel;
	private ShannonsJScrollBarPanel scrollBarPanel;
	/**
	 * Default constructor.
	 */
	public ShannonsView() { super(); }
	/**
	 * Run method in order to execute the GUI.
	 */
	public void run() 
	{
		add(createContentPane());
		pack();
		setVisible(true);
	}
	/**
	 * This private method contains all the panels from the classes instantiated by ShannonsView and creates a JPanel to store all the content.
	 * @return contentPane JPanel containing all the content.
	 */
	private JPanel createContentPane() 
	{ 	
		textFieldPanel = new ShannonsJTextFieldPanel(controller);
		sliderPanel = new ShannonsJSliderPanel(controller);
		scrollBarPanel = new ShannonsJScrollBarPanel(controller);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel contentPane = new JPanel();
		
		contentPane.add(textFieldPanel);	
		contentPane.add(sliderPanel);
		contentPane.add(scrollBarPanel);
		
		return contentPane;
	}
	/**
	 * Sets the ShannonsController object.
	 * @param shannonsController ShannonsController object
	 */
	public void setController(ShannonsController shannonsController) { this.controller = shannonsController; }
	/**
	 * update method from the Observer interface which updates all the values on the main JPanel.
	 */
	@Override
	public void update(Observable observable, Object obj) 
	{	
		ShannonsModel model = (ShannonsModel) observable;
		textFieldPanel.updateAll(model.getBandwidth(), model.getSignalToNoise(), model.getMaximumDataRate());
		sliderPanel.updateAll(model.getBandwidth(), model.getSignalToNoise(), model.getMaximumDataRate());
		scrollBarPanel.updateAll(model.getBandwidth(), model.getSignalToNoise(), model.getMaximumDataRate());
	}
}