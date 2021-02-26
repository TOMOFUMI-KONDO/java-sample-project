// B8Tb2108
// 近藤智文

package enshu2;

public class MathCalc extends Calc {
	private double logBase = 10; // log演算の底。
	private boolean isRadian = true; // sin・cos計算の角度の単位。trueならラジアン、falseなら度。

	public void pow(double d) {
		super.val = Math.pow(super.val, d);
	}

	// 累乗の計算
	public void setLogBase(double d) {
		if (d <= 0 || d == 1) {
			System.out.println("Error");
		} else {
			logBase = d;
		}
	}

	// logの計算
	public void log() {
		if (super.val <= 0) {
			System.out.println("Error");
		} else {
			double log_base = Math.log(logBase);
			double log_val = Math.log(super.val);
			super.val = log_val / log_base;
		}
	}

	// 角度の単位をラジアンに設定
	public void setRadianMode() {
		isRadian = true;
	}

	// 角度の単位を度に設定
	public void setDegreeMode() {
		isRadian = false;
	}

	// sinの計算
	public void sin() {
		double angle = super.val;

		// 角度の単位が度だったら、ラジアンに変換。
		if (!isRadian) {
			angle *= Math.PI / 180;
		}

		super.val = Math.sin(angle);
	}

	// cosの計算
	public void cos() {
		double angle = super.val;

		// 角度の単位が度だったら、ラジアンに変換。
		if (!isRadian) {
			angle *= Math.PI / 180;
		}

		super.val = Math.cos(angle);
	}

	// override
	public void div(double d) {
		if (d == 0) {
			System.out.println("Error");
		} else {
			super.div(d);
		}
	}

	public static void main(String[] args) {
		MathCalc mc = new MathCalc();

		// sin(30度)
		mc.setDegreeMode();
		mc.set(30);
		mc.sin();
		mc.print(); // 0.5

		// sin(PI/2);
		mc.setRadianMode();
		mc.set(Math.PI);
		mc.div(2);
		mc.sin();
		mc.print(); // 1.0

		// log2(1024)
		mc.setLogBase(2);
		mc.set(1024);
		mc.log();
		mc.print(); // 10.0

		// log2(1024)^5
		mc.pow(5);
		mc.print(); // 100000.0

		// log10(log2(1024)^5)
		mc.setLogBase(10);
		mc.log();
		mc.print(); // 5.0

		// Error check
		mc.setLogBase(0); // Error
		mc.setLogBase(-1); // Error
		mc.setLogBase(1); // Error
		mc.div(0); // Error
		mc.set(0);
		mc.log(); // Error
		mc.set(-1);
		mc.log(); // Error

		// オーバーライドしたdivメソッドの確認
		mc.set(10);
		mc.div(2);
		mc.print(); // 5.0
	}
}


