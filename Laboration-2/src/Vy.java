import java.awt.*;
import javax.swing.*;
import java.lang.*;
import java.util.ArrayList;

// Skapat av Mateusz Weber och Felicia Strandberg.

public class Vy extends JFrame {
	private JButton laggTillButton, removeButton; 
	private JPanel patioPanel, insidePanel, mainPanel, addPanel, namePanel, sizePanel, bookPanel, listAndButtonPanel, buttonPanel;
	private JLabel patioLabel, insideLabel, nameLabel, sizeLabel, guestLabel;
	private Controller controller;
	private PatioImage patioImage;
	private IndoorImage indoorImage;
	private final int padding = 10;
	private JTextField name;
	private JTextField size;
	private DefaultListModel<String> listModel;
	private JList<String> guestQueueList;
	
	/**
	 * Konstruktorn. 
	 */
	public Vy() {
		patioImage = new PatioImage();
		indoorImage = new IndoorImage();
		controller = new Controller(this);
		patioPanel = new JPanel();
		insidePanel = new JPanel();
		mainPanel = new JPanel(); 
		name = new JTextField(20);
		size = new JTextField(20);
		addPanel = new JPanel();
		namePanel = new JPanel();
		sizePanel = new JPanel();
		nameLabel = new JLabel("Namn:");
		sizeLabel = new JLabel("Storlek på sällskap:");
		bookPanel = new JPanel(); 
		listModel = new DefaultListModel<String>();
		guestQueueList = new JList<String>(listModel);
		listAndButtonPanel = new JPanel(); 
		guestLabel = new JLabel("Välj sällskap:"); 
		buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); 

		laggTillButton = new JButton("Lägg till i kö");
		laggTillButton.setActionCommand("Add");
		laggTillButton.addActionListener(controller);
		indoorImage.addMouseListener(controller);
		patioImage.addMouseListener(controller);
		removeButton = new JButton("Ta bort");
		removeButton.setActionCommand("Remove");
		removeButton.addActionListener(controller);
		removeButton.setEnabled(false);
		guestQueueList.addListSelectionListener(controller);
		
		patioLabel = new JLabel("Patio");
		insideLabel = new JLabel("Inomhus");
		
		patioPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		patioPanel.setLayout(new BorderLayout());
		patioPanel.setBackground(Color.white);
		insidePanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		insidePanel.setLayout(new BorderLayout());
		insidePanel.setBackground(Color.white);
		addPanel.setLayout(new BoxLayout(addPanel, BoxLayout.Y_AXIS));
		namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.PAGE_AXIS));
		sizePanel.setLayout(new BoxLayout(sizePanel, BoxLayout.PAGE_AXIS));
		bookPanel.setLayout(new BorderLayout());
		bookPanel.setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));
		listAndButtonPanel.setLayout(new BoxLayout(listAndButtonPanel, BoxLayout.PAGE_AXIS));
		
		mainPanel.setLayout(new GridLayout(1, 3));
		mainPanel.add(patioPanel);
		mainPanel.add(insidePanel);
		mainPanel.add(bookPanel);
		listAndButtonPanel.add(guestLabel);
		listAndButtonPanel.add(guestQueueList);
		listAndButtonPanel.add(Box.createVerticalStrut(padding));
		listAndButtonPanel.add(removeButton);
		bookPanel.add(listAndButtonPanel, BorderLayout.NORTH);
		bookPanel.add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.add(laggTillButton);
		
		patioLabel.setHorizontalAlignment(JLabel.CENTER);
		patioLabel.setFont(new Font("Arial", Font.BOLD, 30));
		patioPanel.add(patioLabel, BorderLayout.NORTH);
		patioPanel.add(patioImage, BorderLayout.CENTER);
		insideLabel.setHorizontalAlignment(JLabel.CENTER);
		insideLabel.setFont(new Font("Arial", Font.BOLD, 30));
		insidePanel.add(insideLabel, BorderLayout.NORTH);
		insidePanel.add(indoorImage, BorderLayout.CENTER);
		
		nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		name.setAlignmentX(Component.LEFT_ALIGNMENT);
		namePanel.add(nameLabel);
		namePanel.add(name);
		addPanel.add(namePanel);
		
		sizeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		size.setAlignmentX(Component.LEFT_ALIGNMENT);
		sizePanel.add(sizeLabel);
		sizePanel.add(size);
		addPanel.add(sizePanel);

		this.setLayout(new BorderLayout());
		this.add(mainPanel, BorderLayout.CENTER);
		
		this.setVisible(true);
		this.setSize(1200, 600);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	/**
	 * Metod som returnerar en dialogruta som centreras till this (framen), består av addPanel, har titeln "Lägg till i kö", har ok_cancel-val och plain-message (ingen ikon).
	 * Tömmer namn- och storleksfälten på text om dialogrutan öppnas på nytt. 
	 * @return
	 * En dialogruta. 
	 */
	public int addToQueue() {
		name.setText("");
		size.setText("");
		return JOptionPane.showConfirmDialog(this, addPanel, "Lägg till i kö", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	}

	/**
	 * Metod som retunerar texten i fältet för sällskapets namn. 
	 * @return
	 * Sällskapets namn.
	 */
	public String getGuestName() {
		return name.getText();
	}

	/**
	 * Metod som returnerar texten i fältet för sällskapets storlek. 
	 * @return
	 * Sällskapets storlek. 
	 */
	public String getGuestSize() {
		return size.getText();
	}

	/**
	 * Metod som returnerar en dialogruta som centreras till this (framen), består av bookPanel, har titeln "Boka från kö", har ok_cancel-val och plain-message (ingen ikon).
	 * @return
	 * En dialogruta.
	 */
	public int bookFromQueue() {
		return JOptionPane.showConfirmDialog(this, bookPanel, "Boka från kö", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	}

	/**
	 * Metod som lägger till ett sällskaps namn och storlek i listan guestQueueList. 
	 * En kontroll görs för om listan är skilt från null, om den redan finns och har skapats, och då töms den för att undvika dubletter. 
	 * @param guestQueue
	 * Kölistan med alla sällskap som står i kö till ett bord. 
	 */
	public void addGuestToList(ArrayList<String> guestQueue) {
		if(listModel != null) {
			listModel.clear();
			guestQueueList.removeAll();
		}
		for(int i = 0; i < guestQueue.size(); i++) {
			listModel.addElement(guestQueue.get(i) + ", " + guestQueue.get(i+=1) + " pers"); // Lägger till i = i + 1 för att få sällskapets namn och storlek på samma rad i listan. 
		}
		removeButton.setAlignmentX(Component.LEFT_ALIGNMENT);
		guestQueueList.setAlignmentX(Component.LEFT_ALIGNMENT);
		guestQueueList.setFixedCellWidth(400);
		guestQueueList.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION); // Kan bara välja ett val
	}
	
	/**
	 * Metod som retunerar det valda indexet i listan av sällskap. 
	 * @return
	 * Index i listan för sällskap.
	 */
	public int removeGuestFromList() {
		return guestQueueList.getSelectedIndex();
	}

	/**
	 * Metod som sätter knappen med texten "Lägg till i kö" till enabled.
	 */
	public void setQueueButtonEnabled() {
		laggTillButton.setEnabled(true);
	}

	/**
	 * Metod som sätter knappen med texten "Lägg till i kö" till disabled.
	 */
	public void setQueueButtonDisabled() {
		laggTillButton.setEnabled(false);
	}
	
	/**
	 * Metod som sätter knappen med texten "Ta bort" till disabled.
	 */
	public void setRemoveButtonDisabled() {
		removeButton.setEnabled(false);
	}
	
	/**
	 * Metod som sätter knappen med texten "Ta bort" till enabled. 
	 */
	public void setRemoveButtonEnabled() {
		removeButton.setEnabled(true);
	}
	
	/**
	 * Metod som anropas .repaint() för att rita om paintComponent. 
	 */
	public void repaintImage() {
		patioImage.repaint();
		indoorImage.repaint();
	}
	
	/**
	 * Metod som returnerar patioImage till controller.
	 * @return
	 * patioImage.
	 */
	public PatioImage getPatioImage() {
		return patioImage;
	}
	
	/**
	 * Metod som returnerar indoorImage till controller.
	 * @return
	 * indoorImage.
	 */
	public IndoorImage getIndoorImage() {
		return indoorImage;
	}

	/**
	 * Metod som returnerar en dialogruta där användaren får möjlighet att ångra borttagningen av ett sällskap i kölistan.
	 * @return
	 * En dialogruta.
	 */
	public int verifyRemoveDialog() {
		return JOptionPane.showConfirmDialog(this, "Är du säker på att du vill ta bort ett sällskap från kölistan?", "Ta bort", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
	}

	/**
	 * Metod som hämtar ut valt index i kölistan och tar bort valt index från listan. 
	 * @return
	 * Valt index.
	 */
	public int removeItemInList() {
		int selectedIndex = guestQueueList.getSelectedIndex();
		listModel.remove(selectedIndex);
		return selectedIndex;
	}
}
