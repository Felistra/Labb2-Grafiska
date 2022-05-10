
// Skapat av Mateusz Weber och Felicia Strandberg.

public class Table {
	public static final int WIDTH = 65;
	public static final int HEIGHT = 65;
	private int x, y;
	private boolean activated;
	
	/**
	 * Konstruktorn.
	 * Kod inspirerad av Jonathan Vestin från föreläsning 9. 
	 *
	 */
	public Table(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean isActivated() { return activated; }
	public void toggleActivate() { activated = !activated; }

}
