package enshu2;

public class MobilePhone extends Phone {
	private String email;

	public MobilePhone(String num, String em) {
		super(num);
		email = em;
	}

	public void printEmail() {
		System.out.println("E-mail:" + email);
	}

	// override
	public void print() {
		super.print();
		printEmail();
	}
}
