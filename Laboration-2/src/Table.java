
public class Table {
	public static final int WIDTH = 65;
	public static final int HEIGHT = 65;
	private int x, y;
	private boolean activated;
	
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
	public void toggleActivate() { activated = ! activated; }
	
	
}
