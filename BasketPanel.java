import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BasketPanel extends JPanel {
	private JList listOfBasketItems;
	private JButton removeItemButton, checkout;
	private ArrayList<Product> basketItemsList;
	private RemoveItemListener remItem;
	private DefaultListModel listModel;
	private CheckoutListener checkoutListener;
	public BasketPanel(){
		//init method is called for encapsulation
		init();
	}
	private void init()
	{
		//initialised the required components, a list model is used to control the jlist
		removeItemButton = new JButton("Remove Item");
		checkout = new JButton("Checkout");
		basketItemsList = new ArrayList<Product>(Controller.getPlayer().passCart().getCartItems());
		listModel = new DefaultListModel();
		//refresh the products list on load so that it reads from the users cart
		refreshProductsList();
		//add the elements to the panel
		listOfBasketItems = new JList(listModel);
		add(listOfBasketItems);
		add(removeItemButton);
		remItem = new RemoveItemListener();
		removeItemButton.addActionListener(remItem);
		add(checkout);
		checkoutListener = new CheckoutListener();
		checkout.addActionListener(checkoutListener);
	}
	private void refreshProductsList (){
		//remove all items relating to cart, so we can repopulate and add again to jlist
		basketItemsList.clear();
		basketItemsList.addAll(Controller.getPlayer().passCart().getCartItems());
		listModel.clear();
		//get iterator variables to add to the list model for more jlist items in basket
		Iterator<Product> productIt = basketItemsList.iterator();
		String [] listData = new String[basketItemsList.size()];
		//ITERATOR PATTERN HERE AGAIN
		int x=0;
		while(productIt.hasNext()){
			listData[x] = basketItemsList.get(x).getName();
			listModel.addElement(listData[x]);
			productIt.next();
			x++;
		}
	}
	private class RemoveItemListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if((basketItemsList.size() > 0) && !(listOfBasketItems.isSelectionEmpty())){
				// get the selected index so we know which item to remove from the cart and refresh, repaint
				int selectedProdIndex = listOfBasketItems.getSelectedIndex();
				ArrayList<Product> playerCartItems = Controller.getPlayer().passCart().getCartItems();
				playerCartItems.remove(selectedProdIndex);
				refreshProductsList();
				repaint();
			}
			else{
				JOptionPane.showMessageDialog(null,"Nothing selected, please retry.");
			}
		}
		
	}
	private class CheckoutListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			if (basketItemsList.size() > 0){
				double cost=0;
				Iterator<Product> productIt = basketItemsList.iterator();
				// iterator used to get the price of items and then buy
				int x=0;
				while(productIt.hasNext()){
				 	cost += basketItemsList.get(x).getPrice();
					productIt.next();
					x++;
				}
				//empty the cart once the purchase has completed
				if(Controller.getPlayer().buyProducts(cost, basketItemsList)){
					Controller.getPlayer().passCart().emptyCart();
					refreshProductsList();
					//update the balance and stuff
					BottomPanel.updateBottomPanel();
					JOptionPane.showMessageDialog(null,"Products purchased! Please navigate to the exit, or alternatively, continue shopping.");
					BasketPanel.this.setVisible(false);
					((Window) getRootPane().getParent()).dispose();
				}
				else{
					JOptionPane.showMessageDialog(null,"Sorry, you couldn't afford these items/not enough stock, please add balance.");
				}
			}
			else{
				JOptionPane.showMessageDialog(null,"No items in basket, please add product before reattempting.");
			}
		}
		
	}
}
