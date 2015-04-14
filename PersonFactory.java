
public class PersonFactory {
	//SINGLETON here to make sure only one personfactory is ever created
	 private static final PersonFactory uniFactory = new PersonFactory();
	 
	 private PersonFactory(){}
	 
	 public static PersonFactory getInstance(){
	    return uniFactory;
	 }
	
	public Person getPerson(String personType, String name, Double bal, int loc, boolean registerForNotifications){	
		//does a string comparison, depending on whether the model requires a playable character or a non-playable character
		if (personType.equalsIgnoreCase("NPC")){
			return new NPCPerson("Name", 0);
		}
		else if (personType.equalsIgnoreCase("player")){
			return new Player(name, loc, bal, registerForNotifications);
		}
		return null;
	}
}
