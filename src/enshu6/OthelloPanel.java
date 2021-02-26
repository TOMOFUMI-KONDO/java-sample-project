//B8TB2108
//近藤智文

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
	private int size = 8; // オセロ盤のサイズ
	private int[][] stones;// 配置された石の情報
	private int turn = 1; // 1なら黒番、-1なら白番

	public OthelloPanel() {
		super();

		this.setPreferredSize(new Dimension(600, 600));

		// this.stonesを初期化
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

				// 縦横それぞれ何マス目がクリックされたか求める
				double x = Math.floor((double) mp.x / (r.width / size));
				double y = Math.floor((double) mp.y / (r.height / size));

				System.out.printf("横： %.0fマス目, 縦： %.0fマス目\n", x + 1, y + 1);

				// クリックした位置にまだ石がなければ配置して手番を交代する
				if (!isExistStone((int) x, (int) y)) {
					putStone((int) x, (int) y);
					reverseStone((int) x, (int) y);

					turn *= -1; // 白黒の手番を変更

					repaint();
				}
			}

			public void mouseReleased(MouseEvent e) {
			}
		});
	}

	// 置かれた石の手番と位置をstonesに保存する関数
	private void putStone(int x, int y) {
		this.stones[x][y] = this.turn;
	}

	// 引数で示した位置に、すでに石が置かれているかを判定する関数
	private boolean isExistStone(int x, int y) {
		return !(this.stones[x][y] == 0);
	}

	// 石が置かれたときに相手の石をひっくり返す関数
	private void reverseStone(int x, int y) {
		// 走査する方向
		int[][] directions = { { -1, -1 }, // 左上方向
				{ 0, -1 }, // 上方向
				{ 1, -1 }, // 右上方向
				{ 1, 0 }, // 右方向
				{ 1, 1 }, // 右下方向
				{ 0, 1 }, // 下方向
				{ -1, 1 }, // 左下方向
				{ -1, 0 }, // 左方向
		};

		int baseX = x;
		int baseY = y;
		int opponentStoneColor = turn * -1; // 相手の石の色

		for (int k = 0; k < directions.length; k++) {
			int[] direction = directions[k];
			int checkingX = baseX;
			int checkingY = baseY;
			Vector<Integer[]> turnedStones = new Vector<Integer[]>(); // ひっくり返されるかもしれない石のリスト

			for (int i = 0; i < size; i++) {
				checkingX += direction[0];
				checkingY += direction[1];

				if (checkingX < 0 || checkingY < 0 || checkingX > size - 1 || checkingY > size - 1) {
					break;
				}

				// checkする石の色を取得
				int checkingStoneColor = this.stones[checkingX][checkingY];

				// ひっくり返せる石をひっくり返す
				if (checkingStoneColor == this.turn) {
					for (Integer[] turnedStone : turnedStones) {
						int turnedStoneX = turnedStone[0];
						int turnedStoneY = turnedStone[1];
						this.stones[turnedStoneX][turnedStoneY] = this.turn;
					}
				}
				// checkしている石を、ひっくり返す石のリストに追加
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

		// マスを区切る縦横の線を描画する
		g.setColor(Color.gray);
		for (int i = 0; i < size - 1; i++) {
			for (int j = 0; j < size - 1; j++) {
				int x = r.width / size * (i + 1);
				g.drawLine(x, 0, x, r.height);

				int y = r.height / size * (j + 1);
				g.drawLine(0, y, r.width, y);
			}
		}

		int width = r.width / size; // 1マスの幅
		int height = r.height / size; // 1マスの高さ

		// this.stonesの情報を取得して盤面に石を描画する
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
