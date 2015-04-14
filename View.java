import java.awt.*;
import javax.swing.*;
@SuppressWarnings("serial")
public class View extends JFrame {
	// 3 panels are declared here, the top and bottom are always persistant, the middle changes depending on requirements
	private static TopPanel topPanel;
	private static BottomPanel bottomPanel;
	private static MiddlePanel middlePanel;
	
	public View(int middleType, Zone zoneType, int zoneIndex){
		//declare all the panels and add them, middle panel needs to be set by the constructor where the index (required zone) is sent over
		topPanel = new TopPanel(zoneIndex);
		bottomPanel = new BottomPanel();
		middlePanel = new MiddlePanel(middleType, zoneType); //0 = reception panel, 1 = zone panel, 2 = exit panel
		this.setMinimumSize(new Dimension(785, 500));
		this.add(topPanel, BorderLayout.NORTH);
		this.add(middlePanel);
		this.add(bottomPanel, BorderLayout.SOUTH);
		setResizable(false);
		this.pack();
		setIconImage(new ImageIcon("ico.jpg").getImage());
	}
}
