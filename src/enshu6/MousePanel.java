package enshu6;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class MousePanel extends JPanel {
	private double cx = 0, cy = 0;

	public MousePanel() {
		super();

		this.setPreferredSize(new Dimension(640, 480));

		this.addMouseListener(new MouseListener() {			
			public void mouseClicked(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
				Point mp = e.getPoint();
				Rectangle r = getBounds();

				System.out.println("mp = " + mp);
				System.out.println("r = " + r);

				if (r.width != 0 && r.height != 0) {
					cx = (double) mp.x / (double) r.width;
					cy = (double) mp.y / (double) r.height;
					System.out.printf("(cx, cy) = (%.2f, %.2f)\n\n", cx, cy);

					repaint();
				}
			}

			public void mouseReleased(MouseEvent e) {
			}
		});
	}

	public void paint(Graphics g) {
		Rectangle r = this.getBounds();
		g.setColor(Color.white);
		g.fillRect(r.x, r.y, r.width, r.height);

		g.setColor(Color.blue);
		int x = (int) (r.width * cx);
		int y = (int) (r.height * cy);
		g.fillOval(x - 50, y - 50, 100, 100);
	}
}
