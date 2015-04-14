
public interface Product {
	//interface, declare methods each product will need
	public String getName();
	public boolean hasStock();
	public int getStock();
	public double getPrice();
	public String getDesc();
	public void removeStock();
	public int returnStockLevels();
}
