package enshu6;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class MousePanelTest {
	public MousePanelTest() {
		JFrame jf = new JFrame("Mouse Panel Test");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLayout(new BorderLayout());

		jf.add(new MousePanel(), BorderLayout.CENTER);

		jf.pack();
		jf.setVisible(true);
	}

	public static void main(String[] args) {
		new MousePanelTest();
	}

}
