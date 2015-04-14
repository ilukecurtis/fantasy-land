import java.util.ArrayList;


public class StoreFactory {
//See PersonFactory.java for commented code
	 private static final StoreFactory uniFactory = new StoreFactory();
	 //singleton here
	 private StoreFactory(){}
	 
	 public static StoreFactory getInstance(){
	    return uniFactory;
	 }
	public Store getStore (String storeType, ArrayList<Product> products, String storeName, String storeDesc){
		
		if (storeType.equalsIgnoreCase("activity")){
			return new ActivityStore(storeName,products,storeDesc);
		}
		else if (storeType.equalsIgnoreCase("shopping")){
			return new ShoppingStore(storeName,products,storeDesc);
		}
		else if (storeType.equalsIgnoreCase("mixture")){
			return new MixtureStore(storeName,products,storeDesc);
		}
		return null;
	}
	
}
