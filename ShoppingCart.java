import java.util.ArrayList;

public class ShoppingCart {
	//the shopping cart is assigned to a player, it has a list of products they will eventually buy
	private ArrayList<Product> cartProducts;
	@SuppressWarnings("unused")
	//num items is used to remove that item from the arraylist when interacting with a GUI
	private int numItems;
	public ShoppingCart(){
		//intialise all data, required
		cartProducts = new ArrayList<Product>();
	}
	protected void addItem(Product desiredProduct){
		cartProducts.add(desiredProduct);
		numItems+=1;
	}
	protected void removeItem(Product desiredProduct, int indexOfItem){
		cartProducts.remove(indexOfItem);
		// need num items to go down so the index's dont mess up for the arraylist
		numItems-=1;
	}
	protected ArrayList<Product> getCartItems(){
		return cartProducts;
	}
	protected void emptyCart(){
		cartProducts.clear();
	}
}
