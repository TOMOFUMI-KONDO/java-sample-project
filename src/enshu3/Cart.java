// B8Tb2108
// 近藤智文

package enshu3;

import java.util.Vector;

public class Cart {
	private Vector<Item> items = new Vector<Item>(); // カート内の商品
	private int budget; // 予算
	private int sum = 0; // 合計金額

	// コンストラクタ。予算を保存。
	public Cart(int budget) {
		this.budget = budget;
	}

	// カートに商品を追加するメソッド
	// 予算をオーバーする場合は追加しない
	public void add(String name, int price) {
		Item item = new Item(name, price);
		String s = "[ADD] " + item.toString();
		if (this.sum + price <= this.budget) {
			this.items.add(item);
			System.out.println(s + " OK");

			this.sum += price;
		} else {
			System.out.println(s + " Failed");
		}
	}

	// カートから商品を取り除くメソッド
	public void remove(String name, int price) {
		Item item = new Item(name, price);
		String s = "[REMOVE] " + item.toString();

		if (this.items.contains(item)) {
			this.items.remove(item);
			System.out.println(s + " OK");

			this.sum -= price;
		} else {
			System.out.println(s + " Not exist...");
		}
	}

	public void printPlan() {
		Vector<Item> countedItem = new Vector<Item>();
		for (Item item : this.items) {
			if (!countedItem.contains(item)) {
				int count = 0;

				for (Item i : this.items) {
					count += item.equals(i) ? 1 : 0;
				}
				System.out.println(item.toString() + " x" + count);

				countedItem.add(item);
			}
		}

		System.out.println("Sum = " + this.sum + "JPY");
	}

	public static void main(String[] args) {
		// 予算300円でプランを作成
		Cart sc = new Cart(300);

		// おやつを追加
		sc.add("Chocolate", 120);
		sc.add("Chips", 120);
		sc.add("PopCorn", 110); // ←予算オーバー

		// ChipsをCandyに変更
		sc.remove("Chips", 120);
		sc.add("Candy", 10);

		// 再びPopCornを追加
		sc.add("PopCorn", 110);

		// 買い物プランを表示
		System.out.println("---- Items ----");
		sc.printPlan();

		// チョコレートを削除
		sc.remove("Chocolate", 120);

		// Candyを3個追加
		sc.add("Candy", 10);
		sc.add("Candy", 10);
		sc.add("Candy", 10);

		// PopCornを追加
		sc.add("PopCorn", 110);

		// 買い物プランを表示
		System.out.println("---- Items ----");
		sc.printPlan();
	}

}
