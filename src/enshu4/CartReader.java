//B8TB2108
//�ߓ��q��

package enshu4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class CartReader {
	//�J�[�g���̊e���i�Ƃ��̌�
	private HashMap<String, Integer> item;

	public CartReader(String fname) {
		item = new HashMap<String, Integer>();

		try {
			// �t�@�C����ǂݍ���
			BufferedReader br = new BufferedReader(new FileReader(fname));

			String line;
			while ((line = br.readLine()) != null) {
				String[] splt = line.split(","); // ���i���ƌ��ɕ���
				String key = splt[0].trim(); // ���i������
				Integer val = Integer.valueOf(splt[1].trim()); // ������

				// ���ɃJ�[�g�ɂ���A�C�e���Ȃ����ǉ�
				if (item.containsKey(key)) {
					item.put(key, item.get(key) + val); //���̌���val�̒l��ǉ�
				}
				// �V�K�A�C�e����ǉ�
				else {
					item.put(key, val); // ���i���ƌ���item�v���p�e�B�ɒǉ�
				}
			}
			br.close();
		} catch (FileNotFoundException e1) {
			System.out.println(fname + "���I�[�v���ł��܂���B");
			return;
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}

	public void printAll() {
		for (String k : item.keySet()) {
			System.out.println(k + " x" + item.get(k));
		}
	}

	public static void main(String[] args) {
		CartReader cr = new CartReader("input.txt");
		cr.printAll();
	}

}
