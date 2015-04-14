import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.*;

@SuppressWarnings("serial")
public class BottomPanel extends JPanel {
	// need static labels for editable text to be inputed in
	private JLabel customerNamePlaceholder;
	private static JLabel customerNameString;
	private JLabel customerBalancePlaceholder;
	private static JLabel customerBalanceString;
	private JLabel customerExperiencePlaceholder;
	private static JLabel customerExperienceString;
	private JLabel customerLocationPlaceholder;
	private static JLabel customerLocationString;
	private JButton exitFantasyLand;
	private JButton getCartButton;
	private GetCartListener getCartListener;
	private JButton addBalanceButton;
	private AddBalance addBalListener;
	private ExitLandListener exitLandListener;
	
	public BottomPanel(){
		//initialise all the data thats going to need to be displayed on the bottom and add
		customerNamePlaceholder = new JLabel("Name: ");
		customerNameString = new JLabel("No user");
		customerBalancePlaceholder = new JLabel ("| Balance: ");
		customerBalanceString = new JLabel ("0.00");
		customerExperiencePlaceholder = new JLabel("| Experience: ");
		customerExperienceString = new JLabel("0");
		customerLocationPlaceholder = new JLabel("| Location: ");
		customerLocationString = new JLabel("0");
		
		exitFantasyLand = new JButton("Exit Fantasy Land");
		exitLandListener = new ExitLandListener();
		exitFantasyLand.addActionListener(exitLandListener);
		
		getCartButton = new JButton("Get cart!");
		getCartListener = new GetCartListener();
		getCartButton.addActionListener(getCartListener);
		
		addBalanceButton = new JButton("Add Balance");
		addBalListener = new AddBalance();
		addBalanceButton.addActionListener(addBalListener);
		
		add(customerNamePlaceholder);
		add(customerNameString);
		add(customerBalancePlaceholder);
		add(customerBalanceString);
		add(customerExperiencePlaceholder);
		add(customerExperienceString);
		add(customerLocationPlaceholder);
		add(customerLocationString);
		add(exitFantasyLand);
		add(getCartButton);
		add(addBalanceButton);
		if(Controller.getPlayer()!=null){
			//if a player is present on load, or even on returning to reception, refresh the bottom panel
			updateBottomPanel();
		}
	}
	private class GetCartListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			//if they click the get cart button it checks if they have one, or if they are logged in and if
			// all checks are met, it initialises the current player with a cart to make purchases.
			if ((Controller.getPlayer() != null) && !(Controller.getPlayer().hasCart)){
				Controller.getPlayer().hasCart = true;
				Controller.getPlayer().getCart();
				JOptionPane.showMessageDialog(null,"Cart Added, you are now able to make purchases.");
			}
			else if ((Controller.getPlayer() != null)&&(Controller.getPlayer().hasCart)){
				JOptionPane.showMessageDialog(null,"You already have a cart, go make some purchases.");
			}
			else{
				JOptionPane.showMessageDialog(null,"No customer, please go to reception to log in.");
			}
		}
		
	}
	public static void updateBottomPanel() {
		//if a player is logged in, get information about the player and display it (with formatting)
		if(Controller.getPlayer()!=null){
			Player tempPlayer = Controller.getPlayer();
			customerNameString.setText(tempPlayer.getName());
			String balString = new DecimalFormat("£00.00").format(tempPlayer.getBalance());
			customerBalanceString.setText(balString);
			customerExperienceString.setText(String.valueOf(tempPlayer.getExperience()));
			customerLocationString.setText(String.valueOf(tempPlayer.getLocation()));
		}
		else{
			//otherwise, make it evident no user is logged in
			customerNameString.setText("No User");
			customerBalanceString.setText("0.00");
			customerExperienceString.setText("0");
			customerLocationString.setText("0");
		}
	}
	private class AddBalance implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(Controller.getPlayer()!=null){
				@SuppressWarnings("unused")
				AddBalanceDialog dlg = new AddBalanceDialog(new JFrame(), "Add Balance", "Please enter amount: ");
			}
			else{
				JOptionPane.showMessageDialog(null,"Please log in prior to adding balance.");

			}
		}
		
	}
	private class ExitLandListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(Controller.getPlayer() != null){
				Controller.updateUserLocation(99);
				Controller.changeZone(99);
			}
			else{
				JOptionPane.showMessageDialog(null,"Please enter fantasy land before navigating to exit.");

			}
			
		}
		
	}
}
