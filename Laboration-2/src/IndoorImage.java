import java.awt.*;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

//Skapat av Mateusz Weber och Felicia Strandberg.

public class IndoorImage extends JComponent {
	private Image indoorImage;
	private ArrayList<Table> tables;
	private Table table;

	public IndoorImage() {
		tables = new ArrayList<Table>();
		table = new Table(0, 0);
		tables.add(new Table(67, 412));
		tables.add(new Table(154, 412));
		tables.add(new Table(239, 412));
		tables.add(new Table(325, 412));
		tables.add(new Table(134, 332));
		tables.add(new Table(169, 332));
		tables.add(new Table(244, 311)); // Runda bordet
		tables.add(new Table(358, 316));
		tables.add(new Table(358, 231));
		tables.add(new Table(358, 160));

		try {
			Image raw = ImageIO.read(new File("indoor.jpg"));
			indoorImage = raw.getScaledInstance(raw.getWidth(null) / 2, raw.getHeight(null) / 2, Image.SCALE_SMOOTH);
		} catch(Exception e) {
			System.out.println("Fel");
		}
	}

	public ArrayList<Table> getTables() {
		return tables;
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

			graphics.drawRect(tables.get(0).getX(), tables.get(0).getY(), Table.WIDTH - 30, Table.HEIGHT - 10);
			graphics.drawRect(tables.get(1).getX(), tables.get(1).getY(), Table.WIDTH - 30, Table.HEIGHT - 10);
			graphics.drawRect(tables.get(2).getX(), tables.get(2).getY(), Table.WIDTH - 30, Table.HEIGHT - 10);
			graphics.drawRect(tables.get(3).getX(), tables.get(3).getY(), Table.WIDTH - 30, Table.HEIGHT - 10);
			graphics.drawRect(tables.get(4).getX(), tables.get(4).getY(), Table.WIDTH - 35, Table.HEIGHT - 30);
			graphics.drawRect(tables.get(5).getX(), tables.get(5).getY(), Table.WIDTH - 35, Table.HEIGHT - 30);
			graphics.drawOval(tables.get(6).getX() + 2, tables.get(6).getY() - 7, Table.WIDTH, Table.HEIGHT);
			graphics.drawRect(tables.get(7).getX(), tables.get(7).getY(), Table.WIDTH - 35, Table.HEIGHT - 30);
			graphics.drawRect(tables.get(8).getX(), tables.get(8).getY(), Table.WIDTH - 35, Table.HEIGHT - 30);
			graphics.drawRect(tables.get(9).getX(), tables.get(9).getY(), Table.WIDTH - 35, Table.HEIGHT - 30);
		}
	}
}
