import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;

@SuppressWarnings("serial")
public class TopPanel extends JPanel {
	//global variables for the top panel to use
	private JLabel header;
	private JButton logoutButton;
	private JLabel zoneString;
	private JLabel zoneName;
	private LogoutHandler logHandle;
	private JComboBox changeZoneBox;
	private ChangeZoneHandler changeZoneHandler;
	
	public TopPanel(int zoneIndex) {
		// initialisation of code with appropiate handlers and iterators to get required info such as zone list
		header = new JLabel("Fantasy Land!      | ");
		
		logoutButton = new JButton("Logout");
		logHandle = new LogoutHandler();
		logoutButton.addActionListener(logHandle);
		
		zoneString = new JLabel("Zone: ");
		
		ArrayList<Zone> zoneList = new ArrayList<Zone>(Controller.getZone());
		zoneName =  new JLabel(zoneList.get(0).getName());
		Iterator<Zone> zoneIt = zoneList.iterator();
		changeZoneBox = new JComboBox();
		int x = 0;
		//ITERATOR IS HERE!!!!!!!!
		while (zoneIt.hasNext()){
			changeZoneBox.addItem(zoneList.get(x).getName());
			x++;
			zoneIt.next();
		}
		
		changeZoneBox.setSelectedIndex(zoneIndex);
		changeZoneHandler = new ChangeZoneHandler();
		changeZoneBox.addActionListener(changeZoneHandler);
		add(header);
		add(zoneString);
		add(zoneName);
		add(logoutButton);
		add(changeZoneBox);
	}
	private class  LogoutHandler implements ActionListener 
    {
       public void actionPerformed( ActionEvent e )
       {
    	   //set the player to log out
    	   if(Controller.playerLogged()){
    		   Controller.logout(changeZoneBox.getSelectedIndex());
    	   }
       } 
    } 
	private class ChangeZoneHandler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			//change the zone, pass over the index and the controller will call the appropriate functions depending on index
			Controller.changeZone(changeZoneBox.getSelectedIndex());
		}
		
	}
}
