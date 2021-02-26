package enshu5;

import javax.swing.JFrame;

public class BlankWindow {
	public BlankWindow() {
		JFrame jf = new JFrame("Blank window");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		jf.setSize(320, 480);
		jf.setVisible(true);	
	}
			
	public static void main(String[] args) {
		new BlankWindow();
	}

}
