import java.awt.*;
import javax.swing.*;
import java.lang.*;
import java.util.ArrayList;

// Skapat av Mateusz Weber och Felicia Strandberg.

public class Vy extends JFrame {
	private JButton laggTillButton, bokaFranKoButton, removeButton;
	private JPanel patioPanel, insidePanel, mainPanel, infoPanel, addPanel, namePanel, sizePanel, bookPanel, listAndButtonPanel;
	private JLabel patioLabel, insideLabel, nameLabel, sizeLabel, guestLabel, tableLabel;
	private Controller controller;
	private PatioImage patioImage;
	private IndoorImage indoorImage;
	private final int padding = 10;
	private JTextField name;
	private JTextField size;
	private DefaultListModel<String> listModel;
	private JList<String> guestQueueList;
	private JComboBox<Integer> availableTables;
	
	// Lägg till så att man kan ta bort från kölistan + varning om man vill ta bort
	// Styla kölistvyn
	
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
		infoPanel = new JPanel();
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
		availableTables = new JComboBox<Integer>();
		listAndButtonPanel = new JPanel(); 
		guestLabel = new JLabel("Välj sällskap:"); 
		tableLabel = new JLabel("Välj bord:");

		laggTillButton = new JButton("Lägg till i kö");
		laggTillButton.setActionCommand("Add");
		laggTillButton.addActionListener(controller);
		bokaFranKoButton = new JButton("Boka från kö");
		bokaFranKoButton.setActionCommand("Boka");
		bokaFranKoButton.addActionListener(controller);
		indoorImage.addMouseListener(controller);
		patioImage.addMouseListener(controller);
		removeButton = new JButton("Ta bort");
		removeButton.setActionCommand("Remove");
		removeButton.addActionListener(controller);
		
		bokaFranKoButton.setEnabled(false);
		laggTillButton.setEnabled(false);
		
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
		bookPanel.setLayout(new BoxLayout(bookPanel, BoxLayout.PAGE_AXIS));
		listAndButtonPanel.setLayout(new BoxLayout(listAndButtonPanel, BoxLayout.PAGE_AXIS));
		
		mainPanel.setLayout(new GridLayout(1, 2));
		mainPanel.add(patioPanel);
		mainPanel.add(insidePanel);
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
		this.add(infoPanel, BorderLayout.SOUTH);
		
		infoPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
		infoPanel.add(laggTillButton);
		infoPanel.add(bokaFranKoButton);
		infoPanel.setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));
		
		this.setVisible(true);
		this.setSize(800, 600);
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
	 * Metod som tar bort alla items i availablesTables. 
	 * Anropas för att inte få dubletter om den öppnas flera gånger. 
	 */
	public void removeTableFromList() {
		availableTables.removeAllItems();
	}

	/**
	 * Metod som lägger till bord i comboboxen. 
	 * @param index
	 * Index är det bord som är ledigt som ska in i listan. 
	 */
	public void addTableToList(int index) {
		availableTables.addItem(index);
		bookPanel.add(Box.createVerticalStrut(padding));
		bookPanel.add(tableLabel);
		tableLabel.setAlignmentX(LEFT_ALIGNMENT);
		bookPanel.add(availableTables);
		availableTables.setAlignmentX(LEFT_ALIGNMENT);
	}
	
	/**
	 * Metod som returnerar det valda itemet i comboboxen.
	 * Parsar om det valde itemet till en sträng och sedan till en int. 
	 * @return
	 * Valt item. 
	 */
	public int getTableFromList() {
		if(availableTables.getSelectedItem() != null) {
			String s = availableTables.getSelectedItem().toString();
			int selectedTable = Integer.parseInt(s);
			return selectedTable;
		} else {
			return 0;
		}
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
		listAndButtonPanel.add(guestLabel);
		listAndButtonPanel.add(guestQueueList);
		listAndButtonPanel.add(Box.createVerticalStrut(padding));
		listAndButtonPanel.add(removeButton);
		bookPanel.add(listAndButtonPanel);
		removeButton.setAlignmentX(Component.LEFT_ALIGNMENT);
		guestQueueList.setAlignmentX(Component.LEFT_ALIGNMENT);
		guestQueueList.setFixedCellWidth(350);
		guestQueueList.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION); // Kan bara välja ett val
		guestQueueList.setSelectedIndex(0); // Sätter selectedIndex till 0 för att alltid ha ett val när listan öppnas
		
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
	 * Metod som sätter knappen med texten "Boka från kö" till enabled.
	 */
	public void setBookButtonToEnabled() {
		bokaFranKoButton.setEnabled(true);
	}

	/**
	 * Metod som sätter knappen med texten "Boka från kö" till disabled.
	 */
	public void setBookButtonToDisabled() {
		bokaFranKoButton.setEnabled(false);
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

	public int verifyRemoveDialog() {
		return JOptionPane.showConfirmDialog(this, "Är du säker på detta?", "Ta bort", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
		
	}
}
