package enshu3;

public class Item {
	protected String name; // ���i��
	protected int price; // �l�i

	public Item(String name, int price) {
		this.name = name;
		this.price = price;
	}

	// Override
	public boolean equals(Object o) {
		Item i = (Item) o;
		return this.name.equals(i.name) && this.price == i.price;
	}

	// Override
	public String toString() {
		return name + "(" + price + "JPY)";
	}

	public void print() {
		System.out.println(toString());
	}
}
