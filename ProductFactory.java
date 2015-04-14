public class ProductFactory {
	// see PersonFactory.java for commented code, its the same stuff
	 private static final ProductFactory uniFactory = new ProductFactory();
	 
	 private ProductFactory(){}
	 
	 public static ProductFactory getInstance(){
	    return uniFactory;
	 }
	public Product getProduct (String productType, String productName, boolean purchasableBool, int stock, double d, String desc){
		
		if (productType.equalsIgnoreCase("activity")){
			return new ActivityProduct(productName,purchasableBool,stock,d, desc);
		}
		else if (productType.equalsIgnoreCase("purchasable")){
			return new PurchasableProduct(productName,purchasableBool,stock,d, desc);
		}
		return null;
	}
}
