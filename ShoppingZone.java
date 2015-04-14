public class ShoppingZone implements Zone{
	//see ActivityZone.java for commented code
	private String name;
	private Store store;
	private String desc;
	
	public ShoppingZone (String name, Store store, String desc){
		this.name = name;
		this.store = store;
		this.desc = desc;
	}
	@Override
	public String getName() {
		
		return name;
	}

	@Override
	public Store getStores() {
		
		return store;
	}

	@Override
	public String getDesc() {
		
		return desc;
	}
	@Override
	public String getStoreName() {
		return store.getName();
	}
}
