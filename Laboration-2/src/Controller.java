import java.awt.event.*;
import javax.swing.event.*;

//Skapat av Mateusz Weber och Felicia Strandberg.

public class Controller implements ActionListener, MouseListener, ListSelectionListener {
	private Vy v;
	private Model m;
	private PatioImage p;
	private IndoorImage i;
	private int tablesFull;
	private boolean flag;

	/**
	 * Konstruktorn.
	 * @param vin
	 * Händelser som triggas i vyn. 
	 */
	public Controller(Vy vin) {
		v = vin;										// Variabel för alla inkommande värden från vyn
		m = new Model();								// Instansvariabel för modellen
		this.p = v.getPatioImage();						// Instansvariabel för utomhusbord
		this.i = v.getIndoorImage();					// Instansvariabel för inomhusbord
		tablesFull = 0;									// Variabel som håller antal lediga bord
		flag = false;
	}

	/**
	 * Metod som anropas när en händelse sker i vyn. 
	 * Kollar om knapparna med texten "Boka från kö" eller "Lägg till kö" har tryckts på. 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// Om man trycker på knappen med texten "Lägg till i kö" och trycker på knappen OK så läggs namnet och storleken på sällskapet i kölistan i modellen. 
		if(e.getActionCommand() == "Add") {
			if(v.addToQueue() == 0) { 
				m.setGuestName(v.getGuestName());
				m.setGuestSize(v.getGuestSize());
				m.addGuestToQueue(); 
				v.addGuestToList(m.getGuestQueue());
				v.setRemoveButtonEnabled();
				
				/* if(m.getGuestQueue().isEmpty()) {
					v.setBookButtonToDisabled();
				} */
			} 
			
		} else if(e.getActionCommand() == "Remove") {
			if(v.verifyRemoveDialog() == 0) {
				m.removeGuestFromQueue(v.removeItemInList());
			}
			if(m.getGuestQueue().isEmpty()) {
				v.setRemoveButtonDisabled();
			}
		}
	}

	/**
	 * Metod som anropas när man klickar med musen. 
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) { // Kollar om man vänsterklickat
			
			if(e.getSource() == p) {
				for (Table table : p.getTables()) {	// Loopar igenom alla utomhusbord i patioImage-klassen
					if (e.getX() >= table.getX() && e.getX() <= table.getX() + table.WIDTH) { // Kollar om där man klickat är större eller lika med bordet i loopens x- och y-värde.
						if (e.getY() >= table.getY() && e.getY() <= table.getY() + table.HEIGHT) {
							v.repaintImage();
							if(!table.isActivated() && flag) {
								m.removeGuestFromQueue(v.removeGuestFromList());
								v.removeItemInList();
								table.toggleActivate(); 
								flag = false;
								v.setRemoveButtonDisabled();
							} else {
								table.toggleActivate(); 
							}
						}
					}
				}
				
			} else if(e.getSource() == i) {
				for (Table table : i.getTables()) { // Loopar igenom alla inomhusbord i indoorImage-klassen
					if (e.getX() >= table.getX() && e.getX() <= table.getX() + table.WIDTH - 30) {
						if (e.getY() >= table.getY() && e.getY() <= table.getY() + table.HEIGHT - 10) {
							v.repaintImage();
							if(!table.isActivated() && flag) {
								m.removeGuestFromQueue(v.removeGuestFromList());
								v.removeItemInList();
								table.toggleActivate(); 
								flag = false;
								v.setRemoveButtonDisabled();
							} else {
								table.toggleActivate(); 
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * Metod som anropas när en ändring av värde sker i guestQueueList.
	 * Sätter en flagga till true för om man vill boka genom kölistan. 
	 * Flaggan indikerar på om en post i listan har valts. 
	 */
	@Override
	public void valueChanged(ListSelectionEvent e) {
		flag = true;
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

}