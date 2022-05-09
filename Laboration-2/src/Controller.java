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
			v.removeTableFromList();
			for(int i = 0; i < 6; i++) {
				if(!p.getTables().get(i).isActivated()) {
					v.addTableToList(i + 1); // tog bort + 1
				}
			}
			
			for(int j = 0; j < 10; j++) {
				if(!i.getTables().get(j).isActivated()) { 
					v.addTableToList(j + 7);
				}
			}
			
			if(v.bookFromQueue() == 0) {
				m.removeGuestFromQueue(v.removeGuestFromList());
				
				if(v.getTableFromList() < 7) {
					System.out.println(v.getTableFromList());
					p.getTables().get(v.getTableFromList()).toggleActivate();
					
				} else if(v.getTableFromList() > 7) {
					System.out.println(v.getTableFromList());
					i.getTables().get(v.getTableFromList() - 2).toggleActivate();
				}
				
				if(m.getGuestQueue().isEmpty()) {
					v.setBookButtonToDisabled(); 
				}
			}
		}
		else if(e.getActionCommand() == "Add") {
			if(v.addToQueue() == 0) { // 0 för OK
				m.setGuestName(v.getGuestName());
				m.setGuestSize(v.getGuestSize());
				m.addGuestToQueue(); 
				v.setBookButtonToEnabled(); 
			} 
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		/* for(int k = 0; k < 6; k++) {
			for(int j = 0; j < 10; j++) {
				if(!p.getTables().get(k).isActivated() || !i.getTables().get(j).isActivated()) {
					v.setQueueButtonDisabled();
				} else {
					v.setQueueButtonEnabled(); 
				}
			}
		} */
		v.setQueueButtonEnabled();
		if (e.getButton() == MouseEvent.BUTTON1) {
			for (Table table : p.getTables()) {
				if (e.getX() >= table.getX() && e.getX() <= table.getX() + table.WIDTH) {
					if (e.getY() >= table.getY() && e.getY() <= table.getY() + table.HEIGHT) {
						table.toggleActivate(); 
						System.out.println(table.isActivated());
						// FRÅGA OM .repaint(); och bord 5, 6, 8, 9, 10, 11, 12, 14, 15 och 16 (klicka utanför)
						// Fråga om vi får hårdkoda index i loopen på rad 43 och kolla index - det tal som speglar listan i indoorimage-klassen
					}
				}
			}
			for (Table table : i.getTables()) {
				if (e.getX() >= table.getX() && e.getX() <= table.getX() + table.WIDTH) {
					if (e.getY() >= table.getY() && e.getY() <= table.getY() + table.HEIGHT) {
						table.toggleActivate();
						System.out.println(table.isActivated());
						// FRÅGA OM .repaint(); 
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