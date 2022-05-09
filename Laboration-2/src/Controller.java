import java.awt.event.*;
import javax.swing.event.*;

//Skapat av Mateusz Weber och Felicia Strandberg.

public class Controller implements ActionListener, MouseListener, DocumentListener {
	private Vy v;
	private Model m;
	private PatioImage p;
	private IndoorImage i;
	private int tablesFull;

	public Controller(Vy vin) {
		v = vin;										// Variabel för alla inkommande värden från vyn
		m = new Model();								// Instansvariabel för modellen
		this.p = v.getPatioImage();						// Instansvariabel för utomhusbord
		this.i = v.getIndoorImage();					// Instansvariabel för inomhusbord
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

	/**
	 * Metod som anropas när man klickar med musen. 
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		
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
		
		// Om tablesFull är 16, att alla bord är true (bokade), så blir knappen med texten "Lägg till i kö" enabled. 
		if(tablesFull == 16) {
			v.setQueueButtonEnabled(); 
		} else {
			v.setQueueButtonDisabled();
		}
		
		System.out.println(tablesFull);
		
		if (e.getButton() == MouseEvent.BUTTON1) { // Kollar om man vänsterklickat
			
			// System.out.println(e.getSource());
			if(e.getSource() == p) {
				for (Table table : p.getTables()) {	// Loopar igenom alla utomhusbord i patioImage-klassen
					if (e.getX() >= table.getX() && e.getX() <= table.getX() + table.WIDTH) { // Kollar om där man klickat är större eller lika med bordet i loopens x- och y-värde.
						if (e.getY() >= table.getY() && e.getY() <= table.getY() + table.HEIGHT) {
							table.toggleActivate(); 
							v.repaintImage();
							
							// Fråga om vi får hårdkoda index i loopen på rad 43 och kolla index - det tal som speglar listan i indoorimage-klassen
							// Lägg till så att man kan ta bort från kölistan
						}
					}
				}
			} else if(e.getSource() == i) {
				for (Table table : i.getTables()) { // Loopar igenom alla inomhusbord i indoorImage-klassen
					if (e.getX() >= table.getX() && e.getX() <= table.getX() + table.WIDTH) {
						if (e.getY() >= table.getY() && e.getY() <= table.getY() + table.HEIGHT) {
							table.toggleActivate();
							v.repaintImage();
						}
					}
				}
			}
		}
	}
	
	/**
	 * Metod som behöver överlagras men som inte används. 
	 */
	@Override
	public void mouseClicked(MouseEvent e) {}
	
	/**
	 * Metod som behöver överlagras men som inte används. 
	 */
	@Override
	public void mouseReleased(MouseEvent e) {}
	
	/**
	 * Metod som behöver överlagras men som inte används. 
	 */
	@Override
	public void mouseEntered(MouseEvent e) {}

	/**
	 * Metod som behöver överlagras men som inte används. 
	 */
	@Override
	public void mouseExited(MouseEvent e) {}

	/**
	 * Metod som behöver överlagras men som inte används. 
	 */
	@Override
	public void insertUpdate(DocumentEvent e) {}

	/**
	 * Metod som behöver överlagras men som inte används. 
	 */
	@Override
	public void removeUpdate(DocumentEvent e) {}

	/**
	 * Metod som behöver överlagras men som inte används. 
	 */
	@Override
	public void changedUpdate(DocumentEvent e) {}

}