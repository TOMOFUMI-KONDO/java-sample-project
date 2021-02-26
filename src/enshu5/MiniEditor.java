//B8TB2108
//�ߓ��q��

package enshu5;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MiniEditor {
	private JFrame frame = null;
	private JPanel appBar = null;
	private JLabel label = null;
	private JPanel buttonBar = null;
	private JButton loadButton = null;
	private JButton saveButton = null;
	private JTextField textField = null;
	private JScrollPane scrollPane = null;
	private JTextArea textArea = null;

	public MiniEditor() {
		frame = new JFrame("MiniEditor");
		frame.setLayout(new BorderLayout());

		appBar = new JPanel();
		textArea = new JTextArea("�{���p�e�L�X�g�G���A");
		scrollPane = new JScrollPane(textArea);

		frame.add(appBar, BorderLayout.NORTH);
		frame.add(scrollPane, BorderLayout.CENTER);

		label = new JLabel("File:");
		textField = new JTextField("�t�@�C���������Ă�������");
		buttonBar = new JPanel();

		appBar.setLayout(new BorderLayout());
		appBar.add(label, BorderLayout.WEST);
		appBar.add(textField, BorderLayout.CENTER);
		appBar.add(buttonBar, BorderLayout.EAST);

		loadButton = new JButton("Load");
		saveButton = new JButton("Save");

		buttonBar.setLayout(new BorderLayout());
		buttonBar.add(loadButton, BorderLayout.WEST);
		buttonBar.add(saveButton, BorderLayout.EAST);

		loadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = readFile(textField.getText());
				if (text != null) {
					textArea.setText(text + "\n");
				}
			}
		});

		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String fileName = textField.getText();
				String text = textArea.getText();
				writeFile(fileName, text);
			}
		});

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(640, 480);
		frame.setVisible(true);
	}

	// �t�@�C���̓��e�����s���܂߂Ď擾����֐�
	private String readFile(String fileName) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));

			String text = "";
			String line;
			while ((line = reader.readLine()) != null) {
				if (text == "") {
					text = line;
				} else {
					text = text + "\n" + line;
				}

			}
			reader.close();

			return text;
		} catch (FileNotFoundException e1) {
			System.out.println("�u" + fileName + "�v" + "��������܂���B");
			return null;
		} catch (IOException e2) {
			e2.printStackTrace();
			return null;
		}
	}

	//�e�L�X�g�G���A�̓��e���t�@�C���ɏ������ފ֐�
	private void writeFile(String fileName, String text) {
		try {
			PrintWriter writer = new PrintWriter(new FileWriter(fileName));
			writer.print(text);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new MiniEditor();
	}
}
