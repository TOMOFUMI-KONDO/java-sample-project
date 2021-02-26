package enshu2;

public class SmartPhone extends MobilePhone {
	private String owner;

	public SmartPhone(String num, String em, String o) {
		super(num, em);
		owner = o;
	}

	public void printOwner() {
		System.out.println("Owner:" + owner);
	}

	// override
	public void print() {
		super.print();
		printOwner();
	}

	public static void main(String[] args) {
		Phone p = new SmartPhone("080-9251-4734", "hoge@example.com", "tomokon");
		p.print();
	}

}
