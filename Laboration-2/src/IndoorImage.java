import java.awt.*;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

//Skapat av Mateusz Weber och Felicia Strandberg.

public class IndoorImage extends JComponent {
	private Image indoorImage;
	private ArrayList<Table> indoorTables;
	private Table table;

	public IndoorImage() {
		indoorTables = new ArrayList<Table>();
		table = new Table(0, 0);
		indoorTables.add(new Table(67, 412));
		indoorTables.add(new Table(154, 412));
		indoorTables.add(new Table(239, 412));
		indoorTables.add(new Table(325, 412));
		indoorTables.add(new Table(134, 332));
		indoorTables.add(new Table(169, 332));
		indoorTables.add(new Table(244, 301)); // Runda bordet
		indoorTables.add(new Table(358, 316));
		indoorTables.add(new Table(358, 231));
		indoorTables.add(new Table(358, 160));

		try {
			Image raw = ImageIO.read(new File("indoor.jpg"));
			indoorImage = raw.getScaledInstance(raw.getWidth(null) / 2, raw.getHeight(null) / 2, Image.SCALE_SMOOTH);
		} catch(Exception e) {
			System.out.println("Fel");
		}
	}

	public ArrayList<Table> getTables() {
		return indoorTables;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D graphics = (Graphics2D)g;
		graphics.drawImage(indoorImage, 0, 135, null);
		
		if(table.isActivated()) {
			graphics.setColor(new Color(249, 1, 1));
		}
		else {
			graphics.setColor(new Color(54, 255, 0, 255));
			graphics.setStroke(new BasicStroke(6));
			graphics.drawString("", table.getX() + 10, table.getY() + Table.HEIGHT / 2);
		}
		
		graphics.drawRect(indoorTables.get(0).getX(), indoorTables.get(0).getY(), Table.WIDTH - 30, Table.HEIGHT - 10);
		graphics.drawRect(indoorTables.get(1).getX(), indoorTables.get(1).getY(), Table.WIDTH - 30, Table.HEIGHT - 10);
		graphics.drawRect(indoorTables.get(2).getX(), indoorTables.get(2).getY(), Table.WIDTH - 30, Table.HEIGHT - 10);
		graphics.drawRect(indoorTables.get(3).getX(), indoorTables.get(3).getY(), Table.WIDTH - 30, Table.HEIGHT - 10);
		graphics.drawRect(indoorTables.get(4).getX(), indoorTables.get(4).getY(), Table.WIDTH - 35, Table.HEIGHT - 30);
		graphics.drawRect(indoorTables.get(5).getX(), indoorTables.get(5).getY(), Table.WIDTH - 35, Table.HEIGHT - 30);
		graphics.drawOval(indoorTables.get(6).getX(), indoorTables.get(6).getY(), Table.WIDTH + 5, Table.HEIGHT + 5);
		graphics.drawRect(indoorTables.get(7).getX(), indoorTables.get(7).getY(), Table.WIDTH - 35, Table.HEIGHT - 30);
		graphics.drawRect(indoorTables.get(8).getX(), indoorTables.get(8).getY(), Table.WIDTH - 35, Table.HEIGHT - 30);
		graphics.drawRect(indoorTables.get(9).getX(), indoorTables.get(9).getY(), Table.WIDTH - 35, Table.HEIGHT - 30);
		
		for(int i = 0; i < 10; i++) {
			// System.out.println("Table activated: " + table.isActivated());
		}
	}
}
