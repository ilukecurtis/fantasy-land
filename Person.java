import java.util.ArrayList;

//interface for Person so different types of people can be created for the required task
public interface Person {
	public String getName();
	public int getLocation();
	public double getBalance();
	public ArrayList<Product> getPurchasedProducts();
}
