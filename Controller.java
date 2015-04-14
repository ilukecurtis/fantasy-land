import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Controller {
	private static ArrayList<Player> usersList;
	private static Player currentPlayer;
	private static int numPlayers = 0;
	private static Model newWorld;
	private static View newView;
	private static final int MAX_PLAYERS = 3;
	public static void main (String[] args){
		//create the world, and initialize it, also initialize user list
		newWorld = new Model();
		newWorld.init();
		usersList = new ArrayList<Player>();
		//show the GUI
		setupAndShowGUI(0,null,0);
	}
	public static void setupAndShowGUI(int middleTypeSelector, Zone zoneRequired, int comboZoneIndex){
		//creation of the GUI decides what type of middle zone (whether it be zone, reception or exit)
		//and if it is a zone, pass over that object (null if reception or exit)
		//and the index of the zone wanted, to change the combo box
		newView = new View(middleTypeSelector,zoneRequired,comboZoneIndex);
		newView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		newView.setVisible(true);
	}
	public static boolean createPlayer(String name, double bal, int loc, boolean didRegister){
		if (numPlayers < MAX_PLAYERS){
			//factory object to create a player. with required information
			PersonFactory playerFactory = PersonFactory.getInstance();
			Person newUser = playerFactory.getPerson("player", name, bal, loc, didRegister);
			//cast to player, as in force it to be a player for the list, after factory creation
			usersList.add((Player) newUser);
			//add to the players and make the registered player the current player
			currentPlayer = usersList.get(numPlayers);	
			numPlayers+=1;
			return true;
		}
		else{
			return false;
		}
	}
	public static void updateUserLocation(int loc){
		currentPlayer.updateLocation(loc);
	}
	public static void login(int customerIndex){
		// change zone location here
		//Zone savedZone = newWorld.zoneList.get(currentPlayer.getLocation());
		if(usersList.size()>0){
			currentPlayer = usersList.get(customerIndex);
			BottomPanel.updateBottomPanel();
			changeZone(currentPlayer.getLocation());
		}
		
	}
	public static void logout(int zoneIndexBox){
		//pass index of zone selected, and cross reference to zoneList in world which is parallel to the combo box
		updateUserLocation(zoneIndexBox);
		currentPlayer = null;
		//index 0 is reception
		changeZone(0);
		BottomPanel.updateBottomPanel();
	}
	public static void changeZone(int zoneIndex){
		//the index of which zone selected is in the combo box, get that element from the zone list array list
		if (currentPlayer!=null){
			//update location first if they are logged in
			currentPlayer.updateLocation(zoneIndex);
		}
		newView.dispose(); 
		if (zoneIndex == 0 ){
			//recall the GUI, if it's 0, reception was selected and need to display that
			setupAndShowGUI(0,null,0);
		}
		else if (zoneIndex == 99){
			setupAndShowGUI(2,null,0);
		}
		else{
			Zone zoneType = (newWorld.zoneList.get(zoneIndex));
			//if not, a zone was selected, show the GUI for the corresponding zone
			setupAndShowGUI(1,zoneType, zoneIndex);
		}
	}
	public static ArrayList<Player> getPlayers(){
		//get method for players
		return usersList;
	}
	public static ArrayList<Zone> getZone(){
		//get the zones for the GUI to implement
		return newWorld.zoneList;
	}
	public static boolean playerLogged(){
		//returns a true value if a player is logged in
		if(currentPlayer!=null){
			return true;
		}
		else{
			return false;
		}
	}
	public static Player getPlayer(){
		//returns the player logged in if required
		return currentPlayer;
	}
	public static void newProdNotifier(Product newProd){
		//option dialog here
		JOptionPane.showMessageDialog(null, "New product added! Name: " + newProd.getName() + " \t Price: " + newProd.getPrice());
	}
	public static void levelPlayerUp(){
		ActivityProduct prodToAdd = new ActivityProduct("Chop onions", false, 5, 10.99, "Chop onions till you cry");
		newWorld.getStore().get(0).addNewProduct(prodToAdd);
	}
}