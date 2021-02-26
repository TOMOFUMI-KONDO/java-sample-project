package enshu5;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ActionListenerTest {
	public ActionListenerTest() {
		JFrame jf = new JFrame("Button Test");
		jf.setLayout(new BorderLayout());
		
		JButton btn = new JButton("ƒ{ƒ^ƒ“");
		jf.add(btn, BorderLayout.CENTER);
		
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("‚Û‚¿‚Á‚Æ‚È");
			}
		});


		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(320, 240);
		jf.setVisible(true);
	}

	public static void main(String[] args) {
		new ActionListenerTest();
	}

}
