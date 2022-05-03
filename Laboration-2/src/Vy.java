import java.awt.*;
import javax.swing.*;

public class Vy extends JFrame {
	private JButton bokaButton, avbokaButton, laggTillButton, bokaFranKoButton;
	private JPanel patioPanel, insidePanel, mainPanel, infoPanel;
	private JLabel patioLabel, insideLabel, queueLabel;
	private Controller controller;
	private PatioImage patioImage;
	private IndoorImage indoorImage;
	private final int padding = 10;
	
	public Vy() {
		controller = new Controller(this);
		patioImage = new PatioImage();
		patioImage.addMouseListener(controller);
		indoorImage = new IndoorImage(); 
		indoorImage.addMouseListener(controller);
		patioPanel = new JPanel(); 
		insidePanel = new JPanel();
		mainPanel = new JPanel(); 
		infoPanel = new JPanel();
		
		bokaButton = new JButton("Boka");
		avbokaButton = new JButton("Avboka");
		laggTillButton = new JButton("Lägg till i kö");
		bokaFranKoButton = new JButton("Boka från kö");
		
		bokaButton.setEnabled(false);
		avbokaButton.setEnabled(false);
		bokaFranKoButton.setEnabled(false);
		
		patioLabel = new JLabel("Patio");
		insideLabel = new JLabel("Inomhus");
		queueLabel = new JLabel("Antal i kö:");
		
		patioPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		patioPanel.setLayout(new BorderLayout());
		patioPanel.setBackground(Color.white);
		insidePanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		insidePanel.setLayout(new BorderLayout());
		insidePanel.setBackground(Color.white);
		
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
		
		this.setLayout(new BorderLayout());
		this.add(mainPanel, BorderLayout.CENTER);
		this.add(infoPanel, BorderLayout.SOUTH);
		
		infoPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
		infoPanel.add(bokaButton);
		infoPanel.add(avbokaButton);
		infoPanel.add(laggTillButton);
		infoPanel.add(bokaFranKoButton);
		infoPanel.add(queueLabel);
		infoPanel.setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));
		// Lägg in padding på knapparna
		
		this.setVisible(true);
		this.setSize(800, 600);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
