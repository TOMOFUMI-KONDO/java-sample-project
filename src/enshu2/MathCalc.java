// B8Tb2108
// �ߓ��q��

package enshu2;

public class MathCalc extends Calc {
	private double logBase = 10; // log���Z�̒�B
	private boolean isRadian = true; // sin�Ecos�v�Z�̊p�x�̒P�ʁBtrue�Ȃ烉�W�A���Afalse�Ȃ�x�B

	public void pow(double d) {
		super.val = Math.pow(super.val, d);
	}

	// �ݏ�̌v�Z
	public void setLogBase(double d) {
		if (d <= 0 || d == 1) {
			System.out.println("Error");
		} else {
			logBase = d;
		}
	}

	// log�̌v�Z
	public void log() {
		if (super.val <= 0) {
			System.out.println("Error");
		} else {
			double log_base = Math.log(logBase);
			double log_val = Math.log(super.val);
			super.val = log_val / log_base;
		}
	}

	// �p�x�̒P�ʂ����W�A���ɐݒ�
	public void setRadianMode() {
		isRadian = true;
	}

	// �p�x�̒P�ʂ�x�ɐݒ�
	public void setDegreeMode() {
		isRadian = false;
	}

	// sin�̌v�Z
	public void sin() {
		double angle = super.val;

		// �p�x�̒P�ʂ��x��������A���W�A���ɕϊ��B
		if (!isRadian) {
			angle *= Math.PI / 180;
		}

		super.val = Math.sin(angle);
	}

	// cos�̌v�Z
	public void cos() {
		double angle = super.val;

		// �p�x�̒P�ʂ��x��������A���W�A���ɕϊ��B
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

		// sin(30�x)
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

		// �I�[�o�[���C�h����div���\�b�h�̊m�F
		mc.set(10);
		mc.div(2);
		mc.print(); // 5.0
	}
}


