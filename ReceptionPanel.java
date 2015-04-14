import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ReceptionPanel extends JPanel{
	//global variables of this class with required components
	private JLabel welcomeText;
	private JLabel selectCustomer;
	private static JComboBox customerListBox;
	private JLabel newCustLabel, newCustBalLabel;
	private JTextField nameTextField, balTextField;
	private JButton registerPlayer;
	private RegisterPlayerListener regPlayer;
	private JButton loginButton;
	private LoginPlayerListener logPlayer;

	public ReceptionPanel(){
// initialise all data required for the welcome panel such as registering and logging in
		welcomeText = new JLabel("Welcome");

		selectCustomer = new JLabel("Select Customer: ");

		customerListBox = new JComboBox();

		newCustLabel = new JLabel("New Customer Name: ");
		newCustBalLabel = new JLabel("New Customer Balance: ");
	
		nameTextField = new JTextField(5);

		balTextField = new JTextField(5);

		registerPlayer = new JButton("Register");
		regPlayer = new RegisterPlayerListener();
		registerPlayer.addActionListener(regPlayer);

		loginButton = new JButton("Login");
		logPlayer = new LoginPlayerListener();
		loginButton.addActionListener(logPlayer);	
		//group layout is used to align columns together etc
		 this.setBorder(BorderFactory.createTitledBorder("Reception"));
	        GroupLayout layout = new GroupLayout(this);          
	        this.setLayout(layout);
	        layout.setAutoCreateGaps(true);
	        layout.setAutoCreateContainerGaps(true);
	        layout.setHorizontalGroup(layout.createSequentialGroup()
	            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
	                .addComponent(welcomeText)
	                .addComponent(selectCustomer)
	                .addComponent(newCustLabel)
	                .addComponent(newCustBalLabel)
	                .addComponent(registerPlayer))
	            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                .addComponent(customerListBox)
	                .addComponent(nameTextField)
	                .addComponent(balTextField) 
	                .addComponent(loginButton))
	        );
	        layout.setVerticalGroup(layout.createSequentialGroup()
	            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                .addComponent(welcomeText))
	            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                .addComponent(selectCustomer)
	                .addComponent(customerListBox))
	            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	            	.addComponent(loginButton))
	            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                .addComponent(newCustLabel)
	                .addComponent(nameTextField))
	            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                .addComponent(newCustBalLabel)
	                .addComponent(balTextField))
	            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	               .addComponent(registerPlayer))
	        );
	        //update the customers list incase a customer was added prior to this being called
		updateCustomerCombo();
		setPreferredSize(new Dimension(400,300));
	}
	private static void updateCustomerCombo(){
		//updates all customers registered with an iterator pattern
		customerListBox.removeAllItems();
		ArrayList<Player> users = new ArrayList<Player>(Controller.getPlayers());
		Iterator<Player> userIt = users.iterator();
		//HERE IS ANOTHER ITERATOR
		int x=0;
		while(userIt.hasNext()){
			customerListBox.addItem(users.get(x).getName());
			userIt.next();
			x++;
		}
		//if no customers on the combo box, simply display no players registered
		if (customerListBox.getItemCount() == 0){
			customerListBox.addItem("No Players!");
		}
	}
	private class RegisterPlayerListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			//when register is clicked, gets here, adds name to variable
			String name = new String(nameTextField.getText());
			try {
				//parse double requested balance, and attempts to create a player in the controller
				double balance = Double.parseDouble(balTextField.getText());
				//decides whether the user wanted to be notified for new products being added
				boolean didRegister = false;
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog (null, "Would you like to be notified when a new product is added?","Registering for Notifications?",dialogButton);
				if(dialogResult == JOptionPane.YES_OPTION){
					didRegister = true;
				}
				boolean didCreate = Controller.createPlayer(name, balance,0,didRegister);
				//returns a boolean, true if person created and displays info regarding that player
				if (didCreate){
					updateCustomerCombo();
					nameTextField.setText("");
					balTextField.setText("");
					JOptionPane.showMessageDialog(null,"Welcome "+ name + "! Please select your name from the drop down and log in!");
				}
				else{
					JOptionPane.showMessageDialog(null,"Maximum players reached.");
				}
			} catch (NumberFormatException nfe) {  
				//simple catch is number was not entered as balance
				JOptionPane.showMessageDialog(null,"Input must be a number.");
			}
		}
	
	}
	private class LoginPlayerListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			//set the current player with the index of the selected player that was clicked on the welcome panel
			Controller.login(customerListBox.getSelectedIndex());
			if(Controller.getPlayer() == null){
				JOptionPane.showMessageDialog(null,"No player selected, please register a player.");
			}
		}
			
	}
}
