import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


@SuppressWarnings("serial")
public class ExitPanel extends JPanel {
	private JLabel exitMessage;
	private JButton returnToWelcomeButton;
	private JButton viewSummary;
	private SummaryActionListener summaryActionListener;
	private JTextArea summaryListOfPurchases;
	private ReturnWelcomeListener returnWelcomeListener;
	
	public ExitPanel(){
		init();
	}
	private void init(){
		//all jlabels, buttons and jtextareas to show the information about the visit
		exitMessage = new JLabel("Thank you for visiting Fantasy Land! ");
		returnWelcomeListener = new ReturnWelcomeListener();
		returnToWelcomeButton = new JButton("Logout and return to Welcome screen");
		returnToWelcomeButton.addActionListener(returnWelcomeListener);
		
		viewSummary = new JButton("Show/Hide Summary");
		//action listener for the summary and return to welcome
		summaryActionListener = new SummaryActionListener();
		viewSummary.addActionListener(summaryActionListener);
		this.setPreferredSize(new Dimension(500,300));
		
		//declaring jtextarea, giving the required information to the user
		popSummary();
		summaryListOfPurchases.setVisible(false);
		//group layout for elements, in 2 columns, 3 rows
		this.setBorder(BorderFactory.createTitledBorder("Panel "));
        GroupLayout layout = new GroupLayout(this);          
        this.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                .addComponent(exitMessage)
                .addComponent(returnToWelcomeButton)
                .addComponent(summaryListOfPurchases))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(viewSummary))
            );
        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(exitMessage))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(returnToWelcomeButton)
                .addComponent(viewSummary))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(summaryListOfPurchases))
        );
	}
	private void popSummary(){
		//standard function to populate the text area with the data relating to 
		summaryListOfPurchases = new JTextArea(1,2);
		summaryListOfPurchases.setText("Summary of visit for: " + Controller.getPlayer().getName());
		summaryListOfPurchases.setEditable(false);
		summaryListOfPurchases.setBackground(Color.LIGHT_GRAY);
		
		//iterate through and populate the summary list when initialized 
		ArrayList<Product> purchased = new ArrayList<Product>(Controller.getPlayer().getPurchasedProducts());
		Iterator<Product> productIt = purchased.iterator();
		String [] listData = new String[purchased.size()];
		double [] listData1 = new double[purchased.size()];
		int x=0;
		while(productIt.hasNext()){
			listData[x] = purchased.get(x).getName();			
			listData1[x] = purchased.get(x).getPrice();
			summaryListOfPurchases.append("\nName:\t" + listData[x] + "\tPrice: " + listData1[x]);
			productIt.next();
			x++;
		}
	}
	private class SummaryActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			summaryListOfPurchases.setVisible(true);

		}
		
	}
	private class ReturnWelcomeListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Controller.logout(99);
		}
		
	}
	
}
