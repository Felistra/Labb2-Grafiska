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
    	if(name.length() < 1) {
    		name = "";
    	} else {
    		this.name = name;
    	}
    }
    
    public void setGuestSize(String size) {
    	if(size.length() >= 1) {
    		for(char c : size.toCharArray()) { // Kodinspiration från https://stackoverflow.com/questions/18590901/check-and-extract-a-number-from-a-string-in-java
    			if(!Character.isDigit(c)) {
    				this.size = "0";
    			} else {
    				this.size = size;
    			}
    		}
    	} else {
    		this.size = "0";
    	}
    }

	public void addGuestToQueue() {
		guests.add(name);
		guests.add(size);
	}
	
	public void removeGuestFromQueue(int i) {
		guests.remove(i);
		guests.remove(i);
	}
	
	public ArrayList<String> getGuestQueue() {
		return guests;
	}

}
