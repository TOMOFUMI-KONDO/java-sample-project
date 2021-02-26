package enshu4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileInput {
	public FileInput(String fname) {
		try {
			//�t�@�C����ǂݍ���
			BufferedReader br = new BufferedReader(new FileReader(fname));
			
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
			br.close();
		} catch (FileNotFoundException e1) {
			System.out.println(fname + "���I�[�v���ł��܂���B");
			return;
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new FileInput("input.txt");
	}

}
