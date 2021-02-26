// B8Tb2108
// �ߓ��q��

package enshu3;

import java.util.Vector;

public class Cart {
	private Vector<Item> items = new Vector<Item>(); // �J�[�g���̏��i
	private int budget; // �\�Z
	private int sum = 0; // ���v���z

	// �R���X�g���N�^�B�\�Z��ۑ��B
	public Cart(int budget) {
		this.budget = budget;
	}

	// �J�[�g�ɏ��i��ǉ����郁�\�b�h
	// �\�Z���I�[�o�[����ꍇ�͒ǉ����Ȃ�
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

	// �J�[�g���珤�i����菜�����\�b�h
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
		// �\�Z300�~�Ńv�������쐬
		Cart sc = new Cart(300);

		// �����ǉ�
		sc.add("Chocolate", 120);
		sc.add("Chips", 120);
		sc.add("PopCorn", 110); // ���\�Z�I�[�o�[

		// Chips��Candy�ɕύX
		sc.remove("Chips", 120);
		sc.add("Candy", 10);

		// �Ă�PopCorn��ǉ�
		sc.add("PopCorn", 110);

		// �������v������\��
		System.out.println("---- Items ----");
		sc.printPlan();

		// �`���R���[�g���폜
		sc.remove("Chocolate", 120);

		// Candy��3�ǉ�
		sc.add("Candy", 10);
		sc.add("Candy", 10);
		sc.add("Candy", 10);

		// PopCorn��ǉ�
		sc.add("PopCorn", 110);

		// �������v������\��
		System.out.println("---- Items ----");
		sc.printPlan();
	}

}
