import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MiddlePanel extends JPanel{
	protected ReceptionPanel receptionPanel;
	protected ZonePanel zonePanel;
	private ExitPanel exitPanel;
	public MiddlePanel(int type, Zone zoneType){
		if (type == 0){
			//add reception
			receptionPanel = new ReceptionPanel();
			add(receptionPanel);
		}
		else if(type == 1){
			//user requested a zone, deliver
			zonePanel = new ZonePanel(zoneType);
			add(zonePanel);
		}
		else if(type == 2){
			exitPanel = new ExitPanel();
			add(exitPanel);
		}
		
	}
}
	

