package enshu6;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class OthelloPanelTest {
	public OthelloPanelTest() {
		JFrame jf = new JFrame("Othello");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLayout(new BorderLayout());
		
		jf.add(new OthelloPanel(), BorderLayout.CENTER);
		
		jf.pack();
		jf.setVisible(true);
	}

	public static void main(String[] args) {
		new OthelloPanelTest();
	}

}
