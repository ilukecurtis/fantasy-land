import java.util.ArrayList;


public class Model {
	@SuppressWarnings("rawtypes")
	protected static ArrayList<ArrayList> listOfProductLists;
	protected static ArrayList<Store> storeList;
	protected ArrayList<Zone> zoneList;
	protected final static int MAX_AMOUNT=4;
	public void init() {
		// this method creates the world using a factory design pattern prior to building the GUI
		createProducts();
		createStores();
		createZones();
	}
	@SuppressWarnings("rawtypes")
	private void createProducts(){
		//SINGLETON IMPLEMENTED HERE FOR ALL FACTORIES
		ProductFactory factoryProduct = ProductFactory.getInstance();
		//BEGIN PRODUCT CREATION
		//weird I know, but store a list of lists, pass an index of listOfProductLists to each store so each store gets a different product list
		listOfProductLists = new ArrayList<ArrayList>();
		//two seperate types of products as defined by the factory class stored in separate ArrayLists
		ArrayList<Product> activitiesProductList = new ArrayList<Product>();
		ArrayList<Product> purchasableProductList = new ArrayList<Product>();
		ArrayList<Product> purchasableProductList2 = new ArrayList<Product>();
		ArrayList<Product> mixtureList = new ArrayList<Product>();
		
		//parallel array's to the list so a different product name each time we enter the zone
		String[][] activityProductNames = {
				{"Chop Tomatoes","Learn how to chop tomatoes the authetic Italian way!"},
				{"Stir Fry","Test your skills in creating an original Morrocan stir fry"},
				{"Knife Weilding Lesson", "Experience if you dare a lesson in knife throwing and weilding "},
				{"Apron Wearing Competition", "Who wore it best? You decide!"},
				{"Pan Balancing Challenge", "Pan balancers from across the globe meet here to test their skills"}
		};
		String[][] mixtureListNames = {
				{"Learn to Film","A intensive course to learn the basic of filming."},
				{"Learn Post Production","Take a tour of our editing suite"},
				{"Experience 4D", "Get squirted in the face with water in this 4D version of Titanic"},
				{"Flight Simulator", "Take an authentic trip in our state-of-the-art flight simulator"},
				{"Film Figthing","Learn how to fight like they do in the movies!"}
		};
		String[][] mixtureListNamesPurchase = {
				{"House of cards Season 1","The entire first season of house of cards wow!."},
				{"Breaking bad","The definitive collection, essential viewing!"},
				{"Pulp Fiction", "Classic cinema, classic price"},
				{"Rise of the Planet", "That planet sure knows how to rise up!"},
				{"Dans Disco Recording","View our own resisdent DJ Dan's set!"}
		};

		double[] mixtureListPrices = {2.10, 3.23, 8.18, 1.22, 0.50};
		//parallel array for products and prices
		String[][] purchasableProductNames = {
				{"Ridge Racer","Thrilling racing game for PlayStation"},
				{"Call of Duty", "Arcade shooter for Xbox"},
				{"World of Warcraft","Immersive MMORPG for PC"},
				{"Sim City","The definitive simulation game for PC"},
				{"Tomb Raider","A captivating story of one womans journey through disaster"}
		};
		String[][] purchasableProductNames2 = {
				{"Replica Mona Lisa","Admire a beautiful replica by DaVinci"},
				{"Van Gogh Documentary", "Learn the life of Van Gogh"},
				{"Painting Set","Get high quality paint for your art"},
				{"Brushes", "Only the finest brushes in all of Fantasy Land!"},
				{"Picasso Mixtape","A collection of all the music from the era"}
		};
		double[] purchasableProductPrices = {1.20, 2.32, 1.88, 2.11, 5.00};
		//generate list of products first in an array list
		for (int x=0; x < MAX_AMOUNT+1; x++){
			activitiesProductList.add(factoryProduct.getProduct("activity", activityProductNames[x][0], false, 0,mixtureListPrices[x],activityProductNames[x][1]));
			purchasableProductList.add(factoryProduct.getProduct("purchasable", purchasableProductNames[x][0], true, 5, purchasableProductPrices[x],purchasableProductNames[x][1]));
			mixtureList.add(factoryProduct.getProduct("activity", mixtureListNames[x][0], false, 0,mixtureListPrices[x],mixtureListNames[x][1]));
			mixtureList.add(factoryProduct.getProduct("purchasable", mixtureListNamesPurchase[x][0], true, 5,mixtureListPrices[x],mixtureListNamesPurchase[x][1]));
			purchasableProductList2.add(factoryProduct.getProduct("purchasable", purchasableProductNames2[x][0], true, 5, purchasableProductPrices[x],purchasableProductNames2[x][1]));
		}
		//add these to the list of lists so we can use each list later when adding to the zone
		listOfProductLists.add(activitiesProductList);
		listOfProductLists.add(purchasableProductList);
		listOfProductLists.add(purchasableProductList2);
		listOfProductLists.add(mixtureList);
		//END PRODUCT CREATION
	}
	@SuppressWarnings("unchecked")
	private void createStores(){
		// generate list of stores then and store product list in the stores
		//Singleton creation here of StoreFactory
		StoreFactory factoryStore = StoreFactory.getInstance();
		storeList = new ArrayList<Store>();
		//2d array so every store I add has a different name, and description
		String [][] listOfStoreNames = {
				{"Cooking Store", "A place to cook!"},
				{ "Gaming Store","A place to game"}, 
			    {"Movie Store", "A place to movie"}, 
				{"Art Store","Yay art"}
		};
		String [] activityOrPurchase = {"ACTIVITY", "shopping"};
		//to alternate stores between activity and purchasable stores, get the modulus of x
		//this determines whether the loop is odd or even, if it is even y is 0 (if 1, shopping store) and that
		//variable (y) is used as the index to decide what type of store is going to be created
		//in the getStore method for factoryStore
		for (int x=0; x < MAX_AMOUNT; x++){
			//where y is either 1 or 0 depending on the modulus of the loop switch between an activity store, or a product store
			//also switch the list of product lists depending on index, where Z is the list we are referencing depending on the zone that will be created
			int y=0;
			int z=0;
			if(x == 1){
				y = 1;
				z = 1;
			}
			else if(x == 2){
				y = 0;
				z = 3;
			}
			else if (x == 3)
			{
				y = 1;
				z = 2;
			}
			storeList.add(factoryStore.getStore(activityOrPurchase[y], listOfProductLists.get(z),listOfStoreNames[x][0], listOfStoreNames[x][1]));
		}
	}
	private void createZones(){
		// generate list of zones and store the stores in there which also contain the products
		//Singleton zone factory used here, once again, create a list of zones 
		ZoneFactory factoryZone = ZoneFactory.getInstance();
		zoneList = new ArrayList<Zone>();
		//same as before with stores, a 2d array with name and description to fill zoneList with indexs'
		String [][] listOfZoneNames = {
			  {"Cooking Zone", "A zone to cook!"},
			  { "Gaming Zone","A zone to game"}, 
			  {"Movie Zone", "A zone to movie"}, 
			  {"Art Zone","Yay art zone"}};
		String [] zoneTypes = {"activity", "shopping", "mixture","activity"};
		//add one zone that is the default entrance for the program.
		zoneList.add(factoryZone.getZone("mixture", "Reception",null,null));
		for (int x=0; x < MAX_AMOUNT; x++){
			zoneList.add(factoryZone.getZone(zoneTypes[x],listOfZoneNames[x][0], storeList.get(x), listOfZoneNames[x][1]));
		}
	}
	protected ArrayList<Store> getStore(){
		return storeList;
	}
}
