package enshu2;

public class Calc {
	protected double val = 0;
	
	public void set(double d) {
		val = d;
	}
	
	public void add(double d) {
		val += d;
	}
	
	public void sub(double d) {
		val -= d;
	}
	
	public void mul(double d) {
		val *= d;
	}
	
	public void div(double d) {
		val /= d;
	}
	
	public void print() {
		System.out.println("Value=" + val);
	}
}
