import java.awt.event.*;
import javax.swing.event.*;

public class Controller implements ActionListener, MouseListener, DocumentListener {
	private Vy v;
	private Model m;
	private PatioImage p;
	private IndoorImage i;
	private int tablesFull;

	public Controller(Vy vin) {
		v = vin;										// Variabel för alla inkommande värden från vyn
		m = new Model();								// Instansvariabel för modellen
		p = new PatioImage();							// Instansvariabel för utomhusbord
		i = new IndoorImage();							// Instansvariabel för inomhusbord
		tablesFull = 0;									// Variabel som håller antal lediga bord
	}

	/**
	 * Metod som anropas när en händelse sker i vyn. 
	 * Kollar om knapparna med texten "Boka från kö" eller "Lägg till kö" har tryckts på. 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// Om man trycker på knappen med texten "Boka från kö" så läggs namnet från kölistan i modellen till i vyns lista. 
		if(e.getActionCommand() == "Boka") {
			v.addGuestToList(m.getGuestQueue());
			v.removeTableFromList();
			for(int i = 0; i < 6; i++) {
				if(!p.getTables().get(i).isActivated()) {
					v.addTableToList(i + 1); 
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
					i.getTables().get(v.getTableFromList()).toggleActivate();
				}
				
				if(m.getGuestQueue().isEmpty()) {
					v.setBookButtonToDisabled(); 
				}
			}
		// Om man trycker på knappen med texten "Lägg till i kö" och trycker på knappen OK så läggs namnet och storleken på sällskapet i kölistan i modellen. 
		} else if(e.getActionCommand() == "Add") {
			if(v.addToQueue() == 0) { 
				m.setGuestName(v.getGuestName());
				m.setGuestSize(v.getGuestSize());
				m.addGuestToQueue(); 
				v.setBookButtonToEnabled(); 
			} 
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	/**
	 * Metod som anropas när man klickar med musen. 
	 * 
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		tablesFull = 0;
		
		// Loopar igenom båda bordslistorna i sina klasser och räknas upp tablesFull för att se om alla borden är upptagna och i sådana fall göra knapparna, för att hantera en kö, enabled. 
		for(int k = 0; k < 6; k++) {
			if(p.getTables().get(k).isActivated()) {
				tablesFull++;
			}
		} 
		
		for(int j = 0; j < 10; j++) {
			if(i.getTables().get(j).isActivated()) {
				tablesFull++;
			}
		}
		
		// Om tablesFull är 16, att alla bord är true, så blir knappen med texten "Lägg till i kö" enabled. 
		if(tablesFull == 16) {
			v.setQueueButtonEnabled(); 
		} else {
			v.setQueueButtonDisabled();
		}
		
		System.out.println(tablesFull);
		
		if (e.getButton() == MouseEvent.BUTTON1) {
			for (Table table : p.getTables()) {
				if (e.getX() >= table.getX() && e.getX() <= table.getX() + table.WIDTH) {
					if (e.getY() >= table.getY() && e.getY() <= table.getY() + table.HEIGHT) {
						table.toggleActivate(); 
						System.out.println(e.getX() + " " + e.getY());
						//System.out.println(table.isActivated());
						// FRÅGA OM .repaint(); och bord 5, 6, 8, 9, 10, 11, 12, 14, 15 och 16 (klicka utanför)
						// Fråga om vi får hårdkoda index i loopen på rad 43 och kolla index - det tal som speglar listan i indoorimage-klassen
						// Fråga om vi får instansiera objekt av indoorimage och patioimage-klasserna i controller
						// Ska vi verkligen ha mouselistener på bilderna?
					}
				}
			}
			for (Table table : i.getTables()) {
				if (e.getX() >= table.getX() && e.getX() <= table.getX() + table.WIDTH) {
					if (e.getY() >= table.getY() && e.getY() <= table.getY() + table.HEIGHT) {
						table.toggleActivate();
						//System.out.println(table.isActivated());
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