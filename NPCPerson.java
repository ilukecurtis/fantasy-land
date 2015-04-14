import java.util.ArrayList;

	@SuppressWarnings("unused")
public class NPCPerson implements Person{

	private String name;
	private int location;
	//make balance uneditable for NPCs
	private final double balance=0;
	
	public NPCPerson(String name, int location){
		this.name = name;
		this.location = location;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getLocation() {
		// TODO Auto-generated method stub
		return 0;
	}

	public double getBalance() {
		// TODO Auto-generated method stub
		return 0;
	}

	public ArrayList<Product> getPurchasedProducts() {
		// TODO Auto-generated method stub
		return null;
	}
}
