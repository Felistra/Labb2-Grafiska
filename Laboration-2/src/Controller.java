import java.awt.event.*;
import javax.swing.event.*;

public class Controller implements ActionListener, MouseListener, DocumentListener {
	private Vy v;
	private Model m;
	private PatioImage p;
	private IndoorImage i;

	public Controller(Vy vin) {
		v = vin;
		m = new Model();
		p = new PatioImage();
		i = new IndoorImage();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "Boka") {
			v.addGuestToList(m.getGuestQueue());
			if(v.bookFromQueue() == 0) {
				if(m.getGuestQueue().isEmpty()) {
					v.setButtonToDisabled(); 
				}
			}
		}
		else if(e.getActionCommand() == "Add") {
			if(v.addToQueue() == 0) { // 0 för OK
				m.setGuestName(v.getGuestName());
				m.setGuestSize(v.getGuestSize());
				m.addGuestToQueue(); 
				v.setButtonToEnabled(); 
			} 
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			for (Table table : p.getTables()) {
				if (e.getX() >= table.getX() && e.getX() <= table.getX() + table.WIDTH) {
					if (e.getY() >= table.getY() && e.getY() <= table.getY() + table.HEIGHT) {
						table.toggleActivate(); 
						System.out.println(table.isActivated());
					}
				}
			}
			for (Table table : i.getTables()) {
				if (e.getX() >= table.getX() && e.getX() <= table.getX() + table.WIDTH) {
					if (e.getY() >= table.getY() && e.getY() <= table.getY() + table.HEIGHT) {
						table.toggleActivate();
						System.out.println(table.isActivated());
					}
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void insertUpdate(DocumentEvent e) {}

	@Override
	public void removeUpdate(DocumentEvent e) {}

	@Override
	public void changedUpdate(DocumentEvent e) {}

}