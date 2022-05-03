import java.awt.event.*;

public class Controller implements ActionListener, MouseListener {
	private Vy v;
	private Model m;
	private Table t;
	
	public Controller(Vy vin) {
		v = vin;
		m = new Model(); 
		t = new Table(10, 10); 
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("X: " + e.getX() + " Y: " + e.getY());
		/*if(e.getButton() == MouseEvent.BUTTON1) {
		for(Table table : tables) {
		if(e.getX() >= square.getX() && e.getX() <= square.getX() +
		square.WIDTH) {
		if(e.getY() >= square.getY() && e.getY() <= 
		square.getY() + square.HEIGHT) {
		System.out.println("Clicked something! " + 
		e.getWhen());
		square.toggleActivate();
		}
		this.repaint();
		}
		}
		}*/
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
