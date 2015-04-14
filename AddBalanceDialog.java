import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class AddBalanceDialog extends JDialog implements ActionListener {
	private JTextField addBalBox = new JTextField(5);
	  public AddBalanceDialog(JFrame parent, String title, String message) {
	    super(parent, title, true);
	    //make a new JDialog with required information that is set out by the following elements
	   //ensure the message that is passed over is displayed.
	    JPanel messagePane = new JPanel();
	    messagePane.add(new JLabel(message));
	    messagePane.add(addBalBox);
	    getContentPane().add(messagePane);
	    
	    JPanel buttonPane = new JPanel();
	    JButton button = new JButton("OK"); 
	    buttonPane.add(button);    
	    button.addActionListener(this);
	    //add action listener to be called and add to the current player.
	    getContentPane().add(buttonPane, BorderLayout.SOUTH);
	    
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
	    pack(); 
	    setVisible(true);
	  }
	  public void actionPerformed(ActionEvent e) {
		 try{
			 //check the value entered is a number, and add to balance (number is rounded)
			double balance = Double.parseDouble(addBalBox.getText()); 
			Controller.getPlayer().addBalance(balance);
			//remove the panel if all goes well and update the rest of the GUI
			setVisible(false); 
	    	dispose();
	    	BottomPanel.updateBottomPanel();
		}
		catch(NumberFormatException nfe){
			//show error msg
			JOptionPane.showMessageDialog(null,"Amount entered incorrect, make sure it is a number.");
		}
	  }
}
