import java.util.*;

//Skapat av Mateusz Weber och Felicia Strandberg.

public class Model {

    private String name;
    private String size;
    private ArrayList<String> guests;

    /**
     * Konstruktorn. 
     */
    public Model() {
    	name = "";
    	size = "";
    	guests = new ArrayList<String>(); 
    }
    
    /**
     * Metod för att sätta name till name. 
     * @param name
     * Namnet som skrivits in fältet för namn i vyn. 
     */
    public void setGuestName(String name) {
    	if(name.length() < 1) {
    		name = "";
    	} else {
    		this.name = name;
    	}
    }
    
    /**
     * Metod för att sätta size till size. 
     * @param size
     * Storlek som skrivits in i fältet för storlek i vyn. 
     */
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
    
    /**
     * Metod som lägger till namn och storlek i arraylist.
     */
	public void addGuestToQueue() {
		guests.add(name);
		guests.add(size);
	}
	
	/**
	 * Metod som tar bort namn och storlek i arraylist.
	 * @param i
	 * Index på den post som ska tas bort.
	 */
	public void removeGuestFromQueue(int i) {
		guests.remove(i);
		guests.remove(i);
	}
	
	/**
	 * Metod som returnerar kölistan. 
	 * @return
	 * Kölistan. 
	 */
	public ArrayList<String> getGuestQueue() {
		return guests;
	}

}
