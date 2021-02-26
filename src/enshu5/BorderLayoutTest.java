package enshu5;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class BorderLayoutTest {
	public BorderLayoutTest() {
		JFrame jf = new JFrame("Border Layout Test");
		jf.setLayout(new BorderLayout());

		JPanel addressBar = new JPanel();
		JTextArea ta = new JTextArea("�{���p�e�L�X�g�G���A");
		JLabel statusBar = new JLabel("�X�e�[�^�X�o�[");

		jf.add(addressBar, BorderLayout.NORTH);
		jf.add(ta, BorderLayout.CENTER);
		jf.add(statusBar, BorderLayout.SOUTH);

		JPanel navBar = new JPanel();
		JTextField addressField = new JTextField("�A�h���X����");
		addressBar.setLayout(new BorderLayout());
		addressBar.add(navBar, BorderLayout.WEST);
		addressBar.add(addressField, BorderLayout.CENTER);

		JButton back = new JButton("�߂�");
		JButton fwd = new JButton("�i��");
		JButton reload = new JButton("�ēǂݍ���");
		navBar.setLayout(new BorderLayout());
		navBar.add(back, BorderLayout.WEST);
		navBar.add(fwd, BorderLayout.CENTER);
		navBar.add(reload, BorderLayout.EAST);

		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(320, 240);
		jf.setVisible(true);
	}

	public static void main(String[] args) {
		new BorderLayoutTest();
	}

}
