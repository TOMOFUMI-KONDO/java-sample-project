//B8TB2108
//近藤智文

package enshu4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class CartReader {
	//カート内の各商品とその個数
	private HashMap<String, Integer> item;

	public CartReader(String fname) {
		item = new HashMap<String, Integer>();

		try {
			// ファイルを読み込み
			BufferedReader br = new BufferedReader(new FileReader(fname));

			String line;
			while ((line = br.readLine()) != null) {
				String[] splt = line.split(","); // 商品名と個数に分割
				String key = splt[0].trim(); // 商品名を代入
				Integer val = Integer.valueOf(splt[1].trim()); // 個数を代入

				// 既にカートにあるアイテムなら個数を追加
				if (item.containsKey(key)) {
					item.put(key, item.get(key) + val); //元の個数にvalの値を追加
				}
				// 新規アイテムを追加
				else {
					item.put(key, val); // 商品名と個数をitemプロパティに追加
				}
			}
			br.close();
		} catch (FileNotFoundException e1) {
			System.out.println(fname + "がオープンできません。");
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
