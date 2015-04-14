import java.util.ArrayList;
import java.util.Observable;
public class ActivityStore extends Observable implements Store {
	private String name;
	private ArrayList<Product> products;
	private String desc;
	
	public ActivityStore(String name, ArrayList<Product> products, String desc){
		//initalise all data, also run the get and add new product methods
		this.name = name;
		this.products = products;
		this.desc = desc;
		//add a temp player with default variables, to make it observable for the class
		Player ObPlayer = new Player(null, 0, 0,false);
		addObserver(ObPlayer);
	}
	public String getName(){
		return name;
	}
	public ArrayList<Product> getProducts(){
		return products;
	}
	public String getDesc(){
		return desc;
	}
	@Override
	public void productsChanged(Product newProd) {
		setChanged();
		notifyObservers(newProd);
	}
	public void addNewProduct(Product prodToAdd) {
		//when product needed to be added get required info that was sent over to method, and add it to list
		String prodToAddName = prodToAdd.getName();
		boolean prodToAddStockBool = prodToAdd.hasStock();
		int prodToAddStock = prodToAdd.getStock();
		double prodToAddPrice = prodToAdd.getPrice();
		String prodToAddDesc = prodToAdd.getDesc();

		Product newProd = new ActivityProduct(prodToAddName, prodToAddStockBool, prodToAddStock, prodToAddPrice, prodToAddDesc);
		products.add(newProd);
		productsChanged(newProd);
	}
}
