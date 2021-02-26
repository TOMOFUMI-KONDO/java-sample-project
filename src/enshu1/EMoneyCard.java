//B8TB2108
//‹ß“¡’q•¶

package enshu1;

public class EMoneyCard {
	
	private int balance;
	private String name;
	
	public EMoneyCard(String n) {
		name = n;
		balance = 0;
	}
	
	public void charge(int b) {
		balance += b;
	}
	
	public boolean pay(int b) {
		if (balance >= b) {
			balance -= b;
			return true;
		} else {
			return false;
		}
	}
	
	public boolean transferFrom(EMoneyCard emc, int b) {
		if (emc.pay(b)) {
			balance += b;
			return true;
		} else {
			return false;
		}
	}
	
	public void print() {
		System.out.println(name + ":" + balance);
	}

	public static void main(String[] args) {
		EMoneyCard emc1 = new EMoneyCard("Card1");
		EMoneyCard emc2 = new EMoneyCard("Card2");
		emc1.charge(100);
		emc2.charge(100);
		emc1.print();
		emc2.print();
		if (emc1.transferFrom(emc2, 300)) {
			System.out.println("Success");
		} else {
			System.out.println("Fail");
		}
		emc2.charge(1000);
		emc1.print();
		emc2.print();
		if (emc1.transferFrom(emc2, 300)) {
			System.out.println("Success");
		} else {
			System.out.println("Fail");
		}
		emc1.print();
		emc2.print();
	}

}
