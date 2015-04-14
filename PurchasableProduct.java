
public class PurchasableProduct implements Product {
	//SEE ActivityProduct.java for commented code (its mostly the same)
	private String name;
	private boolean hasStock;
	private int stock;
	private double price;
	private String desc;
	
	public PurchasableProduct (String name, boolean hasStock, int stock, double price, String desc){
		this.name = name;
		this.hasStock = hasStock;
		this.stock = stock;
		this.price = price;
		this.desc = desc;
	}
	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean hasStock() {
		return hasStock;
	}

	@Override
	public int getStock() {
		return stock;
	}

	@Override
	public double getPrice() {
		return price;
	}
	@Override
	public String getDesc() {
		return desc;
	}
	public void removeStock(){
		this.stock -= 1;
	}
	@Override
	public int returnStockLevels() {
		// TODO Auto-generated method stub
		return this.stock;
	}
}
