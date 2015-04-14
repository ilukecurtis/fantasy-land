public class ZoneFactory {
	//see PersonFactory.java for commented code (generally same algorithms etc)
	 private static final ZoneFactory uniFactory = new ZoneFactory();
	 
	 private ZoneFactory(){}
	 
	 public static ZoneFactory getInstance(){
	    return uniFactory;
	 }
	 
	public Zone getZone (String zoneType, String zoneName, Store store, String desc){
		
		if (zoneType.equalsIgnoreCase("activity")){
			return new ActivityZone(zoneName, store,desc);
		}
		else if (zoneType.equalsIgnoreCase("shopping")){
			return new ShoppingZone(zoneName, store,desc);
		}
		else if (zoneType.equalsIgnoreCase("mixture")){
			return new MixtureZone(zoneName,store,desc);
		}
		return null;
	}
}
