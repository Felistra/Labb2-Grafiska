import java.awt.*;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

public class IndoorImage extends JComponent {
	private Image indoorImage;
	private ArrayList<Table> tables;
	
	public IndoorImage() {
		tables = new ArrayList<Table>();
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
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D graphics = (Graphics2D)g;
		graphics.drawImage(indoorImage, 0, 135, null);
		graphics.drawOval(tables.get(6).getX(), tables.get(6).getY(), Table.WIDTH, Table.HEIGHT);
		
		for(Table table : tables) {
			if(table.isActivated()) {
				graphics.setColor(new Color(80, 230, 80));
			} else {
				graphics.setColor(new Color(130, 130, 230));
				graphics.setStroke(new BasicStroke(6));
				graphics.drawString("", table.getX() + 10, table.getY() + Table.HEIGHT / 2);
				graphics.drawRect(table.getX(), table.getY(), Table.WIDTH - 30, Table.HEIGHT - 10);
			}
		}
	}
}
