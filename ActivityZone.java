
public class ActivityZone implements Zone {
	private String name;
	private Store store;
	private String desc;
	
	public ActivityZone (String name, Store store, String desc){
		//all initialising name etc and get methods
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
