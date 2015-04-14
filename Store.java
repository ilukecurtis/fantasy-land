import java.util.ArrayList;


public interface Store {
	//interface for all types of store that are created
	public String getName();
	public ArrayList<Product> getProducts();
	public String getDesc();
	public void productsChanged(Product newProd);
	public void addNewProduct(Product prodToAdd);
}
