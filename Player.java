import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

public class Player implements Person, Observer{
	//has all information about the player, included what is in their cart (stored in cart class)
	private String name;
	private int location;
	private double bal;
	private ArrayList<Product> purchasedProducts;
	private int experience;
	protected boolean hasCart;
	private boolean isRegisteredForNotifications;
	private ShoppingCart cart;
	public Player (String name, int location, double bal, boolean didRegister){
		//initialise data and have all sets and gets regarding this data
		this.name = name;
		this.location = location;
		this.bal = bal;
		this.hasCart = false;
		this.experience = 0;
		purchasedProducts = new ArrayList<Product>();
		isRegisteredForNotifications = didRegister;
	}
	public String getName() {
		return name;
	}

	public boolean checkCart(){
		return hasCart;
	}
	public int getLocation() {
		return location;
	}

	public double getBalance() {
		return bal;
	}
	public ArrayList<Product> getPurchasedProducts() {
		return purchasedProducts;
	}
	public void getCart() {
		cart = new ShoppingCart();
	}
	public void addExperience(){
		experience++;
	}
	public int getExperience(){
		return experience;
	}
	//add a product to their purchased product so it can display on exit
	protected void addPurchasedProducts(ArrayList<Product> boughtProducts){
		purchasedProducts=boughtProducts;
	}
	protected void addBalance (double moreBal){
		this.bal+= moreBal;
	}
	//boolean to check if the user can afford something
	protected boolean buyProducts(double cost, ArrayList<Product> buyingList){
		if (bal-cost < 0){
			return false;
		}
		else{
			Iterator<Product> buyingListIterator = buyingList.iterator();
			int x=0;
			while(buyingListIterator.hasNext()){
				if(buyingList.get(x).hasStock()){
					if(buyingList.get(x).returnStockLevels() > 0){
						buyingList.get(x).removeStock();
					}
					else{
						return false;
					}
				}
				buyingListIterator.next();
				x++;
			}
			bal=bal-cost;
			//add 1 xp for each product purchased
			experience+=buyingList.size();
			if(experience > 5){
				Controller.levelPlayerUp();
			}
			purchasedProducts.addAll(buyingList);
			return true;
		}
	}
	public void updateLocation (int loc){
		//update user location
		this.location = loc;
	}
	@Override
	public void update(Observable o, Object arg) {
		//alright, product is passed as object, cast to product, call methods, pass over to updating the customer
		Product tempProd = (Product) arg;
		if(Controller.getPlayer().isRegisteredForNotifications){
			Controller.newProdNotifier(tempProd);
		}
	}
	protected ShoppingCart passCart(){
		return cart;
	}
	protected boolean getIfRegistered(){
		return isRegisteredForNotifications;
	}
}
