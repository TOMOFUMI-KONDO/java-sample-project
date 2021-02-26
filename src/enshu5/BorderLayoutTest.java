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
		JTextArea ta = new JTextArea("本文用テキストエリア");
		JLabel statusBar = new JLabel("ステータスバー");

		jf.add(addressBar, BorderLayout.NORTH);
		jf.add(ta, BorderLayout.CENTER);
		jf.add(statusBar, BorderLayout.SOUTH);

		JPanel navBar = new JPanel();
		JTextField addressField = new JTextField("アドレス入力");
		addressBar.setLayout(new BorderLayout());
		addressBar.add(navBar, BorderLayout.WEST);
		addressBar.add(addressField, BorderLayout.CENTER);

		JButton back = new JButton("戻る");
		JButton fwd = new JButton("進む");
		JButton reload = new JButton("再読み込み");
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
