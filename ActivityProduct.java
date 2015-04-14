public class ActivityProduct implements Product{
	private String name;
	private boolean hasStock;
	private int stock;
	private double price;
	private String desc;
	public ActivityProduct (String name, boolean hasStock, int stock, double price, String desc){
		//initialise the product needed with all required data, also the GET methods.
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
	public String getDesc(){
		return desc;
	}
	@Override
	public void removeStock() {
		//not needed
	}
	@Override
	public int returnStockLevels() {
		//not needed
		return 0;
	}
}
