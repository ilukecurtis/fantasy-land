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
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

@SuppressWarnings("serial")
public class ZonePanel extends JPanel {
	//global variables for the zone panel to use
	private JLabel storeStringName;
	private JList listOfProducts;
	private JTextArea productDescText;
	private JButton addProductButton, viewBasketButton;
	private AddToBasket addBasketListener;
	private ViewBasket viewBasketListener;
	private ArrayList<Product> products;
	private ChangeProductListener changeProd;
	private Product tempProd;
	
	public ZonePanel(Zone zoneType){
		//declare relevant information to display in the zone panel and iterate through list of products
		storeStringName = new JLabel("Store name: " + zoneType.getStoreName());
		Store tempStore = zoneType.getStores();
		products = new ArrayList<Product>(tempStore.getProducts());
		Iterator<Product> productIt = products.iterator();
		String [] listData = new String[products.size()];
		//ITERATOR PATTERN HERE AGAIN
		int x=0;
		while(productIt.hasNext()){
			listData[x] = products.get(x).getName();
			productIt.next();
			x++;
		}
		//declare all listeners and appropriate text handlers
		listOfProducts = new JList(listData);
		changeProd = new ChangeProductListener();
		listOfProducts.addListSelectionListener(changeProd);
		
		productDescText = new JTextArea(1,2);
	    productDescText.setText("Please select an item to view details!\n Name: \n Description: \n Price: \n Stock: \n");
	    productDescText.setEditable(false);
	    productDescText.setBackground(Color.LIGHT_GRAY);
	    
		addProductButton = new JButton("Add to Basket!");
		addBasketListener = new AddToBasket();
		addProductButton.addActionListener(addBasketListener);
		
		viewBasketListener = new ViewBasket();
		viewBasketButton = new JButton("View Basket");
		viewBasketButton.addActionListener(viewBasketListener);
		//group layout used to put it all in columns and rows
		this.setBorder(BorderFactory.createTitledBorder(zoneType.getName()));
        GroupLayout layout = new GroupLayout(this);          
        this.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
            		.addComponent(storeStringName)
            		.addComponent(listOfProducts)
            		.addComponent(addProductButton))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            		.addComponent(productDescText)
            		.addComponent(viewBasketButton))
            );
        layout.setVerticalGroup(layout.createSequentialGroup()
	            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	            		.addComponent(storeStringName))
	            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	            		.addComponent(listOfProducts)
	            		.addComponent(productDescText))
	            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	            		.addComponent(addProductButton)
	            		.addComponent(viewBasketButton))
	         );
        
		this.setPreferredSize(new Dimension(700,300));
	}
	private class ChangeProductListener implements ListSelectionListener{
		
		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			//when product is changed, the relevant elements are shown in a text box next to it
			int indexSelected = listOfProducts.getSelectedIndex();
			tempProd = products.get(indexSelected);
			String text;
			if (tempProd.getClass().getSimpleName() == "ActivityProduct"){
				text = "Product selected, please add to basket!\n Name: "+ tempProd.getName()+ " \n Description: "+ tempProd.getDesc() + "\n Price: " + tempProd.getPrice();
			}
			else{
				text = "Product selected, please add to basket!\n Name: "+ tempProd.getName()+ " \n Description: "+ tempProd.getDesc() + "\n Price: " + tempProd.getPrice() + " \n Stock:" + tempProd.getStock();
			}
		    productDescText.setText(text);
		}
	}
	private class AddToBasket implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (tempProd != null){
				if ((tempProd.getStock()!=0)||!(tempProd.hasStock())){
					if(Controller.playerLogged()){
						if(Controller.getPlayer().checkCart()){
							//simple error checks here for player and product being correctly stored and add to basket
							Controller.getPlayer().passCart().addItem(tempProd);
							JOptionPane.showMessageDialog(null, "Item added, view basket to checkout or continue shopping");
						}
						else{
							JOptionPane.showMessageDialog(null, "Please get a cart first!");
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Please log in prior to purchasing");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Not in stock");

				}
			}
			else {
				JOptionPane.showMessageDialog(null,"No product selected/Not in stock!");
			}
		}
	}
	private class ViewBasket implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if((Controller.getPlayer() != null)&&(Controller.getPlayer().hasCart)){
				BasketPanel basketPanel = new BasketPanel();
				JOptionPane.showMessageDialog(null,basketPanel,"JOPTIONPANE",JOptionPane.INFORMATION_MESSAGE);
			}
			else{
				JOptionPane.showMessageDialog(null, "No user logged in/need to get cart");
			}
		}
		
	}
}
