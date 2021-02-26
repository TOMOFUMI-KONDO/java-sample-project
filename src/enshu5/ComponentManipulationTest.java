package enshu5;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ComponentManipulationTest {
	private JTextField tf = null;
	private JTextArea ta = null;

	public ComponentManipulationTest() {
		JFrame jf = new JFrame("Button Test");
		jf.setLayout(new BorderLayout());

		tf = new JTextField();
		ta = new JTextArea();
		JButton btn = new JButton("’Ç‰Á");
		jf.add(tf, BorderLayout.NORTH);
		jf.add(new JScrollPane(ta), BorderLayout.CENTER);
		jf.add(btn, BorderLayout.SOUTH);

		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ta.append(tf.getText() + "\n");
			}
		});
		

		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(320, 240);
		jf.setVisible(true);
	}

	public static void main(String[] args) {
		new ComponentManipulationTest();
	}

}
