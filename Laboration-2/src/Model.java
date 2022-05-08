import java.util.*;

public class Model {

    private String name;
    private String size;
    private ArrayList<String> guests;

    public Model() {
    	name = "";
    	size = "";
    	guests = new ArrayList<String>(); 
    }
    
    public void setGuestName(String name) {
    	this.name = name;
    }
    
    public void setGuestSize(String size) {
    	this.size = size;
    }

	public void addGuestToQueue() {
		guests.add(name);
		guests.add(size);
	}
	
	public ArrayList<String> getGuestQueue() {
		return guests;
	}

}
