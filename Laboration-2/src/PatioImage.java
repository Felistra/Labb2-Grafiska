import java.awt.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;

//Skapat av Mateusz Weber och Felicia Strandberg.

public class PatioImage extends JComponent {
	private Image patioImage;
	private ArrayList<Table> patioTables;
	
	public PatioImage() {
		patioTables = new ArrayList<Table>();
		patioTables.add(new Table(134, 32));
		patioTables.add(new Table(141, 130));
		patioTables.add(new Table(192, 202));
		patioTables.add(new Table(141, 268));
		patioTables.add(new Table(192, 336));
		patioTables.add(new Table(140, 402));
		
		try {
			Image raw = ImageIO.read(new File("patio.jpg"));
			patioImage = raw.getScaledInstance(raw.getWidth(null) / 2, raw.getHeight(null) / 2, Image.SCALE_SMOOTH);
		} catch(Exception e) {
			System.out.println("Fel");
		}
	}
	public ArrayList<Table> getTables() {
		return patioTables;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D graphics = (Graphics2D)g;
		graphics.drawImage(patioImage, 120, 20, null);
		
		for(Table table : patioTables) {
			if(table.isActivated()) {
				graphics.setColor(new Color(255, 128, 15));
			} else {
				graphics.setColor(new Color(1, 107, 165));
			}
			graphics.setStroke(new BasicStroke(6));
			graphics.drawString("", table.getX() + 10, table.getY() + Table.HEIGHT / 2);
			graphics.drawOval(table.getX(), table.getY(), Table.WIDTH - 10, Table.HEIGHT - 10);
			
			// System.out.println("Table activated: " + table.isActivated()); 
		}
	}
}
