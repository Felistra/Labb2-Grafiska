import java.awt.*;
import javax.swing.*;
import java.lang.*;
import java.util.ArrayList;

public class Vy extends JFrame {
	private JButton bokaButton, avbokaButton, laggTillButton, bokaFranKoButton;
	private JPanel patioPanel, insidePanel, mainPanel, infoPanel, addPanel, namePanel, sizePanel, bookPanel;
	private JLabel patioLabel, insideLabel, queueLabel, nameLabel, sizeLabel;
	private Controller controller;
	private PatioImage patioImage;
	private IndoorImage indoorImage;
	private final int padding = 10;
	private JTextField name;
	private JTextField size;
	private DefaultListModel<String> listModel;
	private JList<String> guestQueueList;
	private JComboBox<Integer> availableTables;
	
	public Vy() {
		controller = new Controller(this);
		patioImage = new PatioImage();
		indoorImage = new IndoorImage();
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

		laggTillButton = new JButton("Lägg till i kö");
		laggTillButton.setActionCommand("Add");
		laggTillButton.addActionListener(controller);
		bokaFranKoButton = new JButton("Boka från kö");
		bokaFranKoButton.setActionCommand("Boka");
		bokaFranKoButton.addActionListener(controller);
		indoorImage.addMouseListener(controller);
		patioImage.addMouseListener(controller);
		
		bokaFranKoButton.setEnabled(false);
		laggTillButton.setEnabled(false);
		
		patioLabel = new JLabel("Patio");
		insideLabel = new JLabel("Inomhus");
		queueLabel = new JLabel("Antal i kö:");
		
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
		guestQueueList.setAlignmentX(Component.LEFT_ALIGNMENT);
		
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
		infoPanel.add(queueLabel);
		infoPanel.setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));
		
		this.setVisible(true);
		this.setSize(800, 600);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	// Bytte till int för att kolla resultatet i controller
	public int addToQueue() {
		name.setText("");
		size.setText("");
		return JOptionPane.showConfirmDialog(this, addPanel, "Lägg till i kö", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	}

	public String getGuestName() {
		return name.getText();
	}

	public String getGuestSize() {
		return size.getText();
	}

	public int bookFromQueue() {
		return JOptionPane.showConfirmDialog(this, bookPanel, "Boka från kö", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	}
	
	public void removeTableFromList() {
		availableTables.removeAllItems();
	}

	public void addTableToList(int index) {
		availableTables.addItem(index);
		bookPanel.add(availableTables);
	}
	
	public int getTableFromList() {
		return availableTables.getSelectedIndex();
	}

	public void addGuestToList(ArrayList<String> guestQueue) {
		if(listModel != null) {
			listModel.clear();
			guestQueueList.removeAll();
		}
		for(int i = 0; i < guestQueue.size(); i++) {
			listModel.addElement(guestQueue.get(i) + ", " + guestQueue.get(i+=1) + " pers");
		}
		bookPanel.add(guestQueueList);
	}
	
	public int removeGuestFromList() {
		return guestQueueList.getSelectedIndex();
	}

	public void setBookButtonToEnabled() {
		bokaFranKoButton.setEnabled(true);
	}

	public void setBookButtonToDisabled() {
		bokaFranKoButton.setEnabled(false);
	}

	public void setQueueButtonEnabled() {
		laggTillButton.setEnabled(true);
	}

	public void setQueueButtonDisabled() {
		laggTillButton.setEnabled(false);
	}
}
