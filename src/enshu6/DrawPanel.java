package enshu6;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JPanel;

public class DrawPanel extends JPanel {
	public DrawPanel() {
		super();
		this.setPreferredSize(new Dimension(640, 480));
	}

	public void paint(Graphics g) {
		System.out.println("GoPaint!");

		Rectangle r = this.getBounds();
		g.setColor(Color.white);
		g.fillRect(r.x, r.y, r.width, r.height);

		g.setColor(Color.blue);
		for (int i = 0; i <= 300; i += 10) {
			g.drawLine(0, 0, i, 100);
		}

		g.setColor(Color.red);
		g.drawRect(0, 100, 300, 50);
		g.fillRect(0, 150, 300, 50);

		g.setColor(Color.green);
		g.drawOval(0, 200, 300, 50);
		g.fillOval(0, 250, 300, 50);

		g.setColor(new Color(0, 128, 255));
		Font f = new Font("", Font.PLAIN, 50);
		g.setFont(f);
		g.drawString("String•¶Žš", 100, 100);
	}
}
