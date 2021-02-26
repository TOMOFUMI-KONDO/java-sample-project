//B8TB2108
//�ߓ��q��

package enshu6;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JPanel;

public class OthelloPanel extends JPanel {
	private int size = 8; // �I�Z���Ղ̃T�C�Y
	private int[][] stones;// �z�u���ꂽ�΂̏��
	private int turn = 1; // 1�Ȃ獕�ԁA-1�Ȃ甒��

	public OthelloPanel() {
		super();

		this.setPreferredSize(new Dimension(600, 600));

		// this.stones��������
		this.stones = new int[this.size][this.size];
		for (int i = 0; i < this.size; i++) {
			for (int j = 0; j < this.size; j++) {
				this.stones[i][j] = 0;
			}
		}

		this.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
				Point mp = e.getPoint();
				Rectangle r = getBounds();

				// �c�����ꂼ�ꉽ�}�X�ڂ��N���b�N���ꂽ�����߂�
				double x = Math.floor((double) mp.x / (r.width / size));
				double y = Math.floor((double) mp.y / (r.height / size));

				System.out.printf("���F %.0f�}�X��, �c�F %.0f�}�X��\n", x + 1, y + 1);

				// �N���b�N�����ʒu�ɂ܂��΂��Ȃ���Δz�u���Ď�Ԃ���シ��
				if (!isExistStone((int) x, (int) y)) {
					putStone((int) x, (int) y);
					reverseStone((int) x, (int) y);

					turn *= -1; // �����̎�Ԃ�ύX

					repaint();
				}
			}

			public void mouseReleased(MouseEvent e) {
			}
		});
	}

	// �u���ꂽ�΂̎�Ԃƈʒu��stones�ɕۑ�����֐�
	private void putStone(int x, int y) {
		this.stones[x][y] = this.turn;
	}

	// �����Ŏ������ʒu�ɁA���łɐ΂��u����Ă��邩�𔻒肷��֐�
	private boolean isExistStone(int x, int y) {
		return !(this.stones[x][y] == 0);
	}

	// �΂��u���ꂽ�Ƃ��ɑ���̐΂��Ђ�����Ԃ��֐�
	private void reverseStone(int x, int y) {
		// �����������
		int[][] directions = { { -1, -1 }, // �������
				{ 0, -1 }, // �����
				{ 1, -1 }, // �E�����
				{ 1, 0 }, // �E����
				{ 1, 1 }, // �E������
				{ 0, 1 }, // ������
				{ -1, 1 }, // ��������
				{ -1, 0 }, // ������
		};

		int baseX = x;
		int baseY = y;
		int opponentStoneColor = turn * -1; // ����̐΂̐F

		for (int k = 0; k < directions.length; k++) {
			int[] direction = directions[k];
			int checkingX = baseX;
			int checkingY = baseY;
			Vector<Integer[]> turnedStones = new Vector<Integer[]>(); // �Ђ�����Ԃ���邩������Ȃ��΂̃��X�g

			for (int i = 0; i < size; i++) {
				checkingX += direction[0];
				checkingY += direction[1];

				if (checkingX < 0 || checkingY < 0 || checkingX > size - 1 || checkingY > size - 1) {
					break;
				}

				// check����΂̐F���擾
				int checkingStoneColor = this.stones[checkingX][checkingY];

				// �Ђ�����Ԃ���΂��Ђ�����Ԃ�
				if (checkingStoneColor == this.turn) {
					for (Integer[] turnedStone : turnedStones) {
						int turnedStoneX = turnedStone[0];
						int turnedStoneY = turnedStone[1];
						this.stones[turnedStoneX][turnedStoneY] = this.turn;
					}
				}
				// check���Ă���΂��A�Ђ�����Ԃ��΂̃��X�g�ɒǉ�
				else if (checkingStoneColor == opponentStoneColor) {
					Integer[] turnedStone = { checkingX, checkingY };
					turnedStones.add(turnedStone);
				} else {
					break;
				}
			}
		}
	}

	public void paint(Graphics g) {
		Rectangle r = this.getBounds();
		g.setColor(Color.green);
		g.fillRect(r.x, r.y, r.width, r.height);

		// �}�X����؂�c���̐���`�悷��
		g.setColor(Color.gray);
		for (int i = 0; i < size - 1; i++) {
			for (int j = 0; j < size - 1; j++) {
				int x = r.width / size * (i + 1);
				g.drawLine(x, 0, x, r.height);

				int y = r.height / size * (j + 1);
				g.drawLine(0, y, r.width, y);
			}
		}

		int width = r.width / size; // 1�}�X�̕�
		int height = r.height / size; // 1�}�X�̍���

		// this.stones�̏����擾���ĔՖʂɐ΂�`�悷��
		for (int i = 0; i < this.size; i++) {
			for (int j = 0; j < this.size; j++) {
				int turn = this.stones[i][j];

				if (turn == 0) {
					continue;
				}

				if (turn == 1) {
					g.setColor(Color.black);
				} else {
					g.setColor(Color.white);
				}

				int offsetX = r.width / size * (i + 1 / 2);
				int offsetY = r.height / size * (j + 1 / 2);
				g.fillOval(offsetX, offsetY, width, height);
			}
		}
	}
}
