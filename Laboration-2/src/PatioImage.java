import java.awt.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;

public class PatioImage extends JComponent {
	private Image patioImage;
	private ArrayList<Table> tables;
	
	public PatioImage() {
		tables = new ArrayList<Table>();
		tables.add(new Table(134, 32));
		tables.add(new Table(141, 130));
		tables.add(new Table(192, 202));
		tables.add(new Table(141, 268));
		tables.add(new Table(192, 336));
		tables.add(new Table(140, 402));
		try {
			Image raw = ImageIO.read(new File("patio.jpg"));
			patioImage = raw.getScaledInstance(raw.getWidth(null) / 2, raw.getHeight(null) / 2, Image.SCALE_SMOOTH);
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
		graphics.drawImage(patioImage, 120, 20, null);
		
		for(Table table : tables) {
			if(table.isActivated()) {
				graphics.setColor(new Color(230, 8, 8));
			} else {
				graphics.setColor(new Color(72, 247, 4));
				graphics.setStroke(new BasicStroke(6));
				graphics.drawString("", table.getX() + 10, table.getY() + Table.HEIGHT / 2);
				graphics.drawOval(table.getX(), table.getY(), Table.WIDTH - 10, Table.HEIGHT - 10);
			}
		}
	}
}
