
public class MixtureZone implements Zone {
	private String name;
	private Store store;
	private String desc;
	
	public MixtureZone (String name,Store store, String desc){
		//SEE ActivityZone.java FOR COMMENTS, SAME FORMAT AS THIS
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
